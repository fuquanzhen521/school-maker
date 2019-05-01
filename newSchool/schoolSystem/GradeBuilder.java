package schoolSystem;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:GradeBuilder
 * 作用:生成grade数据
 * 日期:5/1
 */
public class GradeBuilder {
	public static Grade build(Integer i) {
		String name = buildName("第" + i + "年级");
		return new Grade(name);
	}

	public static List<Grade> batchBuild(Integer size) {
		List<Grade> gradeList = new ArrayList<Grade>();
		for (int i = 1; i <= size; i++) {
			Grade grade = build(i);
			gradeList.add(grade);
		}
		return gradeList;
	}

	private static String buildName(String name) {
		return name;
	}
}
