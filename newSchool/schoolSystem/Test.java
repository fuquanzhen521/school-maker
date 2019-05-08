package schoolSystem;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String args[]) {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(6);
		list2.add(7);
		list2.add(8);
		list2.add(9);
		list1.addAll(list2);
		System.out.println(list1);
	}
}
