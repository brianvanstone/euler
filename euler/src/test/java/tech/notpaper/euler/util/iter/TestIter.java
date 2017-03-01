package tech.notpaper.euler.util.iter;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestIter {
	List<String> a;
	List<String> b;
	List<String> c;

	//@Test
	public void testZip() {
		loadData();
		
		for(List<String> row : Iter.zip(a, b, c)) {
			System.out.println(row);
		}
	}
	
	@Test
	public void testChain() {
		loadData();
		
		for(String item : Iter.chain(a, b, c)) {
			System.out.println(item);
		}
	}
	
	private void loadData() {
		a = new LinkedList<>();
		b = new LinkedList<>();
		c = new LinkedList<>();
		
		a.add("a1");
		a.add("a2");
		a.add("a3");
		a.add("a4");
		a.add("a5");
		a.add("a6");

		b.add("b1");
		b.add("b2");
		b.add("b3");
		b.add("b4");
		b.add("b5");
		
		c.add("c1");
		c.add("c2");
		c.add("c3");
		c.add("c4");
		c.add("c5");
		c.add("c6");
	}
}
