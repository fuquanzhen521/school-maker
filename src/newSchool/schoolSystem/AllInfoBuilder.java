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
 * 浣滆��:浠樺叏闀�
 * 绫诲悕:AllInfoBuilder
 * 浣滅敤:鎻掑叆鏁版嵁
 * 鏃ユ湡:5/1
 */
public class AllInfoBuilder {
	public static void main(String args[]) throws SQLException {
		
		// generate school
		List<School> schoolList = SchoolBuilder.batchBuild("鍚堣偉", 100);
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
				// 鑾峰緱褰撳墠瀛︽牎鍜屽勾绾х殑鐝骇淇℃伅
				List<ClassInformation> allClassList = ClassDAO.selectFromClass(schoolId, gradeId);
				// 浠ョ彮绾у鍚嶄綔涓簁ey鍊�,鐝骇淇℃伅浣滀负values鍊�,瀛樺叆map闆嗗悎涓�
				for (ClassInformation classInformation : allClassList) {
					String name = classInformation.getName();
					map.put(name, classInformation);
				}
				for (int i = 1; i <= 25; i++) {
					String className = i + "鐝�";
					ClassInformation classInfo = new ClassInformation(className, schoolId, gradeId);
					long classId = ClassDAO.insertIntoClass(map, classInfo);
					// generate student
					// 鏌ヨ褰撳墠鐝骇涓嬫暟鎹簱涓凡缁忓瓨鍦ㄧ殑鎵�鏈夊鐢熺殑id
					List<Student> studentsList = StudentDAO.selectFromStudent(classId);
					List<Student> studentList = StudentBuilder.batchBuild(studentsList, classId, schoolId, gradeId);
					List<Long> studentIdList = StudentDAO.insertIntoStudent(studentList);
					// generate elective
					List<Long> allStudentIdList = new ArrayList<Long>();
					// 閬嶅巻studentsList鐨勫��,骞跺彇鍑哄畠鐨刬d鍊�,瀛樺叆allStudentIdList闆嗗悎涓�
					for (Student student : studentsList) {
						long studentId = student.getId();
						allStudentIdList.add(studentId);
					}
					// 鎶妔tudentIdList闆嗗悎涓殑鍏冪礌瀛樺叆鍒癮llStudentIdList闆嗗悎涓�,杩欐牱灏辫幏寰椾簡涓�涓彮鎵�鏈夊鐢熺殑id
					for (long studentId : studentIdList) {
						allStudentIdList.add(studentId);
					}
					int z = 0;
					for (long studentId : allStudentIdList) {
						z++;
						// 鏌ヨ褰撳墠瀛︾敓id涓嬪鐢熺殑閫夎淇℃伅
						List<Elective> electivesList = ElectiveDAO.selectFromElective(studentId);
						List<Elective> electiveList = ElectiveBuilder.batchBuild(electivesList, teacherList, studentId);
						ElectiveDAO.insertIntoElective(electiveList);
						System.out.println("绗�" + f + "涓" + "绗�" + q + "骞寸骇" + i + "鐝�" + "绗�" + z + "涓鐢熺殑淇℃伅");
					}
				}
			}
			if (q == 4) {
				q = 0;
			}
		}
	}
}
