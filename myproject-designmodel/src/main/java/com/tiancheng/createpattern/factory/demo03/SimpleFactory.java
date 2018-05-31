package com.tiancheng.createpattern.factory.demo03;


/**
 * 第二种方法需要创建工厂类的实例，如果有多用调用，用浪费内存消耗
 * 使用静态的工厂方法，可以不用new工厂对类而直接调用
 * @author DELL
 *
 */
interface Sender {
	public void Send();
}

class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is mail sender!");
	}
}


class SmsSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is sms sender!");
	}
}

class SenderFactory {
	public static MailSender createMail() {
		return new MailSender();
	}
	
	public static SmsSender createSms() {
		return new SmsSender();
	}
}

public class SimpleFactory {
	public static void main(String[] args) {
		Sender sender = SenderFactory.createMail();
		sender.Send();
	}

}
