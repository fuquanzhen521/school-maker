package shujuku;

import java.util.List;

public class SelectStudent {
	public static void main(String args[]) {
		List<Student> stuList = StudentOperation.getInstance().selectStudent();
		System.out.println("学生ID\t学生姓名\t学生年龄\t学生性别");
		for (Student stu : stuList) {
			System.out.println(stu.getSId() + "\t" + stu.getSname() + "\t" + stu.getSage() + "\t" + stu.getSsex());
		}
	}
}
