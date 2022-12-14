package park.yt;

public class AppVO {
	private String Name;
	
	@Override
	public String toString() {
		return "AppVO [Name=" + Name + "]";
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public AppVO(String name) {
		super();
		Name = name;
	}
	public AppVO() {
		
	}




}
