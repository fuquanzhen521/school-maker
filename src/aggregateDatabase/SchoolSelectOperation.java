package aggregateDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchoolSelectOperation {
	private static SchoolSelectOperation instance = new SchoolSelectOperation();

	public static SchoolSelectOperation getInstance() {
		return instance;
	}

	public SchoolSelectOperation() {
	}

	public List<School> selectSchool() {
		List<School> schList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = " select school.id,school.name from school right join" + "(select student.sch_id from student"
					+ " group by student.sch_id order by count(id)" + " desc limit 1)as r" + " on school.id=r.sch_id";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				School sch = new School();
				sch.setId(rs.getInt("school.id"));
				sch.setName(rs.getString("school.name"));
				schList.add(sch);
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

	public List<School> selectSchoolForSecond() {
		List<School> schList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select school.id,name from school right join"
					+ "(select sch_id,id,count(a) from student right join" + "(select sid,count(cid)as a from elective"
					+ " group by sid order by count(cid)" + " desc)as r on student.id=r.sid"
					+ " group by sch_id order by count(a)" + " desc limit 1)as r on school.id=r.sch_id";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				School sch = new School();
				sch.setId(rs.getInt("school.id"));
				sch.setName(rs.getString("name"));
				schList.add(sch);
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

	public List<School> selectSchoolForThree() {
		List<School> schList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select r.sch_id,school.name,count(r.a) from "
					+ "(select student.sch_id,count(elective.cid)as a " + "from elective,student "
					+ "where student.id=elective.sid " + "group by elective.sid)as r,school "
					+ "where r.sch_id=school.id " + "group by r.sch_id " + "order by count(r.a) desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				School sch = new School();
				sch.setId(rs.getInt("r.sch_id"));
				sch.setName(rs.getString("name"));
				sch.setCount(rs.getInt("count(r.a)"));
				schList.add(sch);
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
