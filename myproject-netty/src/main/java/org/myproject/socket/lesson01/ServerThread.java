package org.myproject.socket.lesson01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 获取socket输出流，用来向客户端发送数据
			PrintStream out = new PrintStream(socket.getOutputStream());

			// 获取socket输入流，用来读取客户端发过来的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			boolean flag = true;

			while (flag) {
				// 接收从客户端发送过来的数据
				String str = buf.readLine();
				if (str == null || "".equals(str)) {
					flag = false;
				} else {
					if ("bye".equals(str)) {
						flag = false;
					} else {
						// 将接收到的字符串前面加上echo，发送到对应的客户端
						out.println("echo:" + str);
					}
				}
			}
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
