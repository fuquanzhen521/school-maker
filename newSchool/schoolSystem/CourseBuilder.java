package schoolSystem;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:CourseBuilder
 * 作用:生成course数据
 * 日期:5/1
 */
public class CourseBuilder {
	public static Course build(Integer i) {
		String name = buildName("第" + i + "课程");
		return new Course(name);
	}

	public static List<Course> batchBuild(Integer size) {
		List<Course> courseList = new ArrayList<Course>();
		for (int i = 1; i <= size; i++) {
			Course course = build(i);
			courseList.add(course);
		}
		return courseList;
	}

	private static String buildName(String name) {
		return name;
	}
}
