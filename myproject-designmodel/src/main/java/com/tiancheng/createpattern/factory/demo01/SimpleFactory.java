package com.tiancheng.createpattern.factory.demo01;

/**
 * 发布短信或邮件的工厂模式 不用直接new对象，这样去解耦
 * 一般我们用new的方式去构造一个对象，这样对象变化时，代码也要跟着改变
 * 工厂模式可以帮我们创建对象
 * 但简单工厂模式极易出错，因为是根据字符串不同去选择
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
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}
}

public class SimpleFactory {
	public static void main(String[] args) {
		SenderFactory factory = new SenderFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
	}

}
