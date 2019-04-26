package hellotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//作者:付全镇
//日期:04/15

//类名:GradeDTO
//作用:向grade表插入和查询数据
public class GradeDTO {
	private static GradeDTO instance = new GradeDTO();

	public static GradeDTO getInstance() {
		return instance;
	}

	protected GradeDTO() {
	}

	// 插入数据
	public void insertGrade() {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into hellomyschool.grade(name) values (?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 4; i++) {
				stmt.setString(1, "第" + i + "年级");
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

	// 查询数据
	public List<Integer> selectGrade() {
		List<Integer> graList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id from hellomyschool.grade";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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
}
