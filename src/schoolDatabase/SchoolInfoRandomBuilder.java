package schoolDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 作者:付全镇
 * 类名:SchoolInfoRandomBuilder
 * 日期:04/26
 * 作用:生成一组学校信息
 */
public class SchoolInfoRandomBuilder {
	public static School build(String prefixName, Integer i) {
		String name = buildName(prefixName + "第" + i + "中学");
		Random random = new Random();

		int s = random.nextInt(10);
		if (s <= 4) {
			return new School(name, false, false);

		} else if (s > 4 && s <= 6) {
			return new School(name, true, false);

		} else if (s > 6 && s <= 8) {
			return new School(name, false, true);

		} else {
			return new School(name, true, true);
		}
	}

	public static List<School> batchBuild(String prefixName, int size) {
		List<School> schList = new ArrayList<School>();
		for (int i = 1; i <= size; i++) {
			School school = build(prefixName, i);
			schList.add(school);
		}
		return schList;
	}

	private static String buildName(String name) {
		return name;
	}

}
