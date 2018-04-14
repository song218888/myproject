package com.tiancheng.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ByteBufferDemo01 {
	public static void main(String[] args)throws IOException {
		
		Socket socket = new Socket();
		SocketAddress remote = new InetSocketAddress("127.0.0.1", 8088);
		socket.connect(remote);
	}
}
