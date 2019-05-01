package schoolSystem;

/*
 * 作者:付全镇
 * 类名:School
 * 作用:封装属性
 * 日期:5/1
 */
public class School {
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
