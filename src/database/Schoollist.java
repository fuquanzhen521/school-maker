package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Schoollist {
	private static Schoollist instance = new Schoollist();

	public static Schoollist getInstance() {
		return instance;
	}

	public Schoollist() {
	}

	public ArrayList<Integer> school() {
		ArrayList<Integer> schList = new ArrayList<Integer>();
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
				stmt.executeUpdate();
			}
			ResultSet rs = stmt.executeQuery("select id from aggregate.school");
			while (rs.next()) {
				int a = rs.getInt("id");
				schList.add(a);
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
	public static void main(String args[]){
			Schoollist sch=new Schoollist();
		System.out.println(sch.school());
	}
}
