package aggregateDatabase;

import java.util.List;

public class SelectFirstQuestion {
	public static void main(String args[]) {
		StudentSelectOperation student = new StudentSelectOperation();
		List<Student> stuList = student.selectStudent();
		System.out.println("问题:查询学校的平均学生数");
		for (Student st : stuList) {
			System.out.println("平均学生数" + "\n" + st.getAvg());
		}
	}
}
