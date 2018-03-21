package chaptor01.factory2;

import org.springframework.beans.factory.FactoryBean;

class Car{
	private int maxSpeed;
	private String brand;
	private double price;
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

class CarFactoryBean implements FactoryBean<Car>{
	
	private String carInfo;

	@Override
	public Car getObject() throws Exception {
		Car car =  new  Car () ;    
        String []  infos =  carInfo .split ( "," ) ;    
        car.setBrand ( infos [ 0 ]) ;    
        car.setMaxSpeed ( Integer. valueOf ( infos [ 1 ])) ;    
        car.setPrice ( Double. valueOf ( infos [ 2 ])) ;    
        return  car;
	}

	@Override
	public Class<Car> getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
	
}



public class FactoryBean2 {

}
