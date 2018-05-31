package com.tiancheng.createpattern.factorymethod;

/**
 * 简单工厂依赖于工厂类，如果一旦需要拓展，工厂类需要跟着改动
 * 
 * 解决的方式是将工厂类定义为一个接口以实现解耦
 * 
 * @author DELL
 *
 */
interface Sender {  
    public void Send();  
}  


class MailSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is mailsender!");  
    }  
}  


class SmsSender implements Sender {  
  
    @Override  
    public void Send() {  
        System.out.println("this is sms sender!");  
    }  
}  


class SendMailFactory implements Provider {  
      
    @Override  
    public Sender produce(){  
        return new MailSender();  
    }  
}  


class SendSmsFactory implements Provider{  
  
    @Override  
    public Sender produce() {  
        return new SmsSender();  
    }  
}  

interface Provider {  
    public Sender produce();  
}  

public class FactoryMethodPatternDemo {
	public static void main(String[] args) {
		Provider provider = new SendMailFactory();  
        Sender sender = provider.produce();  
        sender.Send();  
	}
}
