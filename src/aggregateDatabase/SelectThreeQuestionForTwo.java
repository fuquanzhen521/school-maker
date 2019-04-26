package aggregateDatabase;

import java.util.List;

public class SelectThreeQuestionForTwo {
	public static void main(String args[]) {
		SchoolSelectOperation school = new SchoolSelectOperation();
		List<School> schList = school.selectSchoolForThree();
		System.out.println("问题:查询选课最多的学校");
		for (School sc : schList) {
			System.out.println("学校id:" + sc.getId() + "\n" + "学校name:" + sc.getName() + "\n" + "人数:" + sc.getCount());
		}
	}
}
