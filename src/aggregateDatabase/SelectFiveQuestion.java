package aggregateDatabase;

import java.util.List;

public class SelectFiveQuestion {
	public static void main(String args[]) {
		ElectiveSelectOperation elective = new ElectiveSelectOperation();
		List<Elective> eleList = elective.selectElective();
		System.out.println("问题:查询学生们最喜欢选修的课程是哪个");
		for (Elective ele : eleList) {
			System.out.println("课程id:" + ele.getCid());
		}
	}
}
