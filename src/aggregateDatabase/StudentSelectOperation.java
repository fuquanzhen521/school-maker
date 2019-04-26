package aggregateDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aggregateDatabase.DatabaseConnection;
import aggregateDatabase.Student;

public class StudentSelectOperation {
	public List<Student> selectStudent() {
		List<Student> stuList = new ArrayList<Student>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(id)/count(distinct sch_id) from student");
			while (rs.next()) {
				Student stu = new Student();
				stu.setAvg(rs.getFloat("count(id)/count(distinct sch_id)"));
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

	public List<Student> selectStudentTwo() {
		List<Student> stuList = new ArrayList<Student>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select avg(r.a)from (select count(id)as a from student group by student.sch_id)as r";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setAvg(rs.getFloat("avg(r.a)"));
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

	public List<Student> selectStudentFour() {
		List<Student> stuList = new ArrayList<Student>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select sch_id,gid,count(id) from student " + "group by sch_id,gid order by count(id) "
					+ "desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setSch_id(rs.getInt("sch_id"));
				stu.setGid(rs.getInt("gid"));
				stu.setCount(rs.getInt("count(id)"));
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
}
