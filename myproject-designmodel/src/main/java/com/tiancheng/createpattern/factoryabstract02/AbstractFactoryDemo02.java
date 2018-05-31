package com.tiancheng.createpattern.factoryabstract02;


/**
 * 抽象工厂，解决产品簇问题
 * 
 * 对创建工厂的产品进行约束
 * 
 * @author DELL
 *
 */

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

interface ComputerFactory{
	CPU makeCPU();
	MainBoard makeMainBoard();
}

class IntelComputerFactory implements ComputerFactory{

	@Override
	public CPU makeCPU() {
		// TODO Auto-generated method stub
		return new IntelCPU();
	}

	@Override
	public MainBoard makeMainBoard() {
		// TODO Auto-generated method stub
		return new IntelMainBoard();
	}
	
}

class AmdComputerFactory implements ComputerFactory{
	
	@Override
	public CPU makeCPU() {
		// TODO Auto-generated method stub
		return new AMDCPU();
	}

	@Override
	public MainBoard makeMainBoard() {
		// TODO Auto-generated method stub
		return new AMDMainBoard();
	}
}

public class AbstractFactoryDemo02 {

}
