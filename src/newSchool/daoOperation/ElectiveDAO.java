package daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encapsulationInformation.Elective;
import schoolSystem.DatabaseConnection;

/*
 * 作�??:付全�?
 * 类名:ElectiveDAO
 * 作用:elective表的增删查改操作
 * 日期:5/1
 */
public class ElectiveDAO {
	private static ElectiveDAO instance = new ElectiveDAO();

	private static ElectiveDAO getInstance() {
		return instance;
	}

	protected ElectiveDAO() {
	}

	/*
	 * 批量插入多条数据
	 */
	public static void insertIntoElective(List<Elective> electiveList) throws SQLException {
		Connection conn = DatabaseConnection.getCon();
		String sql = "insert into schooldatabase.elective(sid,cid,tid,score)values(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		for (Elective elective : electiveList) {
			stmt.setLong(1, elective.getStudentId());
			stmt.setLong(2, elective.getCourseId());
			stmt.setLong(3, elective.getTeacherId());
			stmt.setFloat(4, elective.getScore());
			stmt.addBatch();
		}
		stmt.executeBatch();
	}

	/*
	 * 查询数据
	 */
	public static List<Elective> selectFromElective(long studentId) {
		List<Elective> electiveList = new ArrayList<Elective>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select elective.* from elective,student " 
			           + "where elective.sid=student.id and student.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Elective elective = new Elective();
				elective.setStudentId(rs.getLong("sid"));
				elective.setCourseId(rs.getLong("cid"));
				elective.setTeacherId(rs.getLong("tid"));
				elective.setScore(rs.getFloat("score"));
				electiveList.add(elective);
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
		return electiveList;
	}
}
