package database;

public class InsertIntoCourse {
	public static void main(String[] args) {
		boolean res = CourseOperation.getInstance().saveCourse2();
		if (res = true) {
			System.out.println("向course表中插入数据成功");
		} else {
			System.out.println("向course表中插入数据失败");
		}
	}
}
