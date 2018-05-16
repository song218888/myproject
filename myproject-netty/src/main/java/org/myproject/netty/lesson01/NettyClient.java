package org.myproject.netty.lesson01;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 一般一个简单的Client会扮演如下角色： 连接到Server 向Server写数据 等待Server返回数据 关闭连接
 * BootsTrapping的过程，和Server端类似，只不过Client端要同时指定连接主机的IP和Port 
 * 1.创建一个ServerBootstrap实例 
 * 2.创建一个EventLoopGroup来处理各种事件，如处理链接请求，发送接收数据等。 
 * 3.定义一个远程InetSocketAddress好让客户端连接 
 * 4.当连接完成之后，Handler会被执行一次 
 * 5.所有准备好之后调用ServerBootstrap.connect()方法连接Server
 * 
 * @author dell
 *
 */
public class NettyClient {
	private final String host;
	private final int port;

	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.remoteAddress(new InetSocketAddress(host, port));
			b.handler(new ChannelInitializer<SocketChannel>() {

				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new NettyClientHandler());
				}
			});
			ChannelFuture f = b.connect().sync();
			f.addListener(new ChannelFutureListener() {

				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("client connected");
					} else {
						System.out.println("server attemp failed");
						future.cause().printStackTrace();
					}

				}
			});
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		new NettyClient("127.0.0.1", 3331).start();
	}
}
