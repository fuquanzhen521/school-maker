package shujuku;

public class DeleteScore {
	public static void main(String args[]) {
		Score sc = new Score();
		sc.setSId(7);
		sc.setCId(3);
		boolean re = ScoreOperation.getInstance().deleteScoreById(sc);
		if (re = true) {
			System.out.println("删除学生ID为7，课程ID为3的学生成绩成功");
		} else {
			System.out.println("删除学生ID为7，课程ID为,3的学生成绩失败");
		}
	}
}
