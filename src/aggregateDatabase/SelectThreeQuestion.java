package aggregateDatabase;

import java.util.List;

public class SelectThreeQuestion {
	public static void main(String args[]) {
		SchoolSelectOperation school = new SchoolSelectOperation();
		List<School> schList = school.selectSchoolForSecond();
		System.out.println("问题:查询选课最多的学校");
		for (School sch : schList) {
			System.out.println("学校id:" + sch.getId() + "\n" + "学校name:" + sch.getName());
		}
	}
}
