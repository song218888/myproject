1：创建线程的三种方式
	I：extends Thread
	II：implement Runnable
	III implement Callable
2：线程的中断interrupted

3：线程join
   thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B