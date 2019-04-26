package hellotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//作者:付全镇
//日期:04/15

//类名:CourseDTO
//作用:向course表里插入和查询数据
public class CourseDTO {
	private static CourseDTO instance = new CourseDTO();

	public static CourseDTO getInstance() {
		return instance;
	}

	protected CourseDTO() {
	}

	// 插入数据
	public void insertCourse() {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into hellomyschool.course (name) values (?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 10; i++) {
				stmt.setString(1, "第" + i + "课程");
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

	// 查询数据
	public List<Integer> selectCourse() {
		Connection conn = null;
		List<Integer> courseList = new ArrayList<Integer>();
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select id from hellomyschool.course";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int a = rs.getInt("id");
				courseList.add(a);
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
		return courseList;
	}
}
