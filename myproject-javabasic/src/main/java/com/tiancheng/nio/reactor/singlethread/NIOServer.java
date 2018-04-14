package com.tiancheng.nio.reactor.singlethread;

import java.io.IOException;

public class NIOServer {
	public static void main(String[] args)throws IOException {  
        (new Thread(new Reactor(8000))).start();  
  
    }
	
}
