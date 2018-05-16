package org.myproject.netty.lesson01;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 业务逻辑ServerHandler 
 * 要想处理接收到的数据，我们必须继承ChannelInboundHandlerAdapter接口
 * 重写里面的MessageReceive方法，
 * 每当有数据到达，此方法就会被调用（一般是Byte类型数组） 
 * 我们就在这里写我们的业务逻辑
 * 
 * @author dell
 *
 */
@SuppressWarnings("deprecation")
// 注解@Sharable可以让它在channels间共享
@Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("server received data :" + msg);
		ctx.write(msg);// 写回数据，
	}

	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // flush掉所有写回的数据
				.addListener(ChannelFutureListener.CLOSE); // 当flush完成后关闭channel
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();// 捕捉异常信息
		ctx.close();// 出现异常时关闭channel
	}
}
