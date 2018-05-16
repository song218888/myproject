package org.myproject.netty.lesson01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 业务逻辑ServerHandler 要想处理接收到的数据，我们必须继承ChannelInboundHandlerAdapter接口
 * 重写里面的MessageReceive方法， 每当有数据到达，此方法就会被调用（一般是Byte类型数组） 我们就在这里写我们的业务逻辑
 * 
 * @author dell
 *
 */
// 注解@Sharable可以让它在channels间共享
@Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	/**
	 * 此方法会在连接到服务器后被调用
	 */
	public void channelActive(ChannelHandlerContext ctx) {
		ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	}

	/**
	 * 捕捉到异常
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
	/**
	 * 此方法会在接收到服务器数据后调用
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
		
	}
}
