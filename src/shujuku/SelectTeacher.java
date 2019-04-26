package shujuku;

import java.util.List;

public class SelectTeacher {
	public static void main(String args[]) {
		List<Teacher> teaList = TeacherOperation.getInstance().selectTeacher();
		System.out.println("教师ID\t教师姓名");
		for (Teacher tea : teaList) {
			System.out.println(tea.getTId() + "\t" + tea.getTname());
		}
	}
}
