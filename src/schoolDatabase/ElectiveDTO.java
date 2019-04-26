package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ElectiveDTO {
	private static ElectiveDTO instance = new ElectiveDTO();

	public static ElectiveDTO getInstance() {
		return instance;
	}

	protected ElectiveDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insertIntoElective(List<Elective> elective) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.elective(sid,cid,tid,score) values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Elective ele : elective) {
				stmt.setInt(1, ele.getSid());
				stmt.setInt(2, ele.getCid());
				stmt.setInt(3, ele.getTid());
				stmt.setFloat(4, ele.getScore());
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
}
