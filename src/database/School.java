package database;

public class School {
	private int id;
	private String name;
	private boolean jbw;
	private boolean eyy;

	public boolean getJbw() {
		return jbw;
	}

	public void setJbw(boolean jbw) {
		this.jbw = jbw;
	}

	public boolean getEyy() {
		return eyy;
	}

	public void setEyy(boolean eyy) {
		this.eyy = eyy;
	}

	public School() {
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
}
