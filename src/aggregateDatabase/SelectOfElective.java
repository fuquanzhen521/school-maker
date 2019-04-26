package aggregateDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectOfElective {
	private static SelectOfSchool instance = new SelectOfSchool();

	public static SelectOfSchool getInstance() {
		return instance;
	}

	public SelectOfElective() {
	}

	public List<School> selectSchool() {
		List<School> schList = new ArrayList<School>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from aggregate.school");
			while (rs.next()) {
				School sch = new School();
				sch.setId(rs.getInt("id"));
				sch.setName(rs.getString("name"));
				sch.setJbw(rs.getBoolean("985"));
				sch.setEyy(rs.getBoolean("211"));
				schList.add(sch);
			}
		} catch (

		Exception e)

		{
			e.printStackTrace();
		} finally

		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return schList;
	}
}