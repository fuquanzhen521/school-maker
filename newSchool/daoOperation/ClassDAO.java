package daoOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import encapsulationInformation.ClassInformation;
import schoolSystem.DatabaseConnection;

/*
 * 作者:付全镇
 * 类名:ClassDAO
 * 作用:class表的增删查改操作
 * 日期:5/1
 */
public class ClassDAO {

	private static ClassDAO instance = new ClassDAO();

	public static ClassDAO getInstance() {
		return instance;
	}

	protected ClassDAO() {
	}

	/*
	 * 插入一条数据
	 */
	public static long insertIntoClass(HashMap<String, ClassInformation> map, ClassInformation classInfo)
			throws SQLException {
		Connection conn = DatabaseConnection.getCon();
		long id = 0;
		// 获得当前班级姓名
		String name = classInfo.getName();
		// 判断班级名是不是不在map集合中,如果不在集合中就执行插入操作
		if (!map.containsKey(name)) {
			String sql = "insert into schooldatabase.class(name,sch_id,gra_id)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, classInfo.getName());
			stmt.setLong(2, classInfo.getSchoolId());
			stmt.setLong(3, classInfo.getGradeId());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
			}
			return id;
		} else {
			// 在集合中就返回当前id
			return map.get(classInfo.getName()).getId();
		}
	}

	/*
	 * 查询数据
	 */
	public static List<ClassInformation> selectFromClass(long schoolId, long gradeId) {
		List<ClassInformation> classList = new ArrayList<ClassInformation>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select class.* from class,school,grade where "
					+ "class.sch_id=school.id and class.gra_id=grade.id " + "and school.id=? and grade.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, schoolId);
			stmt.setLong(2, gradeId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClassInformation classInfo = new ClassInformation();
				classInfo.setId(rs.getLong("id"));
				classInfo.setName(rs.getString("name"));
				classInfo.setSchoolId(rs.getLong("sch_id"));
				classInfo.setGradeId(rs.getLong("gra_id"));
				classList.add(classInfo);
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
		return classList;
	}

}
