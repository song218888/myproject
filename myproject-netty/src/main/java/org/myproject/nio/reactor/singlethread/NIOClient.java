package org.myproject.nio.reactor.singlethread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	// 通道管理器
	private Selector selector;

	/**
	 * 获得一个Socket通道，并对该通道做一些初始化的工作
	 * 
	 * @param ip
	 *            连接的服务器的ip
	 * @param port
	 *            连接的服务器的端口号
	 * @throws IOException
	 */
	public void initClient(String ip, int port) throws IOException {
		// 获得一个Socket通道
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		// 将该通道对应的ServerSocket绑定到port端口
		channel.socket().bind(new InetSocketAddress(port));
		// 获得一个通道管理器
		this.selector = Selector.open();

		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
		// 用channel.finishConnect();才能完成连接
		channel.connect(new InetSocketAddress(ip, port));

		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		channel.register(selector, SelectionKey.OP_CONNECT);

	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			selector.select();
			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			while (keys.hasNext()) {
				SelectionKey key = (SelectionKey) keys.next();
				// 删除已选的key,以防重复处理
				keys.remove();
				// 客户端请求连接事件
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					// 如果正在连接，则完成连接
					if (channel.isConnectionPending()) {
						channel.finishConnect();
					}
					
					channel.configureBlocking(false);

					// 在这里可以给服务器发送信息哦
					channel.write(ByteBuffer.wrap(new String("向服务端发送了一条信息").getBytes()));
					//在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
				} else if(key.isWritable()) {  
                   write(key);
                }else if (key.isReadable()) {
					read(key);
				}
			}
		}
	}

	public void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(10);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务端收到信息：" + msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		channel.write(outBuffer);// 将消息回送给客户端

	}
	
	public void write(SelectionKey key) throws IOException {
		//向客户端发送请求  
        SocketChannel clientChannel = (SocketChannel)key.channel();  
        ByteBuffer buf = (ByteBuffer)key.attachment();  
        buf.flip();  
        clientChannel.write(buf);  
        System.out.println("服务端向客户端发送数据。。。");  
        //重新注册读事件  
        clientChannel.register(selector, SelectionKey.OP_READ);   

	}

	/**
	 * 启动服务端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NIOClient server = new NIOClient();
		server.initClient("127.0.0.1",8000);
		server.listen();
	}
}
