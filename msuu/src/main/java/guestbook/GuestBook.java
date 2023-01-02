package guestbook;

import java.sql.Date;

public class GuestBook {
	private int GB_ID;
	private String GB_NAME;
	private String GB_EMAIL;
	private String GB_PASSWD;
	private String GB_TEL;
	private Date GB_DATE;
	private String GB_CONTENTS;
	
	
	public int getGB_ID() {
		return GB_ID;
	}


	public void setGB_ID(int gB_ID) {
		GB_ID = gB_ID;
	}


	public String getGB_NAME() {
		return GB_NAME;
	}


	public void setGB_NAME(String gB_NAME) {
		GB_NAME = gB_NAME;
	}


	public String getGB_EMAIL() {
		return GB_EMAIL;
	}


	public void setGB_EMAIL(String gB_EMAIL) {
		GB_EMAIL = gB_EMAIL;
	}


	public String getGB_PASSWD() {
		return GB_PASSWD;
	}


	public void setGB_PASSWD(String gB_PASSWD) {
		GB_PASSWD = gB_PASSWD;
	}


	public String getGB_TEL() {
		return GB_TEL;
	}


	public void setGB_TEL(String gB_TEL) {
		GB_TEL = gB_TEL;
	}


	public Date getGB_DATE() {
		return GB_DATE;
	}


	public void setGB_DATE(Date gB_DATE) {
		GB_DATE = gB_DATE;
	}


	public String getGB_CONTENTS() {
		return GB_CONTENTS;
	}


	public void setGB_CONTENTS(String gB_CONTENTS) {
		GB_CONTENTS = gB_CONTENTS;
	}


	public GuestBook() {
		// TODO Auto-generated constructor stub
	}

}
