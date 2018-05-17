package org.myproject.nio.reactor.multithread;

import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class NIOServerReactorThreadGroup {
	private static final AtomicInteger requestCounter = new AtomicInteger();  //请求计数器  
    
    private final int nioThreadCount;  // 线程池IO线程的数量  
    private static final int DEFAULT_NIO_THREAD_COUNT;   
    private NIOServerReactorThread[] nioThreads;  
      
    static {  
//      DEFAULT_NIO_THREAD_COUNT = Runtime.getRuntime().availableProcessors() > 1  
//              ? 2 * (Runtime.getRuntime().availableProcessors() - 1 ) : 2;  
          
        DEFAULT_NIO_THREAD_COUNT = 4;  
    }  
      
    public NIOServerReactorThreadGroup() {  
        this(DEFAULT_NIO_THREAD_COUNT);  
    }  
      
    public NIOServerReactorThreadGroup(int threadCount) {  
        if(threadCount < 1) {  
            threadCount = DEFAULT_NIO_THREAD_COUNT;  
        }  
        this.nioThreadCount = threadCount;  
        this.nioThreads = new NIOServerReactorThread[threadCount];  
        for(int i = 0; i < threadCount; i ++ ) {  
            this.nioThreads[i] = new NIOServerReactorThread();  
            this.nioThreads[i].start(); //构造方法中启动线程，由于nioThreads不会对外暴露，故不会引起线程逃逸  
        }  
          
        System.out.println("Nio线程数量：" + threadCount);  
    }  
      
    public void dispatch(SocketChannel socketChannel) {  
        if(socketChannel != null ) {  
            next().register(socketChannel);  
        }  
    }  
      
    protected NIOServerReactorThread next() {  
        return this.nioThreads[ requestCounter.getAndIncrement() %  nioThreadCount ];  
    }  
      
}
