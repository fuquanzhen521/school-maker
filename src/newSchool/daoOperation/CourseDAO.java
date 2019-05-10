package newSchool.daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import newSchool.encapsulationInformation.Course;
import newSchool.schoolSystem.DatabaseConnection;

/*
 * 作者:付全镇
 * 类名:CourseDAO
 * 作用:course表的增删查改操作
 * 日期:5/1
 */
public class CourseDAO {
	private static CourseDAO instance = new CourseDAO();

	private static CourseDAO getInstance() {
		return instance;
	}

	protected CourseDAO() {
	}

	/*
	 * 插入多条数据
	 */
	public static List<Long> insertIntoCourse(List<Course> courseList) {
		List<Long> courseIdList = new ArrayList<Long>();
		// 获得存有course表所有数据查询结果的集合
		List<Course> allCourseList = selectFromCourse();
		// 遍历course集合,如果里面有数据,就把它的id存入到courseIdList集合里,如果没有数据gcourseIdList就为null
		for (Course course : allCourseList) {
			long courseId = course.getId();
			courseIdList.add(courseId);
		}
		// 判断allCourseList集合是否为null,或集合中的元素个数是否小于courseList中的元素个数
		if (allCourseList == null || allCourseList.size() < courseList.size()) {
			// index为当前集合中元素的个数?
			int index = allCourseList.size();
			// 让i等于index的值?,并作为courseList的下标值?,依次插入,知道下标值小于courseList中元素的总个数?
			for (int i = index; i < courseList.size(); i++) {
				Course course = courseList.get(i);
				long courseId = insertIntoCourse(course);
				courseIdList.add(courseId);
			}
		}
		return courseIdList;
	}

	/*
	 * 插入�?条数�?
	 */
	public static long insertIntoCourse(Course course) {
		Connection conn = null;
		long id = 0;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.course(name)values(?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, course.getName());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
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
	public static List<Course> selectFromCourse() {
		List<Course> courseList = new ArrayList<Course>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select course.* from schooldatabase.course";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getLong("id"));
				course.setName(rs.getString("name"));
				courseList.add(course);
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
		return courseList;
	}

}
