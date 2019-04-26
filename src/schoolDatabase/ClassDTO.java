package schoolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDTO {
	private static ClassDTO instance = new ClassDTO();

	public static ClassDTO getInstance() {
		return instance;
	}

	protected ClassDTO() {
	}

	/*
	 * 插入数据
	 */
	public void insertIntoClass(List<ClassInformation> classInfo) {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into schooldatabase.class(name,sch_id,gra_id) values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (ClassInformation cla : classInfo) {
				stmt.setString(1, cla.getName());
				stmt.setInt(2, cla.getSch_id());
				stmt.setInt(3, cla.getGra_id());
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
	public List<ClassInformation> selectFromClass() {
		List<ClassInformation> list = new ArrayList<ClassInformation>();
		Connection conn = null;
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id,sch_id,gra_id from schooldatabase.class";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ClassInformation classInfo = new ClassInformation();
				classInfo.setId(rs.getInt("id"));
				classInfo.setSch_id(rs.getInt("sch_id"));
				classInfo.setGra_id(rs.getInt("gra_id"));
				list.add(classInfo);
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
	 * 取得class表信息
	 */
	public List<ClassInformation> classInfo() {
		SchoolDTO schoolTable = new SchoolDTO();
		List<School> schoolList = schoolTable.schoolInformation();
		GradeDTO gradeTable = new GradeDTO();
		List<Grade> gradeList = gradeTable.gradeInformation();
		List<ClassInformation> classList = ClassInfoRandomBuilder.batchBuild(schoolList, gradeList, 25);
		ClassDTO classTable = new ClassDTO();
		classTable.insertIntoClass(classList);
		return classTable.selectFromClass();
	}
}
