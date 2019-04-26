package shujuku;

import java.sql.Date;

public class Student {
	private int SId;
	private String Sname;
	private Date Sage;
	private String Ssex;

	public Student() {
	}

	public int getSId() {
		return SId;
	}

	public void setSId(int sId) {
		SId = sId;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public Date getSage() {
		return Sage;
	}

	public void setSage(Date sage) {
		Sage = sage;
	}

	public String getSsex() {
		return Ssex;
	}

	public void setSsex(String ssex) {
		Ssex = ssex;
	}
}
