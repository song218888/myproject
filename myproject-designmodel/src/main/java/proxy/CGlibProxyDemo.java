package proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

class StudentDao {
	public void save() {
		System.out.println("----已经保存数据!----");
	}
}

class StudentProxyFactory implements MethodInterceptor {
	// 维护目标对象
	private Object target;

	public StudentProxyFactory(Object target) {
		this.target = target;
	}

	// 给目标对象创建一个代理对象
	public Object getProxyInstance() {
		// 1.工具类
		Enhancer en = new Enhancer();
		// 2.设置父类
		en.setSuperclass(target.getClass());
		// 3.设置回调函数
		en.setCallback(this);
		// 4.创建子类(代理对象)
		return en.create();

	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("开始事务...");

		// 执行目标对象的方法
		Object returnValue = method.invoke(target, args);

		System.out.println("提交事务...");

		return returnValue;
	}
}

public class CGlibProxyDemo {
	public static void main(String[] args) {
		// 目标对象
		StudentDao target = new StudentDao();

		// 代理对象
		StudentDao proxy = (StudentDao) new StudentProxyFactory(target).getProxyInstance();

		// 执行代理对象的方法
		proxy.save();
	}
}
