package com.tiancheng.reflect;

class Demo {

}

class Person {
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "[" + this.name + "  " + this.age + "]";
	}

	private String name;
	private int age;
}

public class ReflectDemo {
	public static void main(String[] args) {
		Demo demo = new Demo();
		System.out.println(demo.getClass().getName());

		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		try {
			// 一般尽量采用这种形式
			demo1 = Class.forName("com.tiancheng.reflect.Demo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		demo2 = new Demo().getClass();
		demo3 = Demo.class;

		System.out.println("类名称   " + demo1.getName());
		System.out.println("类名称   " + demo2.getName());
		System.out.println("类名称   " + demo3.getName());
		System.out.println(" =================================== ");
		
		
		Class<?> person=null;
		        try{
		        	person=Class.forName("com.tiancheng.reflect.Person");
		         }catch (Exception e) {
		             e.printStackTrace();
		         }
		         Person per=null;
		         try {
		             per=(Person)person.newInstance();
		         } catch (InstantiationException e) {
		             e.printStackTrace();
		         } catch (IllegalAccessException e) {
		             e.printStackTrace();
		         }
		         per.setName("Rollen");
		         per.setAge(20);
		         System.out.println(per);
	}
}
