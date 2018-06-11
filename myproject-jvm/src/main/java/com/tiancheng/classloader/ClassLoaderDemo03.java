package com.tiancheng.classloader;

class Singleton {
    private static int counter1;  
    private static int counter2 = 0;  
    private static Singleton singleton=new Singleton();  
    
    public Singleton() {  
       counter1++;  
       counter2++;  
    }  
    
    public static int getCounter1() {  
       return counter1;  
    } 
    
    public static int getCounter2() {  
       return counter2;  
    }  
    /** 
     * @Description:实例化 
     * @return 
     * @author YHJ create at 2011-6-4 下午08:34:43 
     */  
    public static Singleton getInstance(){  
       return singleton;  
    }  
} 

public class ClassLoaderDemo03 {  
    /** 
     * @Description:启动类 
     * @param args 
     * @author YHJ create at 2011-6-4 下午08:30:12 
     */  
    @SuppressWarnings("static-access")  
    public static void main(String[] args) {  
       Singleton singleton=Singleton.getInstance();  
       System.out.println("counter1:"+singleton.getCounter1());  
       System.out.println("counter2:"+singleton.getCounter2());  
    }  
}
