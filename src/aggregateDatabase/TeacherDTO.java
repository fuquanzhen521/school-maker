package aggregateDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import database.DatabaseConnection;

public class TeacherDTO {
	private static TeacherDTO instance = new TeacherDTO();
	
	protected static HashMap<Integer, Integer> courseTeacherMapping;

	public static TeacherDTO getInstance() {
		return instance;
	}

	public TeacherDTO() {
	}

	public void insertIntoTeacher() {
		Connection conn = null;
		CourseDTO coursedto = new CourseDTO();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into aggregate.teacher(name,cid)values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			String[] x = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈" };
			String[] m = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
			HashMap<Integer, Integer> courseMap = new HashMap<Integer, Integer>();
			coursedto.insertIntoCourse();
			ArrayList<Integer> coursedtoList = coursedto.selectFromCourse();
			Collections.shuffle(coursedtoList);
			for (int i = 0; i < coursedtoList.size(); i++) {
				int key = coursedtoList.get(i);
				courseMap.put(key, courseMap.get(key));
				int a = (int) Math.abs(x.length * Math.random());
				int b = (int) Math.abs(m.length * Math.random());
				stmt.setString(1, x[a] + m[b]);
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
	
	public HashMap<Integer, Integer> getCourseTeacherMapping(){
		if(courseTeacherMapping == null || courseTeacherMapping.isEmpty()){
			selectFromTeacher();
		}
		return courseTeacherMapping;
	}

	protected void selectFromTeacher() {
		Connection conn = null;
		this.courseTeacherMapping=new HashMap<Integer,Integer>();
		try {
			conn = DatabaseConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id,cid from aggregate.teacher");
			while (rs.next()) {
				int tid = rs.getInt("id");
				int cid = rs.getInt("cid");
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
		System.out.println("Success load course-teacher mapping size : "+courseTeacherMapping.keySet().size());
	}
	public static void main(String args[]){
		TeacherDTO teacherdto=new TeacherDTO();
		teacherdto.insertIntoTeacher();
		teacherdto.selectFromTeacher();
		System.out.println(teacherdto.getCourseTeacherMapping());
	}
}
