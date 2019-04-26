package hellotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*作者:付全镇
日期:04/15
类名:ClassDTO
作用:往class表里插入和查询数据*/
public class ClassDTO {
	private static ClassDTO instance = new ClassDTO();

	public static ClassDTO getInstance() {
		return instance;
	}

	protected ClassDTO() {
	}

	// 插入数据
	public void insertClass() {
		Connection conn = null;
		SchoolDTO school = new SchoolDTO();
		GradeDTO grade = new GradeDTO();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "insert into hellomyschool.class(name,grade_id,school_id) values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			school.insertIntoSchool("合肥");
			List<Integer> schList = school.selectSchool();
			grade.insertGrade();
			List<Integer> graList = grade.selectGrade();
			int f = 0;
			int q = 0;
			for (int gid : graList) {
				q++;
				for (int i = 0; i < schList.size(); i++) {
					f++;
					for (int c = 1; c <= 25; c++) {
						stmt.setString(1, c + "班");
						stmt.setInt(2, gid);
						stmt.setInt(3, schList.get(i));
						stmt.executeUpdate();
						System.out.println("第" + f + "中学" + q + "年级" + c + "班");
					}
					if (f >= 100) {
						f = 0;
					}
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

	// 查询数据
	public ClassDTOTest selectClass() {
		Connection conn = null;
		List<ClassTable> claList = new ArrayList<ClassTable>();
		try {
			conn = DatabaseConnection.getCon();
			String sql = "select id,grade_id,school_id from hellomyschool.class";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ClassTable cla = new ClassTable();
				cla.setId(rs.getInt("id"));
				cla.setGrade_id(rs.getInt("grade_id"));
				cla.setSchool_id(rs.getInt("school_id"));
				claList.add(cla);
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
		ClassDTOTest result = new ClassDTOTest(claList);
		return result;
	}
}
