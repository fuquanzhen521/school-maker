package schoolDatabase;

import java.util.List;

public class InsertIntoSchool {
	public static void main(String args[]) {
		// 构建 100个学校信息
		List<School> schoolList = SchoolInfoRandomBuilder.batchBuild("合肥", 100);
		// 保存100个学校信息
		SchoolDTO schoolTable = new SchoolDTO();
		schoolTable.insertIntoSchool(schoolList);
		System.out.println("Success save 100 school info!");
	}
}
