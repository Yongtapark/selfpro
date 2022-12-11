package DB_to_JAVA;

public class SolVO {

	private String USER_NAME; // 이름
	private String USER_CAM; // 캠을 켰는지 안켰는지

	// 기본 생성자
	public SolVO() {

	}

	// 생성자
	public SolVO(String USER_NAME, String USER_CAM) {
		this.USER_NAME = USER_NAME;
		this.USER_CAM = USER_CAM;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String USER_NAME) {
		USER_NAME = USER_NAME;
	}

	public String getUSER_CAM() {
		return USER_CAM;
	}

	public void setUSER_CAM(String USER_CAM) {
		USER_CAM = USER_CAM;
	}

	@Override
	public String toString() {
		return "SolVO [USER_NAME=" + USER_NAME + ", USER_CAM=" + USER_CAM + "]";
	}

}
