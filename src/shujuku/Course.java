package shujuku;

public class Course {
	private Integer CId;
	private String Cname;
	private Integer TId;

	public Course() {
	}

	public Integer getCId() {
		return CId;
	}

	public void setCId(Integer cId) {
		CId = cId;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public Integer getTId() {
		return TId;
	}

	public void setTId(Integer tId) {
		TId = tId;
	}
}
