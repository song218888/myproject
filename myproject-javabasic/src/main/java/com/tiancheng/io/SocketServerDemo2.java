package com.tiancheng.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketServerDemo2 extends Thread {
	private ServerSocket serverSocket;

	public SocketServerDemo2(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void run() {
		while (true) {
			try {
				Socket server = serverSocket.accept();
				System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println("server revice : "+in.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
				server.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			Thread t = new SocketServerDemo2(8080);
			t.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
