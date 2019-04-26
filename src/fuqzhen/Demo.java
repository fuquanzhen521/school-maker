package fuqzhen;

import java.sql.*;

public class Demo {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/question";
	private static final String user = "root";
	private static final String password = "root";

	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, user, password);
			System.out.println("实例化statement对象...");
			stmt = conn.createStatement();
			String sql;
			sql = "select count(*) from teacher where Tname like '李%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int count = rs.getInt("count(*)");
				System.out.print("count(*): " + count);
				System.out.println("\n");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}
}