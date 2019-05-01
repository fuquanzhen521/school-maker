package schoolSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/*
 * 作者:付全镇
 * 类名:StudentDTO
 * 作用:student表的增删查改操作
 * 日期:5/1
 */
public class StudentDTO {
	private static StudentDTO instance = new StudentDTO();

	public static StudentDTO getInstance() {
		return instance;
	}

	protected StudentDTO() {
	}

	/*
	 * 插入多条数据
	 */
	public long[] insertIntoStudent(List<Student> studentList) throws SQLException {
		Connection conn = DatabaseConnection.getCon();
		int i = 0;
		int rowCount = studentList.size();
		long[] keys = new long[rowCount];
		String[] columnNames = { "id" };
		String sql = "insert into schooldatabase.student(name,birthday,sex,sch_id,gra_id,cla_id)values(?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql, columnNames);
		Student student = null;
		for (int r = 0; r < rowCount; r++) {
			student = studentList.get(r);
			stmt.setString(1, student.getName());
			stmt.setDate(2, student.getBirthday());
			stmt.setByte(3, student.getSex());
			stmt.setLong(4, student.getSchoolId());
			stmt.setLong(5, student.getGradeId());
			stmt.setLong(6, student.getClassId());
			stmt.addBatch();
		}
		stmt.executeBatch();
		ResultSet rs = stmt.getGeneratedKeys();
		while (rs.next() && i <= rowCount) {
			keys[i] = rs.getLong(1);
			i++;
		}
		return keys;
	}

	/*
	 * 插入一条数据
	 */
	public void insertIntoStudent(Student student) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.student(name,birthday,sex,sch_id,gra_id,cla_id)values(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getName());
			stmt.setDate(2, student.getBirthday());
			stmt.setByte(3, student.getSex());
			stmt.setLong(4, student.getSchoolId());
			stmt.setLong(5, student.getGradeId());
			stmt.setLong(6, student.getClassId());
			stmt.executeUpdate();
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
		Connection conn = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select * from schooldatabase.student";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
				student.setBirthday(rs.getDate("birthday"));
				student.setSex(rs.getByte("sex"));
				student.setSchoolId(rs.getLong("sch_id"));
				student.setGradeId(rs.getLong("gra_id"));
				student.setClassId(rs.getLong("cla_id"));
				studentList.add(student);
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
		return studentList;
	}

	/*
	 * 取得student的id
	 */
	public long[] generateStudentInfo(List<Student> studentList) throws SQLException {
		StudentDTO studentDTO = new StudentDTO();
		return studentDTO.insertIntoStudent(studentList);
	}
}
