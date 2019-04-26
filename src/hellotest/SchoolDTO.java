package hellotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * 作者:付全镇
    日期:04/15
    类名:SchoolDTO
    作用:向school表插入和查询数据
 */
public class SchoolDTO {
	private static SchoolDTO instance = new SchoolDTO();

	public static SchoolDTO getInstance() {
		return instance;
	}

	protected SchoolDTO() {
	}

	/* 
	 * 插入数据
	 */
	public void insertIntoSchool(String schoolName) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into hellomyschool.school(name,`985`,`211`) values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			for (int i = 1; i <= 100; i++) {
				stmt.setString(1, schoolName + "第" + i + "中学");
				/*
				 *  随机取0-9之间的一个数
				 */
				int r = random.nextInt(10);
				if (r <= 5) {
					stmt.setBoolean(2, false);
					stmt.setBoolean(3, false);
				} else if (r > 5 && r <= 7) {
					stmt.setBoolean(2, true);
					stmt.setBoolean(3, false);
				} else if (r > 7 && r <= 9) {
					stmt.setBoolean(2, false);
					stmt.setBoolean(3, true);
				} else {
					stmt.setBoolean(2, true);
					stmt.setBoolean(3, true);
				}
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

	/* 
	 * 查询数据
	 */
	public List<Integer> selectSchool() {
		List<Integer> schList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id from hellomyschool.school";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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
}
