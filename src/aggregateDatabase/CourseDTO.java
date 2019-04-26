package aggregateDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;

public class CourseDTO {
	private static CourseDTO instance = new CourseDTO();

	public static CourseDTO getInstance() {
		return instance;
	}

	protected CourseDTO() {
	}

	public void insertIntoCourse() {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.course(name) values(?)";
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

	public ArrayList<Integer> selectFromCourse() {
		ArrayList<Integer> creList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id from aggregate.course");
			while (rs.next()) {
				int a = rs.getInt("id");
				creList.add(a);
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
		return creList;
	}
}
