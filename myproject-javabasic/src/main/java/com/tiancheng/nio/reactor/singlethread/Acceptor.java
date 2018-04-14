package com.tiancheng.nio.reactor.singlethread;

import java.io.IOException;
import java.nio.channels.SocketChannel;


/**
 * 转发给Handler处理
 * @author DELL
 *
 */
public class Acceptor implements Runnable{  
    private Reactor reactor;  
    public Acceptor(Reactor reactor){  
        this.reactor=reactor;  
    }  
    @Override  
    public void run() {  
        try {  
            SocketChannel socketChannel=reactor.serverSocketChannel.accept();  
            if(socketChannel!=null)//调用Handler来处理channel  
                new Handler(reactor.selector, socketChannel);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  