package database;

public class InsertIntoGrade {
	public static void main(String[] args) {
		boolean res = GradeOperation.getInstance().saveGrade2();
		if (res = true) {
			System.out.println("向grade表中插入数据成功");
		} else {
			System.out.println("向grade表中插入数据失败");
		}
	}
}
