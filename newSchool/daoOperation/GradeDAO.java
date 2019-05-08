package daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import encapsulationInformation.Grade;
import schoolSystem.DatabaseConnection;

/*
 * 作�??:付全�?
 * 类名:GradeDAO
 * 作用:grade表的增删查改操作
 * 日期:5/1
 */
public class GradeDAO {
	private static GradeDAO instance = new GradeDAO();

	public static GradeDAO getInstance() {
		return instance;
	}

	protected GradeDAO() {
	}

	/*
	 * 插入多条数据
	 */
	public static List<Long> insertIntoGrade(List<Grade> gradeList) {
		List<Long> gradeIdList = new ArrayList<Long>();
		// 查询grade表里的所有数�?
		List<Grade> allGradeList = selectFromGrade();
		// 遍历grade�?,如果里面有数�?,就把它的id存入到gradeIdList集合�?,如果没有数据gradeIdList就为null
		for (Grade grade : allGradeList) {
			long gradeId = grade.getId();
			gradeIdList.add(gradeId);
		}
		// 判断allGradeList集合是否为null,或里面的元素个数是否小于四个
		if (allGradeList == null || allGradeList.size() < 4) {
			// index为当前集合里拥有的元素个�?
			int index = allGradeList.size();
			// 让i等于index的�??,并作为gradeList的下标�??,执行插入,�?直到下标值小�?4为止
			for (int i = index; i < 4; i++) {
				Grade grade = gradeList.get(i);
				long gradeId = insertIntoGrade(grade);
				gradeIdList.add(gradeId);
			}
		}
		return gradeIdList;
	}

	/*
	 * 插入�?条数�?
	 */
	public static long insertIntoGrade(Grade grade) {
		Connection conn = null;
		long id = 0;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.grade(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, grade.getName());
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
	public static List<Grade> selectFromGrade() {
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

}
