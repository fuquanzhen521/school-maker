package shujuku;

import java.util.List;

public class SelectScoreOfScore {
	public static void main(String args[]) {
		List<Score> scList = ScoreOperation.getInstance().selectScore3();
		System.out.println("总成绩\t平均成绩");
		for (Score sc : scList) {
			System.out.println(sc.getSum() + "\t" + sc.getAvg());
		}
	}
}
