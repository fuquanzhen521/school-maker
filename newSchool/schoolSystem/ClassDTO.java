package schoolSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:ClassDTO
 * 作用:class表的增删查改操作
 * 日期:5/1
 */
public class ClassDTO {
	private static ClassDTO instance = new ClassDTO();

	public static ClassDTO getInstance() {
		return instance;
	}

	protected ClassDTO() {
	}

	/*
	 * 插入多条数据
	 */
	public void insertIntoClass(List<ClassInformation> classList) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.class(name,sch_id,gra_id)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (ClassInformation classInfo : classList) {
				stmt.setString(1, classInfo.getName());
				stmt.setLong(2, classInfo.getSchoolId());
				stmt.setLong(3, classInfo.getGradeId());
				stmt.executeBatch();
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
	 * 插入一条数据
	 */
	public long insertIntoClass(ClassInformation classInfo) {
		Connection conn = null;
		long id = 0;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.class(name,sch_id,gra_id)values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, classInfo.getName());
			stmt.setLong(2, classInfo.getSchoolId());
			stmt.setLong(3, classInfo.getGradeId());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
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
		return id;
	}

	/*
	 * 查询数据
	 */
	public List<ClassInformation> selectFromClass() {
		List<ClassInformation> classList = new ArrayList<ClassInformation>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select * from schooldatabase.class";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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

	/*
	 * 取得id
	 */
	public Long generateClassInfo(ClassInformation classInfo) {
		ClassDTO classDTO = new ClassDTO();
		return classDTO.insertIntoClass(classInfo);

	}
}
