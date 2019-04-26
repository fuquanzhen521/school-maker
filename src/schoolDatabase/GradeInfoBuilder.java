package schoolDatabase;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:GradeInfoBuilder
 * 日期:04/26
 * 作用:生成年级信息
 */
public class GradeInfoBuilder {
	public static Grade build(Integer i) {
		String name = buildName("第" + i + "年级");
		return new Grade(name);
	}

	public static List<Grade> batchBuild(int size) {
		List<Grade> graList = new ArrayList<Grade>();
		for (int i = 1; i <= size; i++) {
			Grade grade = build(i);
			graList.add(grade);
		}
		return graList;
	}

	private static String buildName(String name) {
		return name;
	}
}
