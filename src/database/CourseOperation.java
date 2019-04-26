package database;

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

	public boolean saveCourse(Course cse) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.course(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cse.getName());
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
		List<Course> cseList = new ArrayList<Course>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.course");
			while (rs.next()) {
				Course cse = new Course();
				cse.setId(rs.getInt("id"));
				cse.setName(rs.getString("name"));
				cseList.add(cse);
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
		return cseList;
	}

	public boolean updateCourse(Course cse) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update aggregate.course set name=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cse.getName());
			stmt.setInt(2, cse.getId());
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

	public boolean deleteCourseById(Course cse) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.course where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cse.getId());
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

	public boolean saveCourse2() {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.course(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 10; i++) {
				stmt.setString(1, "第" + i + "课程");
				int a = stmt.executeUpdate();
				if (a == 1) {
					result = true;
				}
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
