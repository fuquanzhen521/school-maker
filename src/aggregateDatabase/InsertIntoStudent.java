package aggregateDatabase;

public class InsertIntoStudent {
	public static void main(String args[]) {
		boolean res = StudentOperation.getInstance().insertIntoStudent();
		if (res = true) {
			System.out.println("向student表中插入数据成功");
		} else {
			System.out.println("向student表中插入数据失败");
		}
	}
}
