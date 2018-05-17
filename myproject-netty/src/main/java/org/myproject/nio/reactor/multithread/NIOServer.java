package org.myproject.nio.reactor.multithread;

public class NIOServer {

	public static void main(String[] args) {
		(new Thread(new NIOServerAcceptor(8000))).start();
	}
}
