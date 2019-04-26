package aggregateDatabase;

import java.util.List;

public class SelectFromSchool {
	public static void main(String args[]) {
		SelectOfSchool selectSchool = new SelectOfSchool();
		List<School> schList = selectSchool.selectSchool();
		for (School school : schList) {
			System.out.println("id:" + school.getId() + "\t" + "name:" + school.getName() + "\t" + "是否是985:"
					+ school.getJbw() + "\t" + "是否是211:" + school.getEyy() + "\n");
		}
	}
}
