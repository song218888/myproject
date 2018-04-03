package com.tianchen.concurrency.aqs.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Athlete 运动员每个运动员到达屏障后，才能开始马拉松比赛
 * 
 * @author DELL
 *
 */
class Athlete implements Runnable {
	private CyclicBarrier cyclicBarrier;
	private String name;

	public Athlete(CyclicBarrier cyclicBarrier, String name) {
		this.cyclicBarrier = cyclicBarrier;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + "就位");
		try {
			cyclicBarrier.await();
			System.out.println("====== : " + Thread.currentThread().getName());
			Random random = new Random();
			double time = random.nextDouble() + 9;
			System.out.println(name + ": " + time);
		} catch (Exception e) {
		}
	}
}

public class CyclicBarrierDemo01 {
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

	public void start() {
		List<Athlete> athleteList = new ArrayList<>();
		athleteList.add(new Athlete(cyclicBarrier, "博尔特"));
		athleteList.add(new Athlete(cyclicBarrier, "鲍威尔"));
		athleteList.add(new Athlete(cyclicBarrier, "盖伊"));
		athleteList.add(new Athlete(cyclicBarrier, "布雷克"));
		athleteList.add(new Athlete(cyclicBarrier, "加特林"));
		athleteList.add(new Athlete(cyclicBarrier, "苏炳添"));
		athleteList.add(new Athlete(cyclicBarrier, "路人甲"));
		athleteList.add(new Athlete(cyclicBarrier, "路人乙"));
		Executor executor = Executors.newFixedThreadPool(8);
		for (Athlete athlete : athleteList) {
			executor.execute(athlete);
		}
	}
	
	public static void main(String[] args) {
		CyclicBarrierDemo01 cbt = new CyclicBarrierDemo01();
		cbt.start();
	}
}
