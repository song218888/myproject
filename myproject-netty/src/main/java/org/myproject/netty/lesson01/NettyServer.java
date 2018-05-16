package org.myproject.netty.lesson01;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty框架对NIO进行了封装，下面是一个简单的netty应用例子的代码。
 * 
 * 服务端(netty server)
 * 	一个NettyServer程序主要由两部分组成：
 * 		BootsTrapping:配置服务器端基本信息
 * 		ServerHandler:真正的业务逻辑处理
 * 
 * BootsTrapping的过程:
 * 		1. 创建一个ServerBootstrap实例
 * 		2. 创建一个EventLoopGroup来处理各种事件，如处理链接请求，发送接收数据等。
 * 		3. 定义本地InetSocketAddress( port)好让Server绑定
 * 		4. 创建childHandler来处理每一个链接请求   
 * 		5. 所有准备好之后调用ServerBootstrap.bind()方法绑定Server
 * @author dell
 *
 */
public class NettyServer {
	private static final int port = 8000;

	public void start() throws InterruptedException {
		ServerBootstrap b = new ServerBootstrap();
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			b.group(group);
			
			// 设置nio类型的channel
			b.channel(NioServerSocketChannel.class);
			
			// 设置监听端口
			b.localAddress(new InetSocketAddress(port));
			
			// 有连接到达时会创建一个channel
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				protected void initChannel(SocketChannel ch) throws Exception {
					// pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
					ch.pipeline().addLast("myHandler", new NettyServerHandler());
				}
			});
			ChannelFuture f = b.bind().sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
			System.out.println(NettyServer.class.getName() + " started and listen on " + f.channel().localAddress());
			f.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 关闭EventLoopGroup，释放掉所有资源包括创建的线程
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) {
		try {
			new NettyServer().start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
