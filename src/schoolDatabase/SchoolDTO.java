package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import schoolDatabase.DatabaseConnection;

/*
作者:付全镇
日期:04/23
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
	public void insertIntoSchool(List<School> school) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.school(name,`985`,`211`) values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (School sch : school) {
				stmt.setString(1, sch.getName());
				stmt.setBoolean(2, sch.isJbw());
				stmt.setBoolean(3, sch.isEyy());
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
	public List<School> selectFromSchool() {
		Connection conn = null;
		List<School> list = new ArrayList<School>();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id from schooldatabase.school";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				School school = new School();
				school.setId(rs.getInt("id"));
				list.add(school);
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
		return list;
	}

	/*
	 * 取得School表信息
	 */
	public List<School> schoolInformation() {
		List<School> schoolList = SchoolInfoRandomBuilder.batchBuild("合肥", 100);
		SchoolDTO schoolTable = new SchoolDTO();
		schoolTable.insertIntoSchool(schoolList);
		return schoolTable.selectFromSchool();
	}
}
