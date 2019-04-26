package schoolDatabase;

import java.util.List;

public class InsertIntoStudent {
	public static void main(String args[]) {
		ClassDTO classTable = new ClassDTO();
		List<ClassInformation> classList = classTable.classInfo();
		// 构建学生信息
		List<Student> studentList = StudentInfoRandomBuilder.batchBuild(classList);
		// 保存学生信息
		StudentDTO studentTable = new StudentDTO();
		studentTable.insertIntoStudent(studentList);
		System.out.println("Success save student info!");
	}
}
