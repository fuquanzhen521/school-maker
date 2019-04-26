package schoolDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/*
 * 作者:付全镇
 * 类名:StudentInfoRandomBuilder
 * 日期:04/26
 * 作用:生成一组学生信息
 */
public class StudentInfoRandomBuilder {
	public static List<Student> batchBuild(List<ClassInformation> classInfo) {
		List<Student> studentList = new ArrayList<Student>();
		// 创建随机函数
		Random random = new Random();
		// 创建数组x，存放一些姓氏
		String x[] = { "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许" + "何" + "吕" + "施" + "张" };
		// 创建数组m
		String m[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
		// 创建日历类
		Calendar cal = Calendar.getInstance();
		// 设置时间
		cal.set(1990, 0, 1);
		// 返回当前时间
		long start = cal.getTimeInMillis();
		// 设置时间
		cal.set(2018, 12, 12);
		// 返回当前时间
		long end = cal.getTimeInMillis();
		int f = 1;
		int c = 0;
		int q = 1;
		int t = 0;
		int z = 0;
		for (ClassInformation cla : classInfo) {
			z++;
			int studentIndex = random.nextInt(10) + 90;
			for (int i = 0; i < studentIndex; i++) {
				int firstName = (int) Math.abs(x.length * Math.random());
				int lastName = (int) Math.abs(m.length * Math.random());
				String name = x[firstName] + m[lastName];
				Date birthday = new Date(start + (long) (random.nextDouble() * (end - start)));
				byte sex = (byte) (random.nextInt(2) + 1);
				int cla_id = cla.getId();
				int sch_id = cla.getSch_id();
				int gra_id = cla.getGra_id();
				Student student = new Student(name, birthday, sex, sch_id, gra_id, cla_id);
				studentList.add(student);
				System.out.println("第" + f + "中学" + q + "年级" + z + "班" + "第" + i + "个学生");
			}
			c++;
			t++;
			if (c % 25 == 0) {
				f++;
			}
			if (f > 100) {
				f = 1;
			}
			if (t % 2500 == 0) {
				q++;
			}
			if (z >= 25) {
				z = 0;
			}
		}
		return studentList;
	}
}
