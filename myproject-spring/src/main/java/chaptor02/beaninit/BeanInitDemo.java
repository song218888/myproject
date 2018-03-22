package chaptor02.beaninit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class HelloWorld implements BeanNameAware,BeanFactoryAware, BeanPostProcessor,InitializingBean,DisposableBean{   
	  
    private String hello;   
  
    public void setBeanName(String arg0) {   
        System.out.println("调用BeanNameAware的setBeanName()...");   
    }   
  
    public String getHello() {   
        return hello;   
    }   
  
    public void setHello(String hello) {   
        this.hello = hello;   
        System.out.println("调用setHello()..." );   
    }   
  
    public void customInit() {   
        System.out.println("调用customInit()...");   
    }   
  
    public void customDestroy() {   
        System.out.println("调用customDestroy()...");   
    }  
    
    
    @Override
    public Object postProcessBeforeInitialization(Object bean,
            String beanName) throws BeansException {
        if ("narCodeService".equals(beanName)) {//过滤掉bean实例ID为narCodeService
            return bean;
        }
        System.out.println("============调用BeanPostProcessor的postProcessBeforeInitialization()...");   
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bean;
    }

  
    @Override
    public Object postProcessAfterInitialization(Object bean,
            String beanName) throws BeansException {
        if ("narCodeService".equals(beanName)) {
            return bean;
        }
        System.out.println("============调用BeanPostProcessor的postProcessAfterInitialization()...");   
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bean;
    }  
     
  
    public void destroy() throws Exception {   
        System.out.println("调用DisposableBean的destory()...");   
    }   
  
    public void afterPropertiesSet() throws Exception {   
        System.out.println("调用InitializingBean的afterPropertiesSet()...");   
    }   
  
    public void setBeanFactory(BeanFactory arg0) throws BeansException {   
        System.out.println("调用BeanFactoryAware的setBeanFactory()...");   
    }   
}  


public class BeanInitDemo {
	public static void main(String[] args)throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("beaninitcontext.xml");
		HelloWorld hello = (HelloWorld)context.getBean("helloWorld");
		hello.getHello();
		hello.destroy();
	}
}
