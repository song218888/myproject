ReentrantLock
    1：与Synchronized区别：http://www.cnblogs.com/chenssy/p/4735381.html
    2：一种可重入锁，是独占式的，与synchronized一样
    
    
CyclicBarrier(循环屏障)
	一组线程达到屏障时被阻挡，只有最后一个线程到达时，所有线程才可以继续前进
	典型场景与案例：CyclicBarrierTest
	一组线程使用await()指定barrier，所有线程都到达各自的barrier后，再同时执行各自barrier下面的代码


Semaphore：
	是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源
	参考：https://www.cnblogs.com/cl1024cl/p/6205011.html


CountDownLatch: 
	一个线程A或是组线程A等待其它线程执行完毕后，一个线程A或是组线程A才继续执行   
    