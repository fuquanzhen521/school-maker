package schoolDatabase;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者:付全镇
 * 类名:ClassInfoRandomBuilder
 * 日期:04/26
 * 作用:生成一组班级信息
 */
public class ClassInfoRandomBuilder {

	public static List<ClassInformation> batchBuild(List<School> school, List<Grade> grade, int size) {
		List<ClassInformation> claList = new ArrayList<ClassInformation>();
		int f = 0;
		int q = 0;
		for (Grade gid : grade) {
			q++;
			for (int i = 0; i < school.size(); i++) {
				f++;
				for (int cid = 1; cid <= size; cid++) {
					String name = cid + "班";
					ClassInformation classInfo = new ClassInformation(name, school.get(i).getId(), gid.getId());
					claList.add(classInfo);
					System.out.println("第" + f + "中学" + q + "年级" + cid + "班");
				}
				if (f >= 100) {
					f = 0;
				}
			}
		}
		return claList;
	}
}
