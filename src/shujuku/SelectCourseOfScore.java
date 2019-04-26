package shujuku;

import java.util.List;

public class SelectCourseOfScore {
	public static void main(String args[]) {
		List<Score> scList = ScoreOperation.getInstance().selectScore2();
		System.out.println("学生ID\t课程ID\t成绩");
		for (Score sc : scList) {
			System.out.println(sc.getSId() + "\t" + sc.getCId() + "\t" + sc.getScore());
		}
	}
}
