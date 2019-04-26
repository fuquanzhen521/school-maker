package hellotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*作者:付全镇
       日期:04/15
       类名:ElectiveOperation
作用:向electivetable表插入数据  */
public class ElectiveOperation {
	private static ElectiveOperation instance = new ElectiveOperation();

	public static ElectiveOperation getInstance() {
		return instance;
	}

	protected ElectiveOperation() {
	}

	// 插入数据
	public void insertElective() {
		Connection conn = null;
		TeacherDTO teacher = new TeacherDTO();
		CourseDTO course = new CourseDTO();
		StudentDTO student = new StudentDTO();
		try {
			conn = DatabaseConnection.getCon();
			// 要执行的SQL语句
			String sql = "insert into hellomyschool.elective(student_id,teacher_id,course_id,score) values(?,?,?,?)";
			// 创建PreparedStatement类对象，用来执行SQL语句
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			// 插入teacher表
			teacher.insertTeacher();
			// 插入student表
			student.insetStudent();
			// stuList集合存入学生id
			List<Integer> stuList = student.selectStudent();
			int c = 0;
			for (int sid : stuList) {
				// 建立一个pickCourseList集合
				List<Integer> pickCourseList = new ArrayList<Integer>();
				// course集合存入课程id
				List<Integer> courseList = course.selectCourse();
				// 随机选取4-6门课程
				int randomCourseSize = random.nextInt(3) + 4;
				// 当pickCourseList集合的长度是否小于随机柯城数,执行randomPickCourse方法
				while (pickCourseList.size() < randomCourseSize) {
					randomPickCourse(teacher, stmt, random, sid, pickCourseList, courseList);
				}
				c++;
				if (c % 1000 == 0) {
					System.out.println("第" + c + "个学生选课信息");
				}
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

	public void randomPickCourse(TeacherDTO teacher, PreparedStatement stmt, Random random, int sid,
			List<Integer> pickCourseList, List<Integer> courseList) throws SQLException {
		// 从courseList随机选取一个数
		int courseIndex = random.nextInt(courseList.size());
		// 判断pickCourseList是否包含这个数
		if (!pickCourseList.contains(courseIndex)) {
			// 插入学生id
			stmt.setInt(1, sid);
			int cid = courseList.get(courseIndex);
			// 插入课程id
			stmt.setInt(3, cid);
			int tid = teacher.getCourseTeacherMapping().get(cid);
			// 插入老师id
			stmt.setInt(2, tid);
			// 随机取0-9之间的一个数
			int a = random.nextInt(10);
			if (a <= 2) {
				// 插入成绩
				stmt.setInt(4, random.nextInt(60));
			} else if (a > 2 && a <= 7) {
				// 插入成绩
				stmt.setInt(4, random.nextInt(20) + 60);
			} else {
				// 插入成绩
				stmt.setInt(4, random.nextInt(20) + 80);
			}
			stmt.executeUpdate();
			pickCourseList.add(courseIndex);
		} else {
			return;
		}
	}
}
