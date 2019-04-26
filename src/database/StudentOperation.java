package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class StudentOperation {
	private static StudentOperation instance = new StudentOperation();

	public static StudentOperation getInstance() {
		return instance;
	}

	private StudentOperation() {
	}

	public boolean saveStudent(Student stu) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.student(name,birthday,sex,sch_id,gid,cla_id)values(?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, stu.getName());
			stmt.setDate(2, stu.getBirthday());
			stmt.setByte(3, stu.getSex());
			stmt.setInt(4, stu.getSch_id());
			stmt.setInt(5, stu.getGid());
			stmt.setInt(6, stu.getCla_id());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
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

	public List<Student> selectStudent() {
		List<Student> stuList = new ArrayList<Student>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.student");
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setBirthday(rs.getDate("brithday"));
				stu.setSex(rs.getByte("sex"));
				stu.setSch_id(rs.getInt("sch_id"));
				stu.setGid(rs.getInt("gid"));
				stu.setCla_id(rs.getInt("cla_id"));
				stuList.add(stu);
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

	public boolean updateStudent(Student stu) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update aggregate.student set name=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, stu.getName());
			stmt.setInt(2, stu.getId());
			int flag = stmt.executeUpdate();
			if (flag == 1) {
				result = true;
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

	public boolean deleteStudent(Student stu) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from aggregate.student where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, stu.getId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = true;
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

	public boolean saveStudent2() {
		Gradelist grade=new Gradelist();
		boolean result=false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.class(name,sch_id,gid)values(?,?,?)";
			String sql2 = "insert into aggregate.school(name,`985`,`211`)values(?,?,?)";
			String sql3="insert into aggregate.student(name,birthday,sex,sch_id,gid,cla_id)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			Random random = new Random();
			for (int i = 1; i <= 100; i++) {
				stmt2.setString(1, "第" + i + "中学");
				int a = random.nextInt(10);
				if (a <= 4) {
					stmt2.setBoolean(2, false);
					stmt2.setBoolean(3, false);
				} else if (a > 4 && a <= 6) {
					stmt2.setBoolean(2, false);
					stmt2.setBoolean(3, true);
				} else if (a > 6 && a <= 8) {
					stmt2.setBoolean(2, true);
					stmt2.setBoolean(3, false);
				} else {
					stmt2.setBoolean(2, true);
					stmt2.setBoolean(3, true);
				}
				stmt2.executeUpdate();
			}
			ArrayList<Integer> schList = new ArrayList<Integer>();
			ResultSet rs = stmt2.executeQuery("select id from aggregate.school");
			while (rs.next()) {
				int a = rs.getInt("id");
				schList.add(a);
			}
			for (int s3 : grade.grade()) {
				for (int s = 0; s < schList.size(); s++) {
					for (int i = 1; i <= 25; i++) {
						stmt.setString(1, i + "班");
						stmt.setInt(2, schList.get(s));
						stmt.setInt(3, s3);
						int a = stmt.executeUpdate();
						if (a == 1) {
							result = true;
						}
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
		return result;
	}
}
