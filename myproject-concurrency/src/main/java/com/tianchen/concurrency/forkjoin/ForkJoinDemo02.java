package com.tianchen.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

class PrintTask extends RecursiveAction{
	
	private static final long serialVersionUID = -8711528845668300171L;
	private static final int threshold = 50; 
    private int start; 
    private int end; 
   
    public PrintTask(int start, int end) { 
        this.start = start; 
        this.end = end; 
    } 
   
    @Override 
    protected void compute() { 
        if (end - start < threshold) { 
            for (int i = start; i < end; i++) { 
                System.out.println(Thread.currentThread().getName() + " i: " + i); 
            } 
        } else { 
            int middle = (start + end) / 2; 
            PrintTask left = new PrintTask(start, middle); 
            PrintTask right = new PrintTask(middle, end); 
            left.fork(); 
            right.fork(); 
        } 
    }  
}

public class ForkJoinDemo02 {
	public static void main(String[] args) throws InterruptedException { 
        ForkJoinPool pool = new ForkJoinPool(); 
        pool.submit(new PrintTask(0, 300)); 
        pool.awaitTermination(2, TimeUnit.SECONDS); 
        pool.shutdown(); 
    }  
}
