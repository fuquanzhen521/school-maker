package schoolDatabase;

public class Elective {
	private int id;
	private int sid;
	private int cid;
	private int tid;
	private int score;

	public Elective() {
	}

	public Elective(int sid, int cid, int tid, int score) {
		this.sid = sid;
		this.cid = cid;
		this.tid = tid;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
