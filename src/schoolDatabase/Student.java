package schoolDatabase;

import java.sql.Date;

public class Student {
	private int id;
	private String name;
	private Date birthday;
	private byte sex;
	private int sch_id;
	private int gra_id;
	private int cla_id;

	public Student() {
	}

	public Student(String name, Date birthday, byte sex, int sch_id, int gra_id, int cla_id) {
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.sch_id = sch_id;
		this.gra_id = gra_id;
		this.cla_id = cla_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public int getSch_id() {
		return sch_id;
	}

	public void setSch_id(int sch_id) {
		this.sch_id = sch_id;
	}

	public int getGra_id() {
		return gra_id;
	}

	public void setGra_id(int gra_id) {
		this.gra_id = gra_id;
	}

	public int getCla_id() {
		return cla_id;
	}

	public void setCla_id(int cla_id) {
		this.cla_id = cla_id;
	}
}
