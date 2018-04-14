package com.tiancheng.nio.reactor.singlethread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;


/**
 * 1：构造函数初始化ServerSocketChannel对象，并将通道注册Accept事件
 * 2：Reactor轮询channel
 * @author DELL
 *
 */
public class Reactor implements Runnable{
	final Selector selector;  
	  
    final ServerSocketChannel serverSocketChannel;  
  
    public Reactor(int port) throws IOException {  
        selector = Selector.open();  
        serverSocketChannel = ServerSocketChannel.open();  
        serverSocketChannel.socket().bind(new InetSocketAddress(port));  
        serverSocketChannel.configureBlocking(false);  
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  
        key.attach(new Acceptor(this));  
    }  
  
    @Override  
    public void run() {  
        while (!Thread.interrupted()) {  
            try {  
                selector.select();  
                Set<SelectionKey> selectionKeys = selector.selectedKeys();  
                for(SelectionKey selectionKey : selectionKeys){  
                    dispatch(selectionKey);  
                }  
                selectionKeys.clear();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
  
    }  
  
    private void dispatch(SelectionKey selectionKey) {  
        Runnable run = (Runnable) selectionKey.attachment();  
        if(run != null){  
            run.run();  
        }  
    }  
}
