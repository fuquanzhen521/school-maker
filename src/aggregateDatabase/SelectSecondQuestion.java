package aggregateDatabase;

import java.util.List;

public class SelectSecondQuestion {
	public static void main(String args[]) {
		SchoolSelectOperation school = new SchoolSelectOperation();
		List<School> schList = school.selectSchool();
		System.out.println("问题:查询拥有学生最多的学校");
		for (School sch : schList) {
			System.out.println("学校id:" + sch.getId() + "\n" + "学校name:" + sch.getName());
		}
	}
}
