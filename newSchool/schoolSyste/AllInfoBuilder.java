package schoolSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import builderOperation.CourseBuilder;
import builderOperation.ElectiveBuilder;
import builderOperation.GradeBuilder;
import builderOperation.SchoolBuilder;
import builderOperation.StudentBuilder;
import builderOperation.TeacherBuilder;
import daoOperation.ClassDAO;
import daoOperation.CourseDAO;
import daoOperation.ElectiveDAO;
import daoOperation.GradeDAO;
import daoOperation.SchoolDAO;
import daoOperation.StudentDAO;
import daoOperation.TeacherDAO;
import encapsulationInformation.ClassInformation;
import encapsulationInformation.Course;
import encapsulationInformation.Elective;
import encapsulationInformation.Grade;
import encapsulationInformation.School;
import encapsulationInformation.Student;
import encapsulationInformation.Teacher;

/*
 * 作者:付全镇
 * 类名:AllInfoBuilder
 * 作用:插入数据
 * 日期:5/1
 */
public class AllInfoBuilder {
	public static void main(String args[]) throws SQLException {
		// generate school
		List<School> schoolList = SchoolBuilder.batchBuild("合肥", 100);
		List<Long> schoolIdList = SchoolDAO.insertIntoSchool(schoolList);
		// generate grade
		List<Grade> gradeList = GradeBuilder.batchBuild(4);
		List<Long> gradeIdList = GradeDAO.insertIntoGrade(gradeList);
		// generate course
		List<Course> courseList = CourseBuilder.batchBuild(10);
		List<Long> courseIdList = CourseDAO.insertIntoCourse(courseList);
		// generate teacher
		List<Teacher> teachersList = TeacherBuilder.batchBuild(courseIdList);
		List<Teacher> teacherList = TeacherDAO.insertIntoTeacher(teachersList);
		// generate class
		HashMap<String, ClassInformation> map = new HashMap<String, ClassInformation>();
		int f = 0;
		int q = 0;
		for (long schoolId : schoolIdList) {
			f++;
			for (long gradeId : gradeIdList) {
				q++;
				// 获得当前学校和年级的班级信息
				List<ClassInformation> allClassList = ClassDAO.selectFromClass(schoolId, gradeId);
				// 以班级姓名作为key值,班级信息作为values值,存入map集合中
				for (ClassInformation classInformation : allClassList) {
					String name = classInformation.getName();
					map.put(name, classInformation);
				}
				for (int i = 1; i <= 25; i++) {
					String className = i + "班";
					ClassInformation classInfo = new ClassInformation(className, schoolId, gradeId);
					long classId = ClassDAO.insertIntoClass(map, classInfo);
					// generate student
					// 查询当前班级下数据库中已经存在的所有学生的id
					List<Student> studentsList = StudentDAO.selectFromStudent(classId);
					List<Student> studentList = StudentBuilder.batchBuild(studentsList, classId, schoolId, gradeId);
					List<Long> studentIdList = StudentDAO.insertIntoStudent(studentList);
					// generate elective
					List<Long> allStudentIdList = new ArrayList<Long>();
					// 遍历studentsList的值,并取出它的id值,存入allStudentIdList集合中
					for (Student student : studentsList) {
						long studentId = student.getId();
						allStudentIdList.add(studentId);
					}
					// 把studentIdList集合中的元素存入到allStudentIdList集合中,这样就获得了一个班所有学生的id
					for (long studentId : studentIdList) {
						allStudentIdList.add(studentId);
					}
					int z = 0;
					for (long studentId : allStudentIdList) {
						z++;
						// 查询当前学生id下学生的选课信息
						List<Elective> electivesList = ElectiveDAO.selectFromElective(studentId);
						List<Elective> electiveList = ElectiveBuilder.batchBuild(electivesList, teacherList, studentId);
						ElectiveDAO.insertIntoElective(electiveList);
						System.out.println("第" + f + "中学" + "第" + q + "年级" + i + "班" + "第" + z + "个学生的信息");
					}
				}
			}
			if (q == 4) {
				q = 0;
			}
		}
	}
}
