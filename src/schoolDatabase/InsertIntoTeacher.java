package schoolDatabase;

import java.util.List;

public class InsertIntoTeacher {
	public static void main(String args[]){
		CourseDTO courseTable = new CourseDTO();
		List<Course> courseList = courseTable.courseInformation();
		TeacherDTO teacherTable = new TeacherDTO();
		// 构建 教师信息
		List<Teacher> teacherList = TeacherInfoRandomBuilder.batchBuild(courseList);
		// 保存教师信息
		teacherTable.insertIntoTeacher(teacherList);
		System.out.println("Success save 10 teacher info!");
	}
}
