package chaptor02.javaconfig;

public class SgtPeppers implements CompactDisc{
	
	private String title = "She";
	private String artist = "披头士";
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("SgtPeppers play title : " + title + " , artist : " + artist);
		
	}

}
