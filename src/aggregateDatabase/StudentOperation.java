package aggregateDatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import database.DatabaseConnection;

public class StudentOperation {
	private static StudentOperation instance = new StudentOperation();

	public static StudentOperation getInstance() {
		return instance;
	}

	public StudentOperation() {
	}

	public boolean insertIntoStudent() {
		ClassOperation classoperation = new ClassOperation();
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.student(name,birthday,sex,sch_id,gid,cla_id)values(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			String[] x = { "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许" + "何" + "吕" + "施" + "张" };
			String[] m = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
			Calendar cal = Calendar.getInstance();
			cal.set(1990, 0, 1);
			long start = cal.getTimeInMillis();
			cal.set(2019, 4, 3);
			long end = cal.getTimeInMillis();
			classoperation.insertIntoClass();
			ArrayList<Integer> claList = classoperation.selectIdFromClass();
			ArrayList<Integer> class_gid = classoperation.selectGidFromClass();
			ArrayList<Integer> schid_class = classoperation.selectSchidFromClass();
			int f = 1;
			int c = 0;
			int q = 1;
			int t = 0;
			int z = 0;
			for (int s = 0; s < claList.size(); s++) {
				z++;
				int studentIndex = random.nextInt(10) + 90;
				for (int i = 1; i <= studentIndex; i++) {
					int a = (int) Math.abs(x.length * Math.random());
					int b = (int) Math.abs(m.length * Math.random());
					stmt.setString(1, x[a] + m[b]);
					Date d = new Date(start + (long) (random.nextDouble() * (end - start)));
					stmt.setDate(2, d);
					stmt.setByte(3, (byte) (random.nextInt(2) + 1));
					stmt.setInt(4, schid_class.get(s));
					stmt.setInt(5, class_gid.get(s));
					stmt.setInt(6, claList.get(s));
					int h = stmt.executeUpdate();
					if (h == 1) {
						result = true;
					}
					System.out.println("第" + f + "中学" + q + "年级" + z + "班" + "第" + i + "个学生");
				}
				c++;
				t++;
				if (c % 25 == 0) {
					f++;
				}
				if (f > 100) {
					f = 1;
				}
				if (t % 2500 == 0) {
					q++;
				}
				if (z >= 25) {
					z = 0;
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
		return result;
	}

	public ArrayList<Integer> selectFromStudent() {
		ArrayList<Integer> stuList = new ArrayList<Integer>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id from aggregate.student");
			while (rs.next()) {
				int a = rs.getInt("id");
				stuList.add(a);
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
		return stuList;
	}
}
