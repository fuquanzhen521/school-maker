package schoolDatabase;

import java.util.List;

public class InsertIntoTable {
	public static void main(String args[]) {
		SchoolDTO schoolTable = new SchoolDTO();
		List<School> schoolList = schoolTable.schoolInformation();
		GradeDTO gradeTable = new GradeDTO();
		List<Grade> gradeList = gradeTable.gradeInformation();
		// 构建 班级信息
		List<ClassInformation> classList = ClassInfoRandomBuilder.batchBuild(schoolList, gradeList, 25);
		// 保存班级信息
		ClassDTO classTable = new ClassDTO();
		classTable.insertIntoClass(classList);
		System.out.println("Success save 25 class info!");
	}
}
