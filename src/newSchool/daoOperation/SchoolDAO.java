package newSchool.daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import newSchool.encapsulationInformation.School;
import newSchool.schoolSystem.DatabaseConnection;

/*
 * 作者:付全镇
 * 类名:SchoolDAO
 * 作用:school表的增删查改操作
 * 日期:5/1
 */
public class SchoolDAO {
	private static SchoolDAO instance = new SchoolDAO();

	public static SchoolDAO getInstance() {
		return instance;
	}

	protected SchoolDAO() {
	}

	/*
	 * 插入多条信息
	 */
	public static List<Long> insertIntoSchool(List<School> schoolList) {
		List<Long> schoolIdList = new ArrayList<Long>();
		HashMap<String, School> map = new HashMap<String, School>();
		// 查询合肥所有学校的信息,并返回集合allSchoolList
		List<School> allSchoolList = selectFromSchool("合肥%");
		for (School school : allSchoolList) {
			long schoolId = school.getId();
			schoolIdList.add(schoolId);
		}
		// 遍历allSchoolList集合,以name作为key值,school作为values值,存入到map集合中
		for (School school : allSchoolList) {
			String name = school.getName();
			map.put(name, school);
		}
		for (School school : schoolList) {
			String name = school.getName();
			if (!map.containsKey(name)) {
				long schoolId = insertIntoSchool(school);
				schoolIdList.add(schoolId);
			}
		}
		return schoolIdList;
	}

	/*
	 * 插入一条数据
	 */
	public static long insertIntoSchool(School school) {
		Connection conn = null;
		long id = 0;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.school(name,`985`,`211`)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, school.getName());
			stmt.setBoolean(2, school.isJbw());
			stmt.setBoolean(3, school.isEyy());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
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
		return id;
	}

	/*
	 * 查询数据
	 */
	public static List<School> selectFromSchool(String name) {
		List<School> schoolList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select school.* from schooldatabase.school where school.name like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
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

}
