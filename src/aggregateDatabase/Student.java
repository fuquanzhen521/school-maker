package aggregateDatabase;

import java.sql.Date;

public class Student {
	private int id;
	private String name;
	private Date birthday;
	private byte sex;
	private int sch_id;
	private int gid;
	private int cla_id;
	private float avg;
	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private float sum;
	private int count;

	public Student() {
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

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getCla_id() {
		return cla_id;
	}

	public void setCla_id(int cla_id) {
		this.cla_id = cla_id;
	}
}
