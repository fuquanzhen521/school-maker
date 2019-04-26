package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Gradelist {
	private static Gradelist instance = new Gradelist();

	public static Gradelist getInstance() {
		return instance;
	}

	public Gradelist() {
	}

	public ArrayList<Integer> grade() {
		ArrayList<Integer> graList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 4; i++) {
				stmt.setString(1, i + "年级");
				stmt.executeUpdate();
			}
			Statement stmts = conn.createStatement();
			ResultSet rs = stmts.executeQuery("select id from aggregate.grade");
			while (rs.next()) {
				int a = rs.getInt("id");
				graList.add(a);
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
	public static void main(String args[]){
		Gradelist gra=new Gradelist();
		System.out.println(gra.grade());
	}
}
