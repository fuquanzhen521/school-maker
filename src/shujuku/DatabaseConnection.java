package shujuku;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static Connection conn = null;

	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "root";
			String url = "jdbc:mysql://localhost:3306/question";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
		}
		return conn;
	}
}
