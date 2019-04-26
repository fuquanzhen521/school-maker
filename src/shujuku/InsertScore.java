package shujuku;

public class InsertScore {
	public static void main(String args[]) {
		Score sc = new Score();
		sc.setSId(7);
		sc.setCId(3);
		sc.setScore(98);
		boolean res = ScoreOperation.getInstance().saveScore(sc);
		if (res = true) {
			System.out.println("向score表中插入数据成功");
		} else {
			System.out.println("向score表中插入数据失败");
		}
	}
}
