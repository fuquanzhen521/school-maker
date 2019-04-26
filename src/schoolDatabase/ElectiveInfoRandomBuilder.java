package schoolDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 作者:付全镇
 * 类名:ElectiveInfoRandomBuilder
 * 日期:04/26
 * 作用:生成选课信息
 */

public class ElectiveInfoRandomBuilder {

	public static List<Elective> batchBuild(List<Student> student, List<Teacher> teacher) throws SQLException {
		List<Elective> electiveList = new ArrayList<Elective>();
		Random random = new Random();
		int c = 0;
		for (Student stu : student) {
			List<Integer> pickCourseList = new ArrayList<Integer>();
			int randomCourseSize = random.nextInt(3) + 4;
			while (pickCourseList.size() < randomCourseSize) {
				int teacherIndex = random.nextInt(teacher.size());
				if (!pickCourseList.contains(teacherIndex)) {
					int sid = stu.getId();
					int cid = teacher.get(teacherIndex).getCid();
					int tid = teacher.get(teacherIndex).getId();
					int a = random.nextInt(10);
					if (a <= 2) {
						Elective elective = new Elective(sid, cid, tid, random.nextInt(60));
						electiveList.add(elective);
					} else if (a > 2 && a <= 7) {
						Elective elective = new Elective(sid, cid, tid, random.nextInt(20) + 60);
						electiveList.add(elective);
					} else {
						Elective elective = new Elective(sid, cid, tid, random.nextInt(20) + 80);
						electiveList.add(elective);
					}
					pickCourseList.add(teacherIndex);
				}
			}
			c++;
			if (c % 1000 == 0) {
				System.out.println("第" + c + "个学生选课信息");
			}
		}
		return electiveList;
	}
}
