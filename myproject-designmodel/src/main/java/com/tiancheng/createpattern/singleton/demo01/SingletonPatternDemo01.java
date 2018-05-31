package com.tiancheng.createpattern.singleton.demo01;


/**
 * 第一种，采用懒加载的方式
 * 
 * getInstance()在多线程情况下不安全
 * 
 * I：synchronized getInstance()，但性能很低
 * @author DELL
 *
 */
class Singleton {  
	  
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */  
    private static Singleton instance = null;  
  
    /* 私有构造方法，防止被实例化 */  
    private Singleton() {  
    }  
  
    /* 静态工程方法，创建实例 */  
    public static Singleton getInstance() {  
        if (instance == null) {  
            instance = new Singleton();  
        }  
        return instance;  
    }  
  
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return instance;  
    }  
}  

public class SingletonPatternDemo01 {

}
