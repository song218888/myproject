package chaptor02.beaninit.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TelefonBeanPostProcess implements BeanPostProcessor {
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("TelefonBeanPostProcess postProcessBeforeInitialization######## "+beanName);
		System.out.println("$$$$$$$$$$$$" + ((Telefon)bean).getFiyat());
		return bean;
	}
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("TelefonBeanPostProcess postProcessAfterInitialization######## "+beanName);
		return bean;
	}
}