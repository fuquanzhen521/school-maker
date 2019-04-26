package schoolDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:TeacherInfoRandomBuilder
 * 日期:04/26
 * 作用:生成教师信息
 */
public class TeacherInfoRandomBuilder {
	public static List<Teacher> batchBuild(List<Course> course) {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		String x[] = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈" };
		String m[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
		Collections.shuffle(course);
		for (Course cou : course) {
			int firstName = (int) Math.abs(x.length * Math.random());
			int lastName = (int) Math.abs(m.length * Math.random());
			String name = x[firstName] + m[lastName];
			Teacher teacher = new Teacher(name, cou.getId());
			teacherList.add(teacher);
		}
		return teacherList;
	}
}
