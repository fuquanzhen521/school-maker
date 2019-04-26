package schoolDatabase;

public class Teacher {
	private int id;
	private String name;
	private int cid;

	public Teacher() {
	}

	public Teacher(String name, int cid) {
		this.name = name;
		this.cid = cid;
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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
}
