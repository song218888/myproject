package chaptor02.autoconfig;

import org.springframework.stereotype.Component;

//CD实现类
/**
 * @Compent，表示该类是一个组件类，需要spring为这个类创建bean
 * @author DELL
 *
 */
@Component
public class SgtPeppers implements CompactDisc{
	
	private String title = "She";
	private String artist = "披头士";
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("SgtPeppers play title : " + title + " , artist : " + artist);
		
	}

}
