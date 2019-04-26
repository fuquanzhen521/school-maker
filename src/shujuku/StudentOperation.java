package shujuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentOperation {
	private static StudentOperation instance = new StudentOperation();

	public static StudentOperation getInstance() {
		return instance;
	}

	private StudentOperation() {
	}

	public boolean saveStudent(Student st) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sqlInsert = "insert into question.student(SId,Sname,Sage,Ssex) values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setInt(1, st.getSId());
			stmt.setString(2, st.getSname());
			stmt.setDate(3, st.getSage());
			stmt.setString(4, st.getSsex());
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
			ResultSet rs = stmt.executeQuery("select * from question.student");
			while (rs.next()) {
				Student st = new Student();
				st.setSId(rs.getInt("SId"));
				st.setSname(rs.getString("Sname"));
				st.setSage(rs.getDate("Sage"));
				st.setSsex(rs.getString("Ssex"));
				stuList.add(st);
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

	public boolean updateStudent(Student st) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update question.student set Sname=? where SId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, st.getSname());
			stmt.setInt(2, st.getSId());
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

	public boolean deleteStudentById(Student st) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from question.student where SId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, st.getSId());
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

	public List<Student> selectStudent2() {
		List<Student> stuList = new ArrayList<Student>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select Sname from student" + "where student.SId not in (select score.SId from score,teacher,course"
					+ " where score.CId=course.CId" + "and course.TId=teacher.TId" + "and teacher.Tname='张三')";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student st = new Student();
				st.setSname(rs.getString("Sname"));
				st.setSId(rs.getInt("SId"));
				stuList.add(st);
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
