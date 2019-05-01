package schoolSystem;

/*
 * 作者:付全镇
 * 类名:Teacher
 * 作用:封装属性
 * 日期:5/1
 */
public class Teacher {
	private Long id;
	private String name;
	private Long courseId;

	public Teacher() {
	}

	public Teacher(String name, Long courseId) {
		this.name = name;
		this.courseId = courseId;
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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
