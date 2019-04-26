package schoolDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

/*作者:付全镇
    日期:04/15
    类名:DatabaseConnection
    作用:连接数据库*/
public class DatabaseConnection {
	private static Connection conn = null;

	public static Connection getCon() {
		try {
			/* 加载驱动程序 */
			Class.forName("com.mysql.jdbc.Driver");
			/* MySQL配置时的用户名 */
			String user = "root";
			/* MySQL配置时的密码 */
			String password = "root";
			/* url指向要访问的数据库名 */
			String url = "jdbc:mysql://localhost:3306/schooldatabase?verifyServerCertificate=false&autoReconnect=true";
			/* getConnection()方法，连接MySQL数据库 */
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
		}
		return conn;
	}
}
