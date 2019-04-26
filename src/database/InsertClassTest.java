package database;

public class InsertClassTest {
	public static void main(String args[]) {
		ClassTable cla = new ClassTable();
		cla.setId(1);
		cla.setName("20班");
		boolean res = ClassExcelOperation.getInstance().saveClassExcel(cla);
		if (res = true) {
			System.out.println("向class表中插入数据成功");
		} else {
			System.out.println("向class表中插入数据失败");
		}
	}
}
