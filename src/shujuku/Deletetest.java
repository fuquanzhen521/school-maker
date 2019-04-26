package shujuku;

public class Deletetest {
	public static void main(String args[]) {
		Course cre = new Course();
		cre.setCId(4);
		boolean re = CourseOperation.getInstance().deleteCourseById(cre);
		if (re = true) {
			System.out.println("删除课程ID为4的记录成功");
		} else {
			System.out.println("删除课程ID为4的记录失败");
		}
	}
}
