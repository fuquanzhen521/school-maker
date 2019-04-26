package shujuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreOperation {
	private static ScoreOperation instance = new ScoreOperation();

	public static ScoreOperation getInstance() {
		return instance;
	}

	private ScoreOperation() {
	}

	public boolean saveScore(Score sc) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sqlInsert = "insert into question.score(SId,CId,score)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setInt(1, sc.getSId());
			stmt.setInt(2, sc.getCId());
			stmt.setFloat(3, sc.getScore());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Score> selectScore() {
		List<Score> scList = new ArrayList<Score>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from question.score");
			while (rs.next()) {
				Score sc = new Score();
				sc.setSId(rs.getInt("SId"));
				sc.setCId(rs.getInt("CId"));
				sc.setScore(rs.getFloat("score"));
				scList.add(sc);
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
		return scList;
	}

	public List<Score> selectScore2() {
		List<Score> scList = new ArrayList<Score>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select * from question.score"
					+ " where score.SId not in"
					+ "(select SId from score where score.CId='01') "
					+ "and score.SId in(select SId from score "
					+ "where score.CId='02') and score.CId='02'");
			while (rs.next()) {
				Score sc = new Score();
				sc.setSId(rs.getInt("SId"));
				sc.setCId(rs.getInt("CId"));
				sc.setScore(rs.getFloat("score"));
				scList.add(sc);
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
		return scList;
	}

	public List<Score> selectScore3() {
		List<Score> scList = new ArrayList<Score>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			String sql = "select sum(score),avg(score) from score group by SId order by avg(score) desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Score sc = new Score();
				sc.setSum(rs.getInt("sum(score)"));
				sc.setAvg(rs.getInt("avg(score)"));
				scList.add(sc);
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
		return scList;
	}

	public boolean updateCourse(Score sc) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "update question.score set score=? where SId=? and CId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, sc.getScore());
			stmt.setInt(2, sc.getSId());
			stmt.setInt(3, sc.getCId());
			int flag = stmt.executeUpdate();
			if (flag == 1) {
				result = true;
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
		return result;
	}

	public boolean deleteScoreById(Score sc) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "delete from question.score where SId=? and CId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, sc.getSId());
			stmt.setInt(2, sc.getCId());
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = true;
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
		return result;
	}
}
