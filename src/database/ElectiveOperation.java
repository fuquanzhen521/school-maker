package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElectiveOperation {
	private static ElectiveOperation instance = new ElectiveOperation();

	public static ElectiveOperation getInstance() {
		return instance;
	}

	private ElectiveOperation() {
	}

	public boolean saveElective(Elective ele) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.elective(sid,cid,tid,score)values(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ele.getSid());
			stmt.setInt(2, ele.getCid());
			stmt.setInt(3, ele.getTid());
			stmt.setInt(4, ele.getScore());
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

	public List<Elective> selectElective() {
		List<Elective> eleList = new ArrayList<Elective>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.elective");
			while (rs.next()) {
				Elective ele = new Elective();
				ele.setSid(rs.getInt("sid"));
				ele.setCid(rs.getInt("cid"));
				ele.setTid(rs.getInt("tid"));
				ele.setScore(rs.getInt("score"));
				eleList.add(ele);
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
		return eleList;
	}

	public boolean updateElective(Elective ele) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update aggregate.elective set score=? where sid=? and cid=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ele.getScore());
			stmt.setInt(2, ele.getSid());
			stmt.setInt(3, ele.getCid());
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

	public boolean deleteElectiveById(Elective ele) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.elective where sid=? and cid=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ele.getSid());
			stmt.setInt(2, ele.getCid());
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

	public int saveElective2() {
		int row = 0;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.elective(sid,cid,tid,score)values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			ArrayList<Integer> list = new ArrayList<Integer>();
			ArrayList<Integer> lists = new ArrayList<Integer>();
			ArrayList<Integer> listtwo = new ArrayList<Integer>();
			ArrayList<Integer> listthree = new ArrayList<Integer>();
			for (int i = 1; i <= 25; i++) {
				list.add(i);
			}
			for (int i = 1; i <= 4; i++) {
				listtwo.add(i);
			}
			for (int i = 1; i <= 100; i++) {
				listthree.add(i);
			}
			for (int s : listthree) {
				for (int s2 : listtwo) {
					for (int s3 : list) {
						int h = random.nextInt(20) + 90;
						for (int i = 1; i <= h; i++) {
							int r = 0;
							while (r < 4) {
								int t = random.nextInt(10) + 1;
								if (!list.contains(t)) {
									list.add(t);
									stmt.setInt(2, list.get(i));
								}
							}
						}
					}
				}
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
