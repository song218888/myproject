package com.tiancheng.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo01 {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket socket = serverSocket.accept();
			System.out.println("==============");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
