package observable.demo02;

import java.util.ArrayList;
import java.util.List;

interface Subject {
	void addObserver(Observer obj);

	void deleteObserver(Observer obj);

	void notifyObserver();
}

class TeacherSubject implements Subject {
	// 用来存放和记录观察者
	private List<Observer> observers = new ArrayList<Observer>();
	// 记录状态的字符串
	private String info;

	@Override
	public void addObserver(Observer obj) {
		observers.add(obj);
	}

	@Override
	public void deleteObserver(Observer obj) {
		int i = observers.indexOf(obj);
		if (i >= 0) {
			observers.remove(obj);
		}
	}

	@Override
	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			Observer o = (Observer) observers.get(i);
			o.update(info);
		}
	}

	// 布置作业的方法,在方法最后,需要调用notifyObserver()方法,通知所有观察者更新状态
	public void setHomework(String info) {
		this.info = info;
		System.out.println("今天的作业是" + info);
		this.notifyObserver();
	}

}

interface Observer {
	public void update(String info);
}

class StudentObserver implements Observer {

	// 保存一个Subject的引用,以后如果可以想取消订阅,有了这个引用会比较方便
	private TeacherSubject t;
	// 学生的姓名,用来标识不同的学生对象
	private String name;

	// 构造器用来注册观察者
	public StudentObserver(String name, TeacherSubject t) {
		this.name = name;
		this.t = t;
		// 每新建一个学生对象,默认添加到观察者的行列
		t.addObserver(this);
	}

	@Override
	public void update(String info) {
		System.out.println(name + "   得到作业:" + info);

	}

}

public class TestObserver {
	public static void main(String[] args) {

		TeacherSubject teacher = new TeacherSubject();
		StudentObserver zhangSan = new StudentObserver("张三", teacher);
		StudentObserver LiSi = new StudentObserver("李四", teacher);
		StudentObserver WangWu = new StudentObserver("王五", teacher);

		teacher.setHomework("第二页第六题");
//		teacher.setHomework("第三页第七题");
//		teacher.setHomework("第五页第八题");
	}
}
