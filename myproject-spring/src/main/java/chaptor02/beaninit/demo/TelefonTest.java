package chaptor02.beaninit.demo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TelefonTest {
	private static AbstractApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("beaninitcontext.xml");
		context.registerShutdownHook();

		Telefon telefon = context.getBean("telId", Telefon.class);

		System.out.println(telefon);

		context.registerShutdownHook();

	}
}
