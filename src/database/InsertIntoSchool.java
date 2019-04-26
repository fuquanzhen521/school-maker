package database;

public class InsertIntoSchool {
	public static void main(String[] args) {
		boolean res = SchoolOperation.getInstance().saveSchool2();
		if (res = true) {
			System.out.println("向school表中插入数据成功");
		} else {
			System.out.println("向school表中插入数据失败");
		}
	}
}
