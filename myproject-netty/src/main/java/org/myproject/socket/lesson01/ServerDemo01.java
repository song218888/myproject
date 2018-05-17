package org.myproject.socket.lesson01;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo01 {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(20006);
			Socket socket = null;
			boolean f = true;
			
			while(f){
				//等待客户端连接，如果没有连接，会一直阻塞
				socket = serverSocket.accept();
				System.out.println("客户端连接成功");
				
				new Thread(new ServerThread(socket)).start();
			}
			
			serverSocket.close();
		} catch (Exception e) {
			System.out.println("服务端异常!");
		}
		
	}
}
