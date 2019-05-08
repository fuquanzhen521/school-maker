package newSchool.builderOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import newSchool.encapsulationInformation.School;

/*
 * 浣滆��:浠樺叏闀�
 * 绫诲悕:SchoolBuilder
 * 浣滅敤:鐢熸垚school淇℃伅
 * 鏃ユ湡:5/1
 */
public class SchoolBuilder {

	/*
	 * 瀵箂chool淇℃伅鐢熸垚鐨勬搷浣�
	 */
	public static School build(String schoolName, Integer i) {
		String name = schoolName + "绗�" + i + "涓";
		Random random = new Random();
		// 闅忔満鍙�10涓暟
		int r = random.nextInt(10);
		if (r >= 0 && r < 5) {
			return new School(name, false, false);// 褰撳ぇ浜庣瓑浜�0涓斿皬浜�5鏃�,鏃笉鏄�985,涔熶笉鏄�211;
		} else if (r >= 5 && r < 7) {
			return new School(name, true, false);// 褰撳ぇ浜庣瓑浜�5涓斿皬浜�7鏃�,鏄�985,浣嗕笉鏄�211;
		} else if (r >= 7 && r < 9) {
			return new School(name, false, true);// 褰撳ぇ浜庣瓑浜�7涓斿皬浜�9鏃�,涓嶆槸985,浣嗘槸211;
		} else {
			return new School(name, true, true);// 褰撶瓑浜�9鏃�,鏄�985锛屼篃鏄�211;
		}
	}

	/*
	 * 鐢熸垚school鏁版嵁
	 */
	public static List<School> batchBuild(String schoolName, Integer size) {
		List<School> schoolList = new ArrayList<School>();
		for (int i = 1; i <= size; i++) {
			School school = build(schoolName, i);
			schoolList.add(school);
		}
		return schoolList;
	}

}
