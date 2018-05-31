package com.tiancheng.createpattern.factory.demo02;


/**
 * 因为字符串极易出错，所以在工厂类中增加工厂方法
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

class SenderFactory {
	public MailSender createMail() {
		return new MailSender();
	}
	
	public SmsSender createSms() {
		return new SmsSender();
	}
}

public class SimpleFactory {
	public static void main(String[] args) {
		SenderFactory factory = new SenderFactory();
		Sender sender = factory.createMail();
		sender.Send();
	}

}
