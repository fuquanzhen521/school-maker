package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeDTO {
	private static GradeDTO instance = new GradeDTO();

	public static GradeDTO getInstance() {
		return instance;
	}

	protected GradeDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insertIntoGrade(List<Grade> grade) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.grade(name) values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Grade gra : grade) {
				stmt.setString(1, gra.getName());
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
	public List<Grade> selectFromGrade() {
		List<Grade> list = new ArrayList<Grade>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id from schooldatabase.grade";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setId(rs.getInt("id"));
				list.add(grade);
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
	 * 取得grade表信息
	 */
	public List<Grade> gradeInformation() {
		List<Grade> gradeList = GradeInfoBuilder.batchBuild(4);
		GradeDTO gradeTable = new GradeDTO();
		gradeTable.insertIntoGrade(gradeList);
		return gradeTable.selectFromGrade();
	}
}
