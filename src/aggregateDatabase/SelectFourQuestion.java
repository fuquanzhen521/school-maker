package aggregateDatabase;

import java.util.List;

public class SelectFourQuestion {
	public static void main(String args[]) {
		StudentSelectOperation student = new StudentSelectOperation();
		List<Student> stuList = student.selectStudentFour();
		System.out.println("查询哪个学校的哪个年级拥有最多的学生");
		for (Student st : stuList) {
			System.out
					.println("学校id:" + st.getSch_id() + "\n" + "年级id:" + st.getGid() + "\n" + "学生人数:" + st.getCount());
		}
	}
}
