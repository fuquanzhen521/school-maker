package aggregateDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ElectiveSelectOperation {
	private static ElectiveSelectOperation instance = new ElectiveSelectOperation();

	public static ElectiveSelectOperation getInstance() {
		return instance;
	}

	protected ElectiveSelectOperation() {
	}

	public List<Elective> selectElective() {
		List<Elective> eleList = new ArrayList<Elective>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select cid from elective group by cid order by count(cid) desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Elective ele = new Elective();
				ele.setCid(rs.getInt("cid"));
				eleList.add(ele);
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
		return eleList;
	}
}
