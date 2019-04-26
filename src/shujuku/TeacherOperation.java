package shujuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherOperation {
	private static TeacherOperation instance = new TeacherOperation();

	public static TeacherOperation getInstance() {
		return instance;
	}

	private TeacherOperation() {
	}

	public boolean saveTeacher(Teacher tea) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sqlInsert = "insert into question.teacher(TId,Tname)values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setInt(1, tea.getTId());
			stmt.setString(2, tea.getTname());
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

	public List<Teacher> selectTeacher() {
		List<Teacher> teaList = new ArrayList<Teacher>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from question.teacher");
			while (rs.next()) {
				Teacher tea = new Teacher();
				tea.setTId(rs.getInt("TId"));
				tea.setTname(rs.getString("Tname"));
				teaList.add(tea);
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
		return teaList;
	}

	public List<Teacher> selectTeacher2() {
		List<Teacher> teaList = new ArrayList<Teacher>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select count(*) from question.teacher where Tname like '李%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Teacher tea = new Teacher();
				tea.setCount(rs.getInt("count(*)"));
				teaList.add(tea);
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
		return teaList;
	}

	public boolean updateTeacher(Teacher tea) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update question.teacher set Tname=? where TId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tea.getTname());
			stmt.setInt(2, tea.getTId());
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

	public boolean deleteTeacherById(Teacher tea) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from question.teacher where TId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tea.getTId());
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
