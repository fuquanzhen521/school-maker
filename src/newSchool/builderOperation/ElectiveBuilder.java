package newSchool.builderOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import newSchool.encapsulationInformation.Elective;
import newSchool.encapsulationInformation.Teacher;

/*
 * 作者:付全镇
 * 类名:ElectiveBuilder
 * 作用:生成elective数据
 * 日期:5/1
 */
public class ElectiveBuilder {

	/*
	 * 生成elective数据
	 */
	public static List<Elective> batchBuild(List<Elective> electiveSelectList, List<Teacher> teacherList, long studentId) {
		List<Elective> addMoreElectiveList = new ArrayList<Elective>();
		List<Long> compareList = new ArrayList<Long>();
		for (Elective elective : electiveSelectList) {
			long courseId = elective.getCourseId();
			compareList.add(courseId);
		}
		Random random = new Random();
		// 随机取4到6门课程
		int randomCourseSize = random.nextInt(3) + 4;
		// 判断随机取得课程数是否大于数据库中查询的课程数
		if (randomCourseSize > electiveSelectList.size()) {
			// index为还需要插入的选修课数
			int index = randomCourseSize - electiveSelectList.size();
			while (addMoreElectiveList.size() < index) {
				// 从teacherList中随机取一个数
				int teacherIndex = random.nextInt(teacherList.size());
				// 取得该下标位置的课程id
				long courseId = teacherList.get(teacherIndex).getCourseId();
				// 取得该下标位置的教师id
				long teacherId = teacherList.get(teacherIndex).getId();
				if (!compareList.contains(courseId)) {
					// 随机取十个数
					int a = random.nextInt(10);
					// 当小于等于2时,成绩在60以下;大于2小于等于7时，成绩在60至80之间;大于7时,成绩在80以上
					if (a <= 2) {
						Elective elective = new Elective(studentId, courseId, teacherId, random.nextInt(60));
						addMoreElectiveList.add(elective);
					} else if (a > 2 && a <= 7) {
						Elective elective = new Elective(studentId, courseId, teacherId, random.nextInt(20) + 60);
						addMoreElectiveList.add(elective);
					} else {
						Elective elective = new Elective(studentId, courseId, teacherId, random.nextInt(20) + 80);
						addMoreElectiveList.add(elective);
					}
				}
			}
		}
		return addMoreElectiveList;
	}

}
