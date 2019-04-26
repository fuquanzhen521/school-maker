package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SchoolOperation {
	private static SchoolOperation instance = new SchoolOperation();

	public static SchoolOperation getInstance() {
		return instance;
	}

	private SchoolOperation() {
	}

	public boolean saveSchool(School sch) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.school(name,`985`,`211`)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sch.getName());
			stmt.setBoolean(2, sch.getJbw());
			stmt.setBoolean(3, sch.getEyy());
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

	public List<School> selectSchool() {
		List<School> schList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.school");
			while (rs.next()) {
				School sch = new School();
				sch.setId(rs.getInt("id"));
				sch.setName(rs.getString("name"));
				sch.setJbw(rs.getBoolean("985"));
				sch.setEyy(rs.getBoolean("211"));
				schList.add(sch);
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
		return schList;
	}

	public boolean updateSchool(School sch) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update aggregate.school set name=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sch.getName());
			stmt.setInt(2, sch.getId());
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

	public boolean deleteSchoolById(School sch) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.school where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, sch.getId());
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

	public boolean saveSchool2() {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.school(name,`985`,`211`)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			for (int i = 1; i <= 100; i++) {
				stmt.setString(1, "第" + i + "中学");
				int a = random.nextInt(10);
				if (a <= 4) {
					stmt.setBoolean(2, false);
					stmt.setBoolean(3, false);
				} else if (a > 4 && a <= 6) {
					stmt.setBoolean(2, false);
					stmt.setBoolean(3, true);
				} else if (a > 6 && a <= 8) {
					stmt.setBoolean(2, true);
					stmt.setBoolean(3, false);
				} else {
					stmt.setBoolean(2, true);
					stmt.setBoolean(3, true);
				}
				int f = stmt.executeUpdate();
				if (f == 1) {
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
