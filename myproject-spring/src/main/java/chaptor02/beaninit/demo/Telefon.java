package chaptor02.beaninit.demo;

public class Telefon {
	private String marka;
	private String model;
	private int fiyat;
	
	public Telefon() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Telefon toString()【marka: "+marka+" model: "+model+" fiyat: "+fiyat+"】";
	}
	
	@SuppressWarnings("unused")
	private void baslat() {
		System.out.println("初始化方法");

	}
	
	@SuppressWarnings("unused")
	private void bitir() {
		System.out.println("销毁方法");

}
}
