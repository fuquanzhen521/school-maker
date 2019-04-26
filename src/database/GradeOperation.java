package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeOperation {
	private static GradeOperation instance = new GradeOperation();

	public static GradeOperation getInstance() {
		return instance;
	}

	private GradeOperation() {
	}

	public boolean saveGrade(Grade gra) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, gra.getName());
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

	public List<Grade> selectGrade() {
		List<Grade> graList = new ArrayList<Grade>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.grade");
			while (rs.next()) {
				Grade gra = new Grade();
				gra.setId(rs.getInt("id"));
				gra.setName(rs.getString("name"));
				graList.add(gra);
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
		return graList;
	}

	public boolean updateSchool(Grade gra) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update aggregate.grade set name=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, gra.getName());
			stmt.setInt(2, gra.getId());
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

	public boolean deleteGradeById(Grade gra) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.grade where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gra.getId());
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

	public boolean saveGrade2() {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 4; i++) {
				stmt.setString(1, i + "年级");
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
