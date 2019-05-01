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
 * 类名:GradeDTO
 * 作用:grade表的增删查改操作
 * 日期:5/1
 */
public class GradeDTO {
	private static GradeDTO instance = new GradeDTO();

	public static GradeDTO getInstance() {
		return instance;
	}

	protected GradeDTO() {
	}

	/*
	 * 插入多条数据
	 */
	public void insertIntoGrade(List<Grade> gradeList) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Grade grade : gradeList) {
				stmt.setString(1, grade.getName());
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
	public void insertIntoGrade(Grade grade) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, grade.getName());
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
	public List<Grade> selectFromGrade() {
		List<Grade> gradeList = new ArrayList<Grade>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select * from schooldatabase.grade";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setId(rs.getLong("id"));
				grade.setName(rs.getString("name"));
				gradeList.add(grade);
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
		return gradeList;
	}

	/*
	 * 取得grade的查询结果的集合
	 */
	public List<Grade> generateGradeInfo(Integer i) {
		GradeDTO gradeDTO = new GradeDTO();
		List<Grade> gradeList = GradeBuilder.batchBuild(i);
		gradeDTO.insertIntoGrade(gradeList);
		return gradeDTO.selectFromGrade();
	}
}
