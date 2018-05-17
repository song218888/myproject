package org.myproject.nio.reactor.singlethread;

public class NIOServer {

	public static void main(String[] args) {
		(new Thread(new Reactor(8000))).start();
	}
}
