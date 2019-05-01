package schoolSystem;

/*
 * 作者:付全镇
 * 类名:Course
 * 作用:封装属性
 * 日期:5/1
 */
public class Course {
	private Long id;
	private String name;

	public Course() {
	}

	public Course(String name) {
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
