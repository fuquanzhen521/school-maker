package schoolSystem;

import java.sql.SQLException;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:AllInfoBuilder
 * 作用:插入表数据
 * 日期:5/1
 */
public class AllInfoBuilder {
	public static void main(String args[]) throws SQLException {
		// generate school
		SchoolDTO schoolDTO = new SchoolDTO();
		List<School> schoolList = schoolDTO.generateSchoolInfo("合肥", 100);
		// generate grade
		GradeDTO gradeDTO = new GradeDTO();
		List<Grade> gradeList = gradeDTO.generateGradeInfo(4);
		// generate course
		CourseDTO courseDTO = new CourseDTO();
		List<Course> courseList = courseDTO.generateCourseInfo(10);
		// generate teacher
		TeacherDTO teacherDTO = new TeacherDTO();
		List<Teacher> teachersList = TeacherBuilder.batchBuild(courseList);
		List<Teacher> teacherList = teacherDTO.generateTeacherInfo(teachersList);
		// generate class
		ClassDTO classDTO = new ClassDTO();
		StudentDTO studentDTO = new StudentDTO();
		ElectiveDTO electiveDTO = new ElectiveDTO();
		int f = 0;
		int q = 0;
		for (School school : schoolList) {
			f++;
			for (Grade grade : gradeList) {
				q++;
				for (int i = 1; i <= 25; i++) {
					String className = i + "班";
					Long schoolId = school.getId();
					Long gradeId = grade.getId();
					ClassInformation classInfo = new ClassInformation(className, schoolId, gradeId);
					Long classId = classDTO.generateClassInfo(classInfo);
					// generate student
					List<Student> studentsList = StudentBuilder.batchBuild(classId, schoolId, gradeId);
					long[] studentId = studentDTO.generateStudentInfo(studentsList);
					// generate elective
					int z = 0;
					for (long sid : studentId) {
						z++;
						List<Elective> electiveList = ElectiveBuilder.batchBuild(teacherList, sid);
						electiveDTO.insertIntoElective(electiveList);
						System.out.println("第" + f + "中学" + "第" + q + "年级" + i + "班" + "第" + z + "个学生的选课信息");

					}
				}
			}
		}
	}
}
