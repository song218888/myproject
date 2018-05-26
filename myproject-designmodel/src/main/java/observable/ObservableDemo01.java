package observable;

import java.util.Observable;
import java.util.Observer;

class numObservable extends Observable {
	private int data = 0;

	public int getData() {
		return data;
	}

	public void setData(int i) {
		data = i;
		setChanged();
		notifyObservers();
	}
}

class numObserver implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		numObservable myObserable = (numObservable) o;
		System.out.println("Data has changed to " + myObserable.getData());
		System.out.println("Data has changed arg " + arg);
		
	}
}

public class ObservableDemo01 {
	public static void main(String[] args) {
		// 创建一个主题对象  
        numObservable number = new numObservable();  
        // 为主题对象增加订阅者  
        number.addObserver(new numObserver());  
        // 修改主题对象  
        number.setData(1);  
        number.setData(2);  
        number.setData(3);  
	}
}
