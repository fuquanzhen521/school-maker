package shujuku;

import java.util.List;

public class Updatetest {
	public static void main(String args[]) {
		List<Course> excList = CourseOperation.getInstance().selectCourse();
		System.out.println("课程ID");
		for (Course cre : excList) {
			System.out.println(cre.getCId());
		}
		Course cre = new Course();
		cre.setCId(2);
		cre.setCname("数据库系统管理");
		boolean res = CourseOperation.getInstance().updateCourse(cre);
		if (res = true) {
			System.out.println("课程ID为2的课程名称修改成功");
		} else {
			System.out.println("课程ID为2的课程名称修改失败");
		}
	}
}
