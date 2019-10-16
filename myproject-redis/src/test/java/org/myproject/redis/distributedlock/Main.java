package org.myproject.redis.distributedlock;

public class Main {
	public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
//            ThreadA threadA = new ThreadA(service);
//            threadA.start();
        }
    }
}
