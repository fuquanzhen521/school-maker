package schoolSystem;

/*
 * 作者:付全镇
 * 类名:Elective
 * 作用:封装属性
 * 日期:5/1
 */
public class Elective {
	private Long studentId;
	private Long courseId;
	private Long teacherId;
	private float score;

	public Elective() {
	}

	public Elective(Long studentId, Long courseId, Long teacherId, float score) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.teacherId = teacherId;
		this.score = score;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}
