package shujuku;

import java.util.List;

public class SelectTest {
	public static void main(String args[]) {
		List<Course> creList = CourseOperation.getInstance().selectCourse();
		System.out.println("课程ID\t课程名称\t教师ID");
		for (Course cre : creList) {
			System.out.println(cre.getCId() + "\t" + cre.getCname() + "\t" + cre.getTId());
		}
	}
}
