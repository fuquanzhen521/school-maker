package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import schoolDatabase.DatabaseConnection;

public class CourseDTO {
	private static CourseDTO instance = new CourseDTO();

	public static CourseDTO getInstance() {
		return instance;
	}

	protected CourseDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insertIntoCourse(List<Course> course) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.course(name) values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (Course cou : course) {
				stmt.setString(1, cou.getName());
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
	public List<Course> selectFromCourse() {
		Connection conn = null;
		List<Course> list = new ArrayList<Course>();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id from schooldatabase.course";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				list.add(course);
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
	public List<Course> courseInformation() {
		List<Course> courseList = CourseInfoRandomBuilder.batchBuild(10);
		CourseDTO courseTable = new CourseDTO();
		courseTable.insertIntoCourse(courseList);
		return courseTable.selectFromCourse();
	}
}
