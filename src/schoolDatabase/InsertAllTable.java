package schoolDatabase;

import java.sql.SQLException;
import java.util.List;

public class InsertAllTable {
	public static void main(String args[]) throws SQLException {
		StudentDTO studentTable = new StudentDTO();
		List<Student> studentList = studentTable.studentInformation();
		TeacherDTO teacherTable = new TeacherDTO();
		List<Teacher> teacherList = teacherTable.teacherInformation();
		List<Elective> electiveList = ElectiveInfoRandomBuilder.batchBuild(studentList, teacherList);
		ElectiveDTO electiveTable = new ElectiveDTO();
		electiveTable.insertIntoElective(electiveList);
		System.out.println("Successful1");
	}
}
