package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
			String sql = "insert into aggregate.teacher(name,cid)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			stmt.setString(1, tea.getName());
			stmt.setInt(2, random.nextInt(10) + 1);
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
			ResultSet rs = stmt.executeQuery("select * from aggregate.teacher");
			while (rs.next()) {
				Teacher tea = new Teacher();
				tea.setId(rs.getInt("id"));
				tea.setName(rs.getString("name"));
				tea.setCid(rs.getInt("cid"));
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
			String sql = "update aggregate.teacher set name=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tea.getName());
			stmt.setInt(2, tea.getId());
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

	public boolean deleteTeacher(Teacher tea) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.teacher where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tea.getId());
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

	public int saveTeacher2() {
		int row = 0;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.teacher(name,cid)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			ArrayList<Integer> list = new ArrayList<Integer>();
			int s;
			while (list.size() < 10) {
				s = random.nextInt(10) + 1;
				if (!list.contains(s)) {
					list.add(s);
				}
			}
			String[] x = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈" };
			String[] m = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
			for (int i = 0; i < 10; i++) {
				int a = (int) Math.abs(x.length * Math.random());
				int b = (int) Math.abs(m.length * Math.random());
				stmt.setString(1, x[a] + m[b]);
				stmt.setInt(2, list.get(i));
				stmt.addBatch();
			}
			int[] rows = stmt.executeBatch();
			row = rows.length;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
