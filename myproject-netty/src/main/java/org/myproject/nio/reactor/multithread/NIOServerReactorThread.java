package org.myproject.nio.reactor.multithread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class NIOServerReactorThread extends Thread {

	private Selector selector;
	private List<SocketChannel> waitRegisterList = new ArrayList<SocketChannel>(512);
	private ReentrantLock registerLock = new ReentrantLock();

	public NIOServerReactorThread() {
		try {
			this.selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * socket channel
	 * 
	 * @param socketChannel
	 */
	public void register(SocketChannel socketChannel) {
		if (socketChannel != null) {
			try {
				registerLock.lock();
				waitRegisterList.add(socketChannel);
			} finally {
				registerLock.unlock();
			}
		}
	}

	// private

	public void run() {
		while (true) {
			Set<SelectionKey> ops = null;
			try {
				selector.select(1000);
				ops = selector.selectedKeys();

				// 处理相关事件
				for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
					SelectionKey key = it.next();
					it.remove();

					if (key.isWritable()) { // 向客户端发送请求
						write(key);
					} else if (key.isReadable()) { // 接受客户端请求
						read(key);
					}

				}
			} catch (Throwable e) {
				e.printStackTrace();
				System.out.println("客户端主动断开连接。。。。。。。");
			}

			// 注册事件
			if (!waitRegisterList.isEmpty()) {
				try {
					registerLock.lock();
					for (Iterator<SocketChannel> it = waitRegisterList.iterator(); it.hasNext();) {
						SocketChannel sc = it.next();
						try {
							sc.register(selector, SelectionKey.OP_READ);
						} catch (Throwable e) {
							e.printStackTrace();// ignore
						}
						it.remove();
					}

				} finally {
					registerLock.unlock();
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
		channel.register(selector, SelectionKey.OP_WRITE, buffer);// 注册写事件

	}

	public void write(SelectionKey key) throws IOException {
		// 向客户端发送请求
		SocketChannel clientChannel = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		buf.flip();
		clientChannel.write(buf);
		System.out.println("服务端向客户端发送数据。。。");
		// 重新注册读事件
		clientChannel.register(selector, SelectionKey.OP_READ);

	}

}
