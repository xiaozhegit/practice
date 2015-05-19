package solutionTest;

import static org.junit.Assert.*;
import org.junit.Test;
import solution.RedMartSkiingSolution;


public class RedMartSkiingSolutionTest {

	@Test
	public void test() {
		RedMartSkiingSolution ins = new RedMartSkiingSolution("D:/temp/data.txt");
		System.out.println(ins.getMaxLength());
		System.out.println(ins.getMaxDrop());
	}

}
