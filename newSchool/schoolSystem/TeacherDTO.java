package schoolSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:TeacherDTO
 * 作用:teacher表的增删查改操作
 * 日期:5/1
 */
public class TeacherDTO {
	private static TeacherDTO instance = new TeacherDTO();

	public static TeacherDTO getInstance() {
		return instance;
	}

	protected TeacherDTO() {
	}

	/*
	 * 插入多条数据
	 */
	public void insertIntoTeacher(List<Teacher> teacherList) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.teacher(name,cid)values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Teacher teacher : teacherList) {
				stmt.setString(1, teacher.getName());
				stmt.setLong(2, teacher.getCourseId());
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
	 * 插入一条数据
	 */
	public void insertIntoTeacher(Teacher teacher) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.teacher(name,cid)values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, teacher.getName());
			stmt.setLong(2, teacher.getCourseId());
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
	public List<Teacher> selectFromTeacher() {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select * from schooldatabase.teacher";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getLong("id"));
				teacher.setName(rs.getString("name"));
				teacher.setCourseId(rs.getLong("cid"));
				teacherList.add(teacher);
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
		return teacherList;
	}

	/*
	 * 取得teacher的查询结果
	 */
	public List<Teacher> generateTeacherInfo(List<Teacher> teacherList) {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.insertIntoTeacher(teacherList);
		return teacherDTO.selectFromTeacher();
	}
}
