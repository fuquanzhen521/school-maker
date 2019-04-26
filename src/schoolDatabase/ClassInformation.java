package schoolDatabase;

public class ClassInformation {
	private int id;
	private String name;
	private int sch_id;
	private int gra_id;

	public ClassInformation() {
	}

	public ClassInformation(String name, int sch_id, int gra_id) {
		this.name = name;
		this.sch_id = sch_id;
		this.gra_id = gra_id;
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
}
