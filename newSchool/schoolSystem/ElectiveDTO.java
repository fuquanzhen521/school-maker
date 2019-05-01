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
 * 类名:ElectiveDTO
 * 作用:elective表的增删查改操作
 * 日期:5/1
 */
public class ElectiveDTO {
	private static ElectiveDTO instance = new ElectiveDTO();

	private static ElectiveDTO getInstance() {
		return instance;
	}

	protected ElectiveDTO() {
	}

	/*
	 * 插入多条数据
	 */
	public void insertIntoElective(List<Elective> electiveList) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.elective(sid,cid,tid,score)values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Elective elective : electiveList) {
				stmt.setLong(1, elective.getStudentId());
				stmt.setLong(2, elective.getCourseId());
				stmt.setLong(3, elective.getTeacherId());
				stmt.setFloat(4, elective.getScore());
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
	 * 插入一条数据
	 */
	public void insertIntoElective(Elective elective) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.elective(sid,cid,tid,score)values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, elective.getStudentId());
			stmt.setLong(2, elective.getCourseId());
			stmt.setLong(3, elective.getTeacherId());
			stmt.setFloat(4, elective.getScore());
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
	public List<Elective> selectFromElective() {
		List<Elective> electiveList = new ArrayList<Elective>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select * from schooldatabase.elective";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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
