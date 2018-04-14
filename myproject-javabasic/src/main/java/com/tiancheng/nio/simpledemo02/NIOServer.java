package com.tiancheng.nio.simpledemo02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	
	private Selector selector;
	
	
	/** 
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作 
     * @param port  绑定的端口号 
     * @throws IOException 
     */  
	public void initServer(int port)throws IOException{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		this.selector =  Selector.open();
		
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	
	/** 
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理 
     * @throws IOException 
     */
	public void listen()throws IOException{
		System.out.println("服务端启动成功");
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
		
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel)key.channel();
					SocketChannel channel =  server.accept();
					channel.configureBlocking(false);
					
					channel.write(ByteBuffer.wrap(new String("Im server side.......").getBytes()));
				
					//在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。  
                    channel.register(this.selector, SelectionKey.OP_READ);  
				}else if(key.isReadable()){
					read(key);
				}
			}
		
		}
	}
	
	
	public void read(SelectionKey key)throws IOException{
		SocketChannel channel = (SocketChannel)key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(10);  
        channel.read(buffer);  
        byte[] data = buffer.array();  
        String msg = new String(data).trim();  
        System.out.println("服务端收到信息："+msg);  
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
        channel.write(outBuffer);// 将消息回送给客户端  
		
	}
	
	
	public static void main(String[] args) throws IOException {  
        NIOServer server = new NIOServer();  
        server.initServer(8000);  
        server.listen();  
    }  
	
}
