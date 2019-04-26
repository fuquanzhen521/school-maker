package aggregateDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;

public class GradeDTO {
	private static GradeDTO instance = new GradeDTO();

	public static GradeDTO getInstance() {
		return instance;
	}

	protected GradeDTO() {
	}

	public void insertIntoGrade() {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 4; i++) {
				stmt.setString(1, i + "年级");
				stmt.executeUpdate();
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
	}

	public ArrayList<Integer> selectIdFromGrade() {
		ArrayList<Integer> graList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id from aggregate.grade");
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

	public static void main(String args[]) {
		GradeDTO gradedto = new GradeDTO();
		gradedto.insertIntoGrade();
		System.out.println(gradedto.selectIdFromGrade());
	}
}
