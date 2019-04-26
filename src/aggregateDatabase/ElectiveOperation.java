package aggregateDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import aggregateDatabase.ElectiveOperation;
import aggregateDatabase.DatabaseConnection;

public class ElectiveOperation {
	private static ElectiveOperation instance = new ElectiveOperation();

	public static ElectiveOperation getInstance() {
		return instance;
	}

	protected ElectiveOperation() {
	}

	public void insertIntoElective() {
		Connection conn = null;
		StudentOperation studentoperation = new StudentOperation();
		CourseDTO coursedto = new CourseDTO();
		TeacherDTO teacherdto = new TeacherDTO();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.elective(sid,cid,tid,score)values(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Random random = new Random();
			studentoperation.insertIntoStudent();
			teacherdto.insertIntoTeacher();
			ArrayList<Integer> stuList = studentoperation.selectFromStudent();
			int c = 0;
			for (int sid : stuList) {
				ArrayList<Integer> pickCourseList = new ArrayList<Integer>();
				ArrayList<Integer> courseList = coursedto.selectFromCourse();
				int randomCourseSize = random.nextInt(3) + 4;
				while (pickCourseList.size() < randomCourseSize) {
					randomPickCourse(teacherdto, stmt, random, sid, pickCourseList, courseList);
				}
				c++;
				if (c % 1000 == 0) {
					System.out.println("第" + c + "个学生选课信息");
				}
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
	}

	private void randomPickCourse(TeacherDTO teacherdto, PreparedStatement stmt, Random random, int sid,
			ArrayList<Integer> pickCourseList, ArrayList<Integer> courseList) throws SQLException {
		int courseIndex = random.nextInt(courseList.size());
		if (!pickCourseList.contains(courseIndex)) {
			stmt.setInt(1, sid);
			Integer cid = courseList.get(courseIndex);
			stmt.setInt(2, cid);
			Integer tid = teacherdto.getCourseTeacherMapping().get(cid);
			stmt.setInt(3, tid);
			int a = random.nextInt(10);
			if (a <= 2) {
				stmt.setInt(4, random.nextInt(60));
			} else if (a > 2 && a <= 7) {
				stmt.setInt(4, random.nextInt(20) + 60);
			} else {
				stmt.setInt(4, random.nextInt(20) + 80);
			}
			stmt.executeUpdate();
			pickCourseList.add(courseIndex);
		} else {
			return;
		}
	}
}
