package newSchool.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import newSchool.classDAO.ClassDAO;
import newSchool.classInformation.ClassInformation;
import newSchool.course.Course;
import newSchool.courseBuilder.CourseBuilder;
import newSchool.courseDAO.CourseDAO;
import newSchool.decideCourse.DecideCourse;
import newSchool.decideGrade.DecideGrade;
import newSchool.decideSchool.DecideSchool;
import newSchool.elective.Elective;
import newSchool.electiveBuilder.ElectiveBuilder;
import newSchool.electiveDAO.ElectiveDAO;
import newSchool.grade.Grade;
import newSchool.gradeBuilder.GradeBuilder;
import newSchool.gradeDAO.GradeDAO;
import newSchool.school.School;
import newSchool.schoolBuilder.SchoolBuilder;
import newSchool.schoolDAO.SchoolDAO;
import newSchool.student.Student;
import newSchool.studentBuilder.StudentBuilder;
import newSchool.studentDAO.StudentDAO;
import newSchool.teacher.Teacher;
import newSchool.teacherBuilder.TeacherBuilder;
import newSchool.teacherDAO.TeacherDAO;

/*
 * 作者:付全镇
 * 类名:InsertIntoClass
 * 作用:插入数据
 * 日期:5/16
 */
public class AllInformationBuilder {

	public static void main(String args[]) {
		// build school
		SchoolDAO schoolDAO = new SchoolDAO();
		// 生成100个合肥学校信息
		List<School> buildSchoolList = SchoolBuilder.batchBuild("合肥", 100);
		// 查询合肥的学校信息
		List<Map<String, Object>> existsSchoolList = schoolDAO.selectFromSchool("合肥%");
		// 获得还需要插入的学校数据集合
		List<School> schoolList = DecideSchool.decide(buildSchoolList, existsSchoolList);
		// 执行插入,返回id
		List<Long> schoolIdList = schoolDAO.insertSchool(schoolList);
		// 遍历查询的集合existsSchoolList
		for (Map<String, Object> school : existsSchoolList) {
			// 获得id列的信息
			Object objId = school.get("id");
			// 转成long类型
			Long schoolId = Long.valueOf(String.valueOf(objId));
			schoolIdList.add(schoolId);
		}
		// 给id集合schoolIdList排序
		Collections.sort(schoolIdList);
		// build grade
		GradeDAO gradeDAO = new GradeDAO();
		// 生成4个年级信息
		List<Grade> buildGradeList = GradeBuilder.batchBuild(4);
		// 查询数据
		List<Map<String, Object>> existsGradeList = gradeDAO.selectFromGrade();
		// 获得还需要插入的年级信息的集合
		List<Grade> gradeList = DecideGrade.decide(buildGradeList, existsGradeList);
		// 执行插入,返回id
		List<Long> gradeIdList = gradeDAO.insertIntoGrade(gradeList);
		// 遍历查询的集合existsGradeList
		for (Map<String, Object> grade : existsGradeList) {
			// 获得id列信息
			Object objId = grade.get("id");
			// 转成long类型
			long gradeId = Long.valueOf(String.valueOf(objId));
			gradeIdList.add(gradeId);
		}
		// 给id集合gradeIdList排序
		Collections.sort(gradeIdList);
		// build course
		CourseDAO courseDAO = new CourseDAO();
		// 生成10个课程信息
		List<Course> batchBuildList = CourseBuilder.batchBuild(10);
		// 查询数据库里的课程信息
		List<Map<String, Object>> existsList = courseDAO.selectFromCourse();
		// 返回需要插入的课程信息
		List<Course> courseList = DecideCourse.decide(batchBuildList, existsList);
		// 查询课程信息,返回id
		List<Long> courseIdList = courseDAO.insertIntoCourse(courseList);
		// 遍历查询的集合existsList
		for (Map<String, Object> course : existsList) {
			// 取得id列的信息
			Object objId = course.get("id");
			// 转成long类型
			long courseId = Long.valueOf(String.valueOf(objId));
			// 把id存入courseIdList
			courseIdList.add(courseId);
		}
		// 给courseIdList排序
		Collections.sort(courseIdList);
		// build teacher
		TeacherDAO teacherDAO = new TeacherDAO();
		// 查询数据库里的教师信息
		List<Map<String, Object>> existsTeacherList = teacherDAO.selectFromTeacher();
		// 生成教师信息,batchBuildTeacherList里面的教师信息为需要插入的信息
		List<Teacher> batchBuildTeacherList = TeacherBuilder.batchBuild(courseIdList, existsTeacherList);
		// 插入教师信息
		List<Teacher> teacherInsertList = teacherDAO.insertIntoTeacher(batchBuildTeacherList);
		// 遍历查询的集合existsTeacherList
		for (Map<String, Object> teacher : existsTeacherList) {
			// 取得id列的信息
			Object objId = teacher.get("id");
			// 转成long类型
			long teacherId = Long.valueOf(String.valueOf(objId));
			// 取得name列信息
			Object objName = teacher.get("name");
			// 转成String类型
			String teacherName = objName.toString();
			// 取得cid列信息
			Object objCid = teacher.get("cid");
			// 转成long类型
			long cid = Long.valueOf(String.valueOf(objCid));
			// 生成一条学生信息
			Teacher teacherInfo = new Teacher(teacherId, teacherName, cid);
			// 将学生信息插入到teacherInsertList
			teacherInsertList.add(teacherInfo);
		}
		// build class
		ClassDAO classDAO = new ClassDAO();
		int f = 0;
		int q = 0;
		// 遍历学校id集合
		for (long schoolId : schoolIdList) {
			f++;
			// 遍历年级id集合
			for (long gradeId : gradeIdList) {
				q++;
				// 查询当前schoolId和gradeId下的班级信息
				List<Map<String, Object>> existsClassList = classDAO.selectFromClass(schoolId, gradeId);
				// 建一个map
				HashMap<String, Long> map = new HashMap<String, Long>();
				// 遍历查询到的existsClassList集合
				for (Map<String, Object> classInfo : existsClassList) {
					// 取得name列的信息
					Object objName = classInfo.get("name");
					// 转成String类型
					String className = objName.toString();
					// 取得id列信息
					Object objId = classInfo.get("id");
					// 转成long类型
					long classId = Long.valueOf(String.valueOf(objId));
					// name作为map的key值,id作为map的values值
					map.put(className, classId);
				}
				// 插入25个班级
				for (int i = 1; i <= 25; i++) {
					// 生成班级name
					String className = i + "班";
					// 生成一条班级信息
					ClassInformation classInformation = new ClassInformation(className, schoolId, gradeId);
					// 给classId赋初值
					long classId = 0;
					// 如果map中不包含className,执行if分支的内容
					if (!map.containsKey(className)) {
						// 执行插入操作
						classId = classDAO.insertIntoClass(classInformation);
					} else {
						classId = map.get(className);
					}
					// build student
					StudentDAO studentDAO = new StudentDAO();
					// 查询数据库里当前班级下已有的学生信息
					List<Map<String, Object>> existsStudentList = studentDAO.selectFromStudent(classId);
					// 生成学生信息,并返回需要插入的学生信息的集合
					List<Student> batchStudentBuildList = StudentBuilder.batchBuild(existsStudentList, classId,
							schoolId, gradeId);
					// 插入学生信息,并返回id
					List<Long> studentIdList = studentDAO.insertIntoStudent(batchStudentBuildList);
					// 遍历查询的学生信息集合existsStudentList
					for (Map<String, Object> student : existsStudentList) {
						// 获得id列的信息
						Object objId = student.get("id");
						// 转成long类型
						long studentId = Long.valueOf(String.valueOf(objId));
						// 把id存入studentIdList,获得班级下所有学生的id信息
						studentIdList.add(studentId);
					}
					// 给studentIdList排序
					Collections.sort(studentIdList);
					// build elective信息
					ElectiveDAO electiveDAO = new ElectiveDAO();
					int z = 0;
					// 遍历studentIdList集合
					for (long studentId : studentIdList) {
						z++;
						// 查询当前学生id下数据里的选课信息
						List<Map<String, Object>> existsElectiveList = electiveDAO.selectFromElective(studentId);
						// 获得还需要插入的选课信息集合batchBuildElectiveList
						List<Elective> batchBuildElectiveList = ElectiveBuilder.batchBuild(existsElectiveList,
								teacherInsertList, studentId);
						// 插入elective信息
						electiveDAO.insertIntoElective(batchBuildElectiveList);
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
