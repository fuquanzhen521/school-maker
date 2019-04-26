package database;

public class InsertIntoClass {
	public static void main(String[] args) {
		boolean res = ClassExcelOperation.getInstance().saveClassExcel2();
		if (res = true) {
			System.out.println("向class表中插入数据成功");
		} else {
			System.out.println("向class表中插入数据失败");
		}
	}
}
