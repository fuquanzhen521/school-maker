package database;

public class InsertSchoolTest {
	public static void main(String args[]) {
		School sch = new School();
		sch.setId(1);
		sch.setName("一中");
		boolean res = SchoolOperation.getInstance().saveSchool(sch);
		if (res = true) {
			System.out.println("向school表中插入数据成功");
		} else {
			System.out.println("向school表中插入数据失败");
		}
	}
}
