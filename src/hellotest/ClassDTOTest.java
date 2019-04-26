package hellotest;

import java.util.ArrayList;
import java.util.List;
//作者:付全镇
//日期:04/15

//类名:ClassDTOTest
//作用:把三个list集合放到一个容器里面
public class ClassDTOTest {
	private List<ClassTable> claList;

	public ClassDTOTest(List<ClassTable> claList) {
		this.claList = claList;
	}

	public List<ClassTable> getClaList() {
		return claList;
	}

	public void setClaList(List<ClassTable> claList) {
		this.claList = claList;
	}

	public List<Integer> getClassList() {
		List<Integer> classList = new ArrayList<Integer>();
		for (ClassTable s : claList) {
			classList.add(s.getId());
		}
		return classList;
	}

	public List<Integer> getGradeList() {
		List<Integer> graList = new ArrayList<Integer>();
		for (ClassTable s : claList) {
			graList.add(s.getGrade_id());
		}
		return graList;
	}

	public List<Integer> getSchoolList() {
		List<Integer> schList = new ArrayList<Integer>();
		for (ClassTable s : claList) {
			schList.add(s.getSchool_id());
		}
		return schList;
	}
}