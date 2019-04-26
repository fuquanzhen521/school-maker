package schoolDatabase;

public class School {
	private int id;
	private String name;
	private boolean jbw;
	private boolean eyy;

	public School() {
	}

	public School(String name, boolean jbw, boolean eyy) {
		this.name = name;
		this.jbw = jbw;
		this.eyy = eyy;
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

	public boolean isJbw() {
		return jbw;
	}

	public void setJbw(boolean jbw) {
		this.jbw = jbw;
	}

	public boolean isEyy() {
		return eyy;
	}

	public void setEyy(boolean eyy) {
		this.eyy = eyy;
	}
}
