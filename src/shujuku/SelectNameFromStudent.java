package shujuku;

import java.util.List;

public class SelectNameFromStudent {
	public static void main(String args[]) {
		List<Student> stuList = StudentOperation.getInstance().selectStudent2();
		System.out.println("没学过李三老师课程的学生姓名");
		for (Student st : stuList) {
			System.out.println(st.getSname());
		}
	}
}
