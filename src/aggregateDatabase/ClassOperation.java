package aggregateDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aggregateDatabase.ClassOperation;
import database.DatabaseConnection;

public class ClassOperation {
	private static ClassOperation instance = new ClassOperation();

	public static ClassOperation getInstance() {
		return instance;
	}

	protected ClassOperation() {
	}

	public void insertIntoClass() {
		GradeDTO grade = new GradeDTO();
		SchoolDTO school = new SchoolDTO();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.class(name,sch_id,gid)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			grade.insertIntoGrade();
			school.insertIntoSchool("合肥");
			int f = 0;
			int q = 0;
			for (int grade_id : grade.selectIdFromGrade()) {
				q++;
				for (int s = 0; s < school.selectFromSchool().size(); s++) {
					f++;
					for (int i = 1; i <= 25; i++) {
						stmt.setString(1, i + "班");
						stmt.setInt(2, school.selectFromSchool().get(s));
						stmt.setInt(3, grade_id);
						stmt.executeUpdate();
						System.out.println("第" + f + "中学" + q + "年级" + i + "班");
					}
					if (f >= 100) {
						f = 0;
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
	}

	public ArrayList<Integer> selectIdFromClass() {
		ArrayList<Integer> claList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id from aggregate.class");
			while (rs.next()) {
				int a = rs.getInt("id");
				claList.add(a);
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

	public ArrayList<Integer> selectGidFromClass() {
		ArrayList<Integer> graList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select gid from aggregate.class");
			while (rs.next()) {
				int a = rs.getInt("gid");
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
		ClassOperation classhah = new ClassOperation();
		classhah.insertIntoClass();
		System.out.println(classhah.selectGidFromClass());
		System.out.println(classhah.selectSchidFromClass());
	}

	public ArrayList<Integer> selectSchidFromClass() {
		ArrayList<Integer> schList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select sch_id from aggregate.class");
			while (rs.next()) {
				int a = rs.getInt("sch_id");
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
}
