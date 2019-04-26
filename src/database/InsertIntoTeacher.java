package database;

public class InsertIntoTeacher {
	public static void main(String[] args) {
		int rows = TeacherOperation.getInstance().saveTeacher2();
		System.out.println("批量添加信息的行数是:" + rows);
	}
}
