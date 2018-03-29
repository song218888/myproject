package com.tianchen.concurrency.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueThreadIdGenerator {
	  private static final AtomicInteger uniqueId = new AtomicInteger(0);
	 
	  private static final ThreadLocal<Integer> uniqueNum = new ThreadLocal<Integer>() {
	    @Override
	    protected Integer initialValue() {
	      return uniqueId.getAndIncrement();
	    }
	  };
	 
	  public static void main(String[] args) {
	    Thread[] threads = new Thread[5];
	    for (int i = 0; i < 5; i++) {
	      String name = "Thread-" + i;
	      threads[i] = new Thread(name){
	        @Override
	        public void run() {
	          System.out.println(Thread.currentThread().getName() + ": "
	              + uniqueNum.get());
	        }
	      };
	      threads[i].start();
	    }
	 
	    System.out.println(Thread.currentThread().getName() + ": "
	        + uniqueNum.get());
	  }
	}
