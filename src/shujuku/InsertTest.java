package shujuku;

public class InsertTest {
	public static void main(String args[]) {
		Course cre = new Course();
		cre.setCId(4);
		cre.setCname("思想品德");
		cre.setTId(2);
		boolean res = CourseOperation.getInstance().saveCourse(cre);
		if (res = true) {
			System.out.println("向course表中插入数据成功");
		} else {
			System.out.println("向course表中插入数据失败");
		}
	}
}
