package hellotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//作者:付全镇
//日期:04/15

//类名:TeacherDTO
//作用:向teacher表插入和查询数据
public class TeacherDTO {
	private static TeacherDTO instance = new TeacherDTO();
	private static HashMap<Integer, Integer> courseTeacherMapping;

	public static TeacherDTO getInstance() {
		return instance;
	}

	protected TeacherDTO() {
	}

	public void insertTeacher() {
		Connection conn = null;
		CourseDTO course = new CourseDTO();
		try {
			conn = DatabaseConnection.getCon();
			// 创建sql语句
			String sql = "insert into hellomyschool.teacher(name,course_id) values (?,?)";
			// 创建PreparedStatement类,用来执行sql语句
			PreparedStatement stmt = conn.prepareStatement(sql);
			// 创建数组x，存入一些姓氏
			String x[] = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈" };
			// 创建数组m
			String m[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
			// 创建courseMap集合,用来存入教师id和课程id
			HashMap<Integer, Integer> courseMap = new HashMap<Integer, Integer>();
			// 插入课程信息
			course.insertCourse();
			// 创建courseList集合,存入课程id
			List<Integer> courseList = course.selectCourse();
			// 打乱courseList的排序
			Collections.shuffle(courseList);
			for (int i = 0; i < courseList.size(); i++) {
				// 获取courseList的key值
				int key = courseList.get(i);
				courseMap.put(key, courseMap.get(key));
				int a = (int) Math.abs(x.length * Math.random());
				int b = (int) Math.abs(m.length * Math.random());
				// 插入学校名
				stmt.setString(1, x[a] + m[b]);
				// 插入课程id
				stmt.setInt(2, key);
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

	public HashMap<Integer, Integer> getCourseTeacherMapping() {
		if (courseTeacherMapping == null || courseTeacherMapping.isEmpty()) {
			selectTeacher();
		}
		return courseTeacherMapping;
	}

	public void selectTeacher() {
		Connection conn = null;
		// 实例化courseTeacherMapping集合
		this.courseTeacherMapping = new HashMap<Integer, Integer>();
		try {
			conn = DatabaseConnection.getCon();
			// 创建statement类对象，用来执行SQL语句
			Statement stmt = conn.createStatement();
			// 创建sql语句
			String sql = "select id,course_id from hellomyschool.teacher";
			// ResultSet类，用来存放获取的结果集
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// 获取id这列数据
				int tid = rs.getInt("id");
				// 获取course_id这列数据
				int cid = rs.getInt("course_id");
				// 把结果存入courseTeacherMapping集合
				courseTeacherMapping.put(cid, tid);
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
		System.out.println("Success load course-teacher mapping size:" + courseTeacherMapping.keySet().size());
	}
}
