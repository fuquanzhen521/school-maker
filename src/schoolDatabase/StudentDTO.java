package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
	private static StudentDTO instance = new StudentDTO();

	public static StudentDTO getInstance() {
		return instance;
	}

	protected StudentDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insertIntoStudent(List<Student> student) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.student(name,birthday,sex,sch_id,gra_id,cla_id) values(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Student stu : student) {
				stmt.setString(1, stu.getName());
				stmt.setDate(2, stu.getBirthday());
				stmt.setByte(3, stu.getSex());
				stmt.setInt(4, stu.getSch_id());
				stmt.setInt(5, stu.getGra_id());
				stmt.setInt(6, stu.getCla_id());
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 查询数据
	 */
	public List<Student> selectFromStudent() {
		List<Student> list = new ArrayList<Student>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id from schooldatabase.student";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				list.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/*
	 * 取得student表信息
	 */
	public List<Student> studentInformation() {
		ClassDTO classTable = new ClassDTO();
		List<ClassInformation> classList = classTable.classInfo();
		List<Student> studentList = StudentInfoRandomBuilder.batchBuild(classList);
		StudentDTO studentTable = new StudentDTO();
		studentTable.insertIntoStudent(studentList);
		return studentTable.selectFromStudent();
	}
}
