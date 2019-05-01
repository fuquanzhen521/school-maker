package schoolSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 作者:付全镇
 * 类名:ElectiveBuilder
 * 作用:生成elective数据
 * 日期:5/1
 */
public class ElectiveBuilder {
	public static List<Elective> batchBuild(List<Teacher> teacherList, Long studentId) {
		List<Elective> electiveList = new ArrayList<Elective>();
		Random random = new Random();
		List<Integer> pickCourseList = new ArrayList<Integer>();
		int randomCourseSize = random.nextInt(3) + 4;
		while (pickCourseList.size() < randomCourseSize) {
			int teacherIndex = random.nextInt(teacherList.size());
			if (!pickCourseList.contains(teacherIndex)) {
				Long courseId = teacherList.get(teacherIndex).getCourseId();
				Long teacherId = teacherList.get(teacherIndex).getId();
				int a = random.nextInt(10);
				if (a <= 2) {
					Elective elective = new Elective(studentId, courseId, teacherId, random.nextInt(60));
					electiveList.add(elective);
				} else if (a > 2 && a <= 7) {
					Elective elective = new Elective(studentId, courseId, teacherId, random.nextInt(20) + 60);
					electiveList.add(elective);
				} else {
					Elective elective = new Elective(studentId, courseId, teacherId, random.nextInt(20) + 80);
					electiveList.add(elective);
				}
				pickCourseList.add(teacherIndex);
			}
		}
		return electiveList;
	}
}
