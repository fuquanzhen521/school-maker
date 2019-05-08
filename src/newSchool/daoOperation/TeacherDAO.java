package newSchool.daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import newSchool.encapsulationInformation.Teacher;
import newSchool.schoolSystem.DatabaseConnection;

/*
 * 作者:付全镇
 * 类名:TeacherDAO
 * 作用:teacher表的增删查改操作
 * 日期:5/1
 */
public class TeacherDAO {
	private static TeacherDAO instance = new TeacherDAO();

	public static TeacherDAO getInstance() {
		return instance;
	}

	protected TeacherDAO() {
	}

	/*
	 * 插入多条数据
	 */
	public static List<Teacher> insertIntoTeacher(List<Teacher> teacherList) {
		// 查询teacher表中的所有数据
		List<Teacher> allTeacherList = selectFromTeacher();
		// 判断allTeacherList是否为null,或集中中的元素个数是否小于teacherList中的元素个数
		if (allTeacherList == null || allTeacherList.size() < teacherList.size()) {
			// index为当前allTeacherList集合中元素的个数
			int index = allTeacherList.size();
			// 让i等于index,并作为teacherList的下标值,依次插入,直到下标值小于teacherList中元素的个数
			for (int i = index; i < teacherList.size(); i++) {
				Teacher teacher = teacherList.get(i);
				insertIntoTeacher(teacher);
			}
		} else {
			// 如果集合中的元素已经大于等于teacherList中的元素个数,则直接返回查询结果
			return allTeacherList;
		}
		// 返回插入完毕后的查询结果
		return selectFromTeacher();
	}

	/*
	 * 插入一条数据
	 */
	public static void insertIntoTeacher(Teacher teacher) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.teacher(name,cid)values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, teacher.getName());
			stmt.setLong(2, teacher.getCourseId());
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
	public static List<Teacher> selectFromTeacher() {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select teacher.* from schooldatabase.teacher";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getLong("id"));
				teacher.setName(rs.getString("name"));
				teacher.setCourseId(rs.getLong("cid"));
				teacherList.add(teacher);
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
		return teacherList;
	}

}
