package schoolSystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/*
 * 作者:付全镇
 * 类名:StudentBuilder
 * 作用:生成student信息
 * 日期:5/1
 */
public class StudentBuilder {
	public static List<Student> batchBuild(Long classId, Long schoolId, Long gradeId) {
		List<Student> studentList = new ArrayList<Student>();
		String x[] = { "孙", "刘", "曹", "关", "张", "赵", "黄", "郭", "吕", "诸葛", "马", "魏", "杨", "付", "王", "吴", "任", "李", "司徒",
				"顾", "易", "万", "马", "谢", "毛", "周" };
		String m[] = { "权", "备", "操", "羽", "飞", "云", "忠", "嘉", "布", "亮", "超", "延", "广", "强", "阳", "广", "静", "龙", "末",
				"未", "川", "可", "珂", "倩", "东", "来" };
		Calendar cal = Calendar.getInstance();
		cal.set(1990, 0, 1);
		long start = cal.getTimeInMillis();
		cal.set(2002, 12, 12);
		long end = cal.getTimeInMillis();
		Random random = new Random();
		int studentIndex = random.nextInt(10) + 90;
		for (int i = 0; i < studentIndex; i++) {
			int a = (int) Math.abs(x.length * Math.random());
			int b = (int) Math.abs(m.length * Math.random());
			String studentName = x[a] + m[b];
			Date birthday = new Date(start + (long) (random.nextDouble() * (end - start)));
			Byte sex = (byte) (random.nextInt(2) + 1);
			Student student = new Student(studentName, birthday, sex, schoolId, gradeId, classId);
			studentList.add(student);
		}
		return studentList;
	}
}
