package chaptor02.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

interface HelloWorld {
	void printHelloWorld(String msg);

}

class HelloWorldImpl implements HelloWorld {
	@Override
	public void printHelloWorld(String msg) {
		System.out.println("Hello : " + msg);
	}

}

@Configuration
public class JavaConfigDemo {

	@Bean(name = "helloBean")
	public HelloWorld helloWorld() {
		return new HelloWorldImpl();
	}

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigDemo.class);
		HelloWorld obj = (HelloWorld) context.getBean("helloBean");
		obj.printHelloWorld("Spring Java Config");

	}
}
