package newSchool.daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import newSchool.encapsulationInformation.Student;
import newSchool.schoolSystem.DatabaseConnection;

/*
 * 作者:付全镇
 * 类名:StudentDAO
 * 作用:student表的增删查改操作
 * 日期:5/1
 */
public class StudentDAO {
	private static StudentDAO instance = new StudentDAO();

	public static StudentDAO getInstance() {
		return instance;
	}

	protected StudentDAO() {
	}

	/*
	 * 插入多条数据
	 */
	public static List<Long> insertIntoStudent(List<Student> studentList) {
		List<Long> studentIdList = new ArrayList<Long>();
		for (Student student : studentList) {
			long studentId = insertIntoStudent(student);
			studentIdList.add(studentId);
		}
		return studentIdList;
	}

	/*
	 * 插入一条数据
	 */
	public static long insertIntoStudent(Student student) {
		Connection conn = null;
		long id = 0;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.student(name,birthday,sex,sch_id,gra_id,cla_id)values(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, student.getName());
			stmt.setDate(2, student.getBirthday());
			stmt.setByte(3, student.getSex());
			stmt.setLong(4, student.getSchoolId());
			stmt.setLong(5, student.getGradeId());
			stmt.setLong(6, student.getClassId());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
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
		return id;
	}

	/*
	 * 查询数据
	 */
	public static List<Student> selectFromStudent(long classId) {
		Connection conn = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			conn = DatabaseConnection.getCon();
			String sql = " select student.* from student,class " + "where student.cla_id=class.id and class.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, classId);
			ResultSet rs = stmt.executeQuery();
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

}
