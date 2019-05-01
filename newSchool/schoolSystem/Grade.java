package schoolSystem;

/*
 * 作者:付全镇
 * 类名:Grade
 * 作用:封装属性
 * 日期:5/1
 */
public class Grade {
	private Long id;
	private String name;

	public Grade() {
	}

	public Grade(String name) {
		this.name = name;
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
}
