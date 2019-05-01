package schoolSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 作者:付全镇
 * 类名:SchoolBuilder
 * 作用:生成school信息
 * 日期:5/1
 */
public class SchoolBuilder {
	public static School build(String schoolName, Integer i) {
		String name = buildName(schoolName + "第" + i + "中学");
		Random random = new Random();
		int r = random.nextInt(10);
		if (r >= 0 && r < 6) {
			return new School(name, false, false);
		} else if (r >= 6 && r < 8) {
			return new School(name, true, false);
		} else if (r >= 8 && r < 10) {
			return new School(name, false, true);
		} else {
			return new School(name, true, true);
		}
	}

	public static List<School> batchBuild(String schoolName, Integer size) {
		List<School> schoolList = new ArrayList<School>();
		for (int i = 1; i <= size; i++) {
			School school = build(schoolName, i);
			schoolList.add(school);
		}
		return schoolList;
	}

	private static String buildName(String name) {
		return name;
	}
}
