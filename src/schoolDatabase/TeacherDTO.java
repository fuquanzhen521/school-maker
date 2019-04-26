package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import schoolDatabase.DatabaseConnection;

public class TeacherDTO {
	private static TeacherDTO instance = new TeacherDTO();

	public static TeacherDTO getInstance() {
		return instance;
	}

	protected TeacherDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insertIntoTeacher(List<Teacher> teacher) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.teacher(name,cid) values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Teacher tea : teacher) {
				stmt.setString(1, tea.getName());
				stmt.setInt(2, tea.getCid());
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
	public List<Teacher> selectFromTeacher() {
		List<Teacher> list = new ArrayList<Teacher>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id,cid from schooldatabase.teacher";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setCid(rs.getInt("cid"));
				list.add(teacher);
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
	 * 取得teacher信息
	 */
	public List<Teacher> teacherInformation() {
		CourseDTO courseTable = new CourseDTO();
		List<Course> courseList = courseTable.courseInformation();
		TeacherDTO teacherTable = new TeacherDTO();
		List<Teacher> teacherList = TeacherInfoRandomBuilder.batchBuild(courseList);
		teacherTable.insertIntoTeacher(teacherList);
		return teacherTable.selectFromTeacher();
	}
}
