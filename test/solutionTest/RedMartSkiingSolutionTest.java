package solutionTest;

import static org.junit.Assert.*;
import org.junit.Test;
import solution.RedMartSkiingSolution;


public class RedMartSkiingSolutionTest {

	@Test
	public void test() {
		/**
		 * Extreme case 1: all the elements with same value
		 *  4 4 
		 *	0 0 0 0 
		 *	0 0 0 0 
		 *	0 0 0 0 
		 *	0 0 0 0
		 */
		//RedMartSkiingSolution ins = new RedMartSkiingSolution("D:/temp/d1.txt");
		/**
		 * Extreme case 2: only one value different
		 *  4 4 
		 *	0 0 0 0 
		 *	0 0 1 0 
		 *	0 0 0 0 
		 *	0 0 0 0
		 */
		//RedMartSkiingSolution ins = new RedMartSkiingSolution("D:/temp/d2.txt");
		/**
		 * Extreme case 3: two paths with the same length but different drop
		 *  4 4 
		 *	0 0 0 0 
		 *	8 0 9 0 
		 *	2 0 6 0 
		 *	0 0 0 0
		 */
		//RedMartSkiingSolution ins = new RedMartSkiingSolution("D:/temp/d3.txt");
		/**
		 * Extreme case 4: two paths with the same length but different drop (check wether equal value will form a path or not)
		 *  4 4 
		 *	0 0 0 0 
		 *	8 0 9 0 
		 *	2 0 9 0 
		 *	0 0 0 0
		 */
		//RedMartSkiingSolution ins = new RedMartSkiingSolution("D:/temp/d4.txt");
		
		RedMartSkiingSolution ins = new RedMartSkiingSolution("D:/temp/data.txt");
		System.out.println(ins.getMaxLength());
		System.out.println(ins.getMaxDrop());
	}

}
