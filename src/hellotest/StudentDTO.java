package hellotest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/*
 * 作者:付全镇
 * 日期:04/15
 * 类名:StudentDTO
 * 作用:向student表中插入和查询数据
 */
public class StudentDTO {
	private static StudentDTO instance = new StudentDTO();

	public StudentDTO getInstance() {
		return instance;
	}

	protected StudentDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insetStudent() {
		Connection conn = null;
		ClassDTO classtable = new ClassDTO();
		try {
			conn = DatabaseConnection.getCon();
			// 创建sql语句
			String sql = "insert into hellomyschool.student(name,sex,birthday,grade_id,school_id,class_id)values(?,?,?,?,?,?)";
			// 创建PreparedStatement类，用来执行sql语句
			PreparedStatement stmt = conn.prepareStatement(sql);
			// 创建随机函数
			Random random = new Random();
			// 创建数组x，存放一些姓氏
			String x[] = { "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许" + "何" + "吕" + "施" + "张" };
			// 创建数组m
			String m[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
			// 创建日历类
			Calendar cal = Calendar.getInstance();
			// 设置时间
			cal.set(1990, 0, 1);
			// 返回当前时间
			long start = cal.getTimeInMillis();
			// 设置时间
			cal.set(2018, 12, 12);
			// 返回当前时间
			long end = cal.getTimeInMillis();
			// 插入class表
			classtable.insertClass();
			// claList集合存入学生id
			List<Integer> claList = classtable.selectClass().getClassList();
			// graList集合存入年级id
			List<Integer> graList = classtable.selectClass().getGradeList();
			// schList集合存入学校id
			List<Integer> schList = classtable.selectClass().getSchoolList();
			int f = 1;
			int c = 0;
			int q = 1;
			int t = 0;
			int z = 0;
			for (int s = 0; s < claList.size(); s++) {
				z++;
				int studentIndex = random.nextInt(10) + 90;
				for (int i = 0; i < studentIndex; i++) {
					int a = (int) Math.abs(x.length * Math.random());
					int b = (int) Math.abs(m.length * Math.random());
					// 插入姓名
					stmt.setString(1, x[a] + m[b]);
					// 插入性别
					stmt.setByte(2, (byte) (random.nextInt(2) + 1));
					Date d = new Date(start + (long) (random.nextDouble() * (end - start)));
					// 插入生日
					stmt.setDate(3, d);
					// 插入年级id
					stmt.setInt(4, graList.get(s));
					// 插入学校id
					stmt.setInt(5, schList.get(s));
					// 插入班级id
					stmt.setInt(6, claList.get(s));
					stmt.executeUpdate();
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
	}

	/*
	 * 查询数据
	 */
	public List<Integer> selectStudent() {
		Connection conn = null;
		List<Integer> stuList = new ArrayList<Integer>();
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select id from hellomyschool.student";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int sid = rs.getInt("id");
				stuList.add(sid);
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
