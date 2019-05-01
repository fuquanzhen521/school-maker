package schoolSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:TeacherBuilder
 * 作用:生成teacher信息
 * 日期:5/1
 */
public class TeacherBuilder {
	public static List<Teacher> batchBuild(List<Course> courseList) {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		String x[] = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈" };
		String m[] = { "华", "夏", "雷", "锋", "好", "榜", "样", "学", "习", "好" };
		Collections.shuffle(courseList);
		for (Course course : courseList) {
			int a = (int) Math.abs(x.length * Math.random());
			int b = (int) Math.abs(m.length * Math.random());
			String name = x[a] + m[b];
			Long courseId = course.getId();
			Teacher teacher = new Teacher(name, courseId);
			teacherList.add(teacher);
		}
		return teacherList;
	}
}
