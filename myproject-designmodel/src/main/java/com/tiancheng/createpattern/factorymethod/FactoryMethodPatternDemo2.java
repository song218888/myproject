package com.tiancheng.createpattern.factorymethod;

//工厂方法
class ShapeFactory2 {

	@SuppressWarnings("unchecked")
	public static <T> T getClass(Class<? extends T> clazz) {
		T obj = null;

		try {
			obj = (T) Class.forName(clazz.getName()).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return obj;
	}
}

public class FactoryMethodPatternDemo2 {
	public static void main(String[] args) {
		// 获取 Circle 的对象，并调用它的 draw 方法
		Circle circle = ShapeFactory2.getClass(Circle.class);

		// 调用 Circle 的 draw 方法
		circle.draw();

		// 获取 Square 的对象，并调用它的 draw 方法
		Square square = ShapeFactory2.getClass(Square.class);

		// 调用 Square 的 draw 方法
		square.draw();

		// 获取 Rectangle 的对象，并调用它的 draw 方法
		Rectangle rectangle = ShapeFactory2.getClass(Rectangle.class);

		// 调用 Rectangle 的 draw 方法
		rectangle.draw();
	}
}
