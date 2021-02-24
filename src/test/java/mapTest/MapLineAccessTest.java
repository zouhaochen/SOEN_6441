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
	public void testOne() {
		File l_file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getLines(l_file);
		assertEquals(25, l_a);
	}

	/**
	 * This method is test case1 for continent lines in the map file.
	 */
	@Test
	public void testTwo() {
		File l_file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getContinentLines(l_file);
		assertEquals(8, l_a);
	}

	/**
	 * This method is test case1 for country lines in the map file.
	 */
	@Test
	public void testThree() {
		File l_file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getCountryLines(l_file);
		assertEquals(11, l_a);
	}

	/**
	 * This method is test case1 for borders lines in the map file.
	 */
	@Test
	public void testFour() {
		File l_file = new File("test_02.map");
		MapLineAccess l_case1 = new MapLineAccess();
		int l_a = l_case1.getBorderLines(l_file);
		assertEquals(16, l_a);
	}

}
