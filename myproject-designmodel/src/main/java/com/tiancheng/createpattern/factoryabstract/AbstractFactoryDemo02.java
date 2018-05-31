package com.tiancheng.createpattern.factoryabstract;

interface CPUFactory {
	public CPU makeCPU();
}

class IntelCPUFactory implements CPUFactory {
	@Override
	public CPU makeCPU() {
		return new IntelCPU();
	}
}

class AMDCPUFactory implements CPUFactory {
	@Override
	public CPU makeCPU() {
		return new AMDCPU();
	}
}

interface MainBoardFactory {
	public MainBoard makeBoard();
}

class IntelMainBoardFactory implements MainBoardFactory {
	@Override
	public MainBoard makeBoard() {
		return new IntelMainBoard();
	}
}

class AMDMainBoardFactory implements MainBoardFactory {
	@Override
	public MainBoard makeBoard() {
		return new AMDMainBoard();
	}
}

interface CPU {

}

class IntelCPU implements CPU {

}

class AMDCPU implements CPU {

}

interface MainBoard {

}

class IntelMainBoard implements MainBoard {

}

class AMDMainBoard implements MainBoard {

}

class Computer {

	private CPU cpu;
	
	private MainBoard mainBoard;

	public Computer(CPU cpu, MainBoard mainBoard) {
		this.cpu = cpu;
		this.mainBoard = mainBoard;
	}

	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", mainBoard=" + mainBoard + "]";
	}
	
	
}

/**
 * 出现了Intel CPU使用AMD主板的问题
 * @author DELL
 *
 */
public class AbstractFactoryDemo02 {
	public static void main(String[] args) {
		// 得到 Intel 的 CPU
		CPUFactory cpuFactory = new IntelCPUFactory();
		CPU cpu = cpuFactory.makeCPU();

		// 得到 AMD 的主板
		MainBoardFactory mainBoardFactory = new AMDMainBoardFactory();
		MainBoard mainBoard = mainBoardFactory.makeBoard();

		// 组装 CPU 和主板
		Computer computer = new Computer(cpu, mainBoard);
		
		System.out.println(computer);
	}
}
