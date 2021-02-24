package mapTest;

import static org.junit.Assert.*;

import map.*;
import org.junit.Test;

import java.io.File;

/**
 * This class is for testing MapLineAccess
 *
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapLineAccessTest {


	/**
	 * This method is test case1 for total lines in the map file.
	 */
	@Test
	public void test1() {
		File file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getLines(file);
		assertEquals(25, l_a);
	}

	/**
	 * This method is test case1 for continent lines in the map file.
	 */
	@Test
	public void test2() {
		File file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getContinentLines(file);
		assertEquals(8, l_a);
	}

	/**
	 * This method is test case1 for country lines in the map file.
	 */
	@Test
	public void test3() {
		File file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getCountryLines(file);
		assertEquals(11, l_a);
	}

	/**
	 * This method is test case1 for borders lines in the map file.
	 */
	@Test
	public void test4() {
		File file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getBorderLines(file);
		assertEquals(16, l_a);
	}

}
