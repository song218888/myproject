package org.myproject.nio.reactor.singlethread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Reactor implements Runnable {
	Selector selector = null;
	private int port;

	public Reactor(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			initServer(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
	 * 
	 * @param port
	 *            绑定的端口号
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException {
		// 获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		// 将该通道对应的ServerSocket绑定到port端口
		serverChannel.socket().bind(new InetSocketAddress(port));
		// 获得一个通道管理器
		this.selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("start server！");

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
				if (key.isAcceptable()) {

					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);

					// 在这里可以给客户端发送信息哦
					channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
				} else if (key.isWritable()) {
					write(key);
				} else if (key.isReadable()) {
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
		channel.register(selector, SelectionKey.OP_WRITE, buffer);//注册写事件 

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

}
