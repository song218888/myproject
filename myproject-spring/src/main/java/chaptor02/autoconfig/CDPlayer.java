package chaptor02.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CDPlayer implements MediaPlayer{
	
	@Autowired
	CompactDisc cd;

	@Override
	public void play() {
		// TODO Auto-generated method stub
		cd.play();
	}

}
