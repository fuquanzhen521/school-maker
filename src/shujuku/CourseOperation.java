package shujuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseOperation {
	private static CourseOperation instance = new CourseOperation();

	public static CourseOperation getInstance() {
		return instance;
	}

	private CourseOperation() {
	}

	public boolean saveCourse(Course exc) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sqlInsert = "insert into question.course(CId,Cname,TId)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setInt(1, exc.getCId());
			stmt.setString(2, exc.getCname());
			stmt.setInt(3, exc.getTId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Course> selectCourse() {
		List<Course> creList = new ArrayList<Course>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from question.course");
			while (rs.next()) {
				Course exc = new Course();
				exc.setCId(rs.getInt("CId"));
				exc.setCname(rs.getString("Cname"));
				exc.setTId(rs.getInt("TId"));
				creList.add(exc);
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

	public boolean updateCourse(Course exc) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update question.course set Cname=? where CId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, exc.getCname());
			stmt.setInt(2, exc.getCId());
			int flag = stmt.executeUpdate();
			if (flag == 1) {
				result = true;
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
		return result;
	}

	public boolean deleteCourseById(Course exc) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from question.course where CId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, exc.getCId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = true;
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
		return result;
	}
}
