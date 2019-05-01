package schoolSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:SchoolDTO
 * 作用:school表的增删查改操作
 * 日期:5/1
 */
public class SchoolDTO {
	private static SchoolDTO instance = new SchoolDTO();

	public static SchoolDTO getInstance() {
		return instance;
	}

	protected SchoolDTO() {
	}

	/*
	 * 插入多条信息
	 */
	public void InsertIntoSchool(List<School> schoolList) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.school(name,`985`,`211`)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (School school : schoolList) {
				stmt.setString(1, school.getName());
				stmt.setBoolean(2, school.isJbw());
				stmt.setBoolean(3, school.isEyy());
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
	 * 插入一条信息
	 */
	public void InsertIntoSchool(School school) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.school(name,`985`,`211`)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, school.getName());
			stmt.setBoolean(2, school.isJbw());
			stmt.setBoolean(3, school.isEyy());
			stmt.executeUpdate();
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
	public List<School> selectFromSchool() {
		List<School> schoolList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select * from schooldatabase.school";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				School school = new School();
				school.setId(rs.getLong("id"));
				school.setName(rs.getString("name"));
				school.setJbw(rs.getBoolean("985"));
				school.setEyy(rs.getBoolean("211"));
				schoolList.add(school);
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
		return schoolList;
	}

	/*
	 * 取得school查询结果的集合
	 */
	public List<School> generateSchoolInfo(String schoolName, Integer i) {
		SchoolDTO schoolDTO = new SchoolDTO();
		List<School> studentList = SchoolBuilder.batchBuild(schoolName, i);
		schoolDTO.InsertIntoSchool(studentList);
		return schoolDTO.selectFromSchool();
	}
}
