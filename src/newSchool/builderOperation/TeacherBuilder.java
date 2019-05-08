package newSchool.builderOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import newSchool.encapsulationInformation.Teacher;

/*
 * 作者:付全镇
 * 类名:TeacherBuilder
 * 作用:生成teacher信息
 * 日期:5/1
 */
public class TeacherBuilder {

	/*
	 * 生成teacher信息
	 */
	public static List<Teacher> batchBuild(List<Long> courseList) {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		// 存入一些姓氏数组元素
		String x[] = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈" };
		// 存入一些名字数组元素
		String m[] = { "华", "夏", "雷", "锋", "好", "榜", "样", "学", "习", "好" };
		// 打乱courseList集合的顺序
		Collections.shuffle(courseList);
		for (long courseId : courseList) {
			// 随机取得数组x的下标值
			int a = (int) Math.abs(x.length * Math.random());
			// 随机取得数组m的下标值
			int b = (int) Math.abs(m.length * Math.random());
			// 取得姓名
			String name = x[a] + m[b];
			Teacher teacher = new Teacher(name, courseId);
			teacherList.add(teacher);
		}
		return teacherList;
	}
}
