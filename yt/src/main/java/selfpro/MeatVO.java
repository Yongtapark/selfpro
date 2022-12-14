package selfpro;

public class MeatVO {
	private String kor_cow;
	private String cow;
	private String pig;
	private String chick;
	private String egg;
	
	public  MeatVO() {
		
	}

	public MeatVO(String kor_cow, String cow, String pig, String chick, String egg) {
		super();
		this.kor_cow = kor_cow;
		this.cow = cow;
		this.pig = pig;
		this.chick = chick;
		this.egg = egg;
	}

	
	
	public String getKor_cow() {
		return kor_cow;
	}

	public void setKor_cow(String kor_cow) {
		this.kor_cow = kor_cow;
	}

	public String getCow() {
		return cow;
	}

	public void setCow(String cow) {
		this.cow = cow;
	}

	public String getPig() {
		return pig;
	}

	public void setPig(String pig) {
		this.pig = pig;
	}

	public String getChick() {
		return chick;
	}

	public void setChick(String chick) {
		this.chick = chick;
	}

	public String getEgg() {
		return egg;
	}

	public void setEgg(String egg) {
		this.egg = egg;
	}

	@Override
	public String toString() {
		return "MeatVO [kor_cow=" + kor_cow + ", cow=" + cow + ", pig=" + pig + ", chick=" + chick + ", egg=" + egg
				+ "]";
	}
	
	
	
	
	
	

}
