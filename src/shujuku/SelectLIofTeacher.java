package shujuku;

import java.util.List;

public class SelectLIofTeacher {
	public static void main(String args[]) {
		List<Teacher> teaList = TeacherOperation.getInstance().selectTeacher2();
		System.out.println("「李」姓老师的数量:");
		for (Teacher t : teaList) {
			System.out.println(t.getCount());
		}
	}
}