package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassExcelOperation {
	private static ClassExcelOperation instance = new ClassExcelOperation();

	public static ClassExcelOperation getInstance() {
		return instance;
	}

	private ClassExcelOperation() {
	}

	public boolean saveClassExcel(ClassTable cla) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.class(name,sch_id,gid)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cla.getName());
			stmt.setInt(2, cla.getSch_id());
			stmt.setInt(3, cla.getGid());
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

	public List<ClassTable> selectClassExcel() {
		List<ClassTable> claList = new ArrayList<ClassTable>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.class");
			while (rs.next()) {
				ClassTable cla = new ClassTable();
				cla.setId(rs.getInt("id"));
				cla.setName(rs.getString("name"));
				cla.setSch_id(rs.getInt("sch_id"));
				cla.setGid(rs.getInt("gid"));
				claList.add(cla);
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
		return claList;
	}

	public boolean updateClassExcel(ClassTable cla) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update aggregate.class set name=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cla.getName());
			stmt.setInt(2, cla.getId());
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

	public boolean deleteClassExcelById(ClassTable cla) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.class where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cla.getId());
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

	public boolean saveClassExcel2() {
		Gradelist grade = new Gradelist();
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.class(name,sch_id,gid)values(?,?,?)";
			String sql2 = "insert into aggregate.school(name,`985`,`211`)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			Random random = new Random();
			for (int i = 1; i <= 100; i++) {
				stmt2.setString(1, "第" + i + "中学");
				int a = random.nextInt(10);
				if (a <= 4) {
					stmt2.setBoolean(2, false);
					stmt2.setBoolean(3, false);
				} else if (a > 4 && a <= 6) {
					stmt2.setBoolean(2, false);
					stmt2.setBoolean(3, true);
				} else if (a > 6 && a <= 8) {
					stmt2.setBoolean(2, true);
					stmt2.setBoolean(3, false);
				} else {
					stmt2.setBoolean(2, true);
					stmt2.setBoolean(3, true);
				}
				stmt2.executeUpdate();
			}
			ArrayList<Integer> schList = new ArrayList<Integer>();
			ResultSet rs = stmt2.executeQuery("select id from aggregate.school");
			while (rs.next()) {
				int a = rs.getInt("id");
				schList.add(a);
			}
			for (int s3 : grade.grade()) {
				for (int s = 0; s < schList.size(); s++) {
					for (int i = 1; i <= 25; i++) {
						stmt.setString(1, i + "班");
						stmt.setInt(2, schList.get(s));
						stmt.setInt(3, s3);
						int a = stmt.executeUpdate();
						if (a == 1) {
							result = true;
						}
					}
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
