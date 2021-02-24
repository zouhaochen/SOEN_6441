package mapTest;

import map.MapCheck;
import map.MapLineAccess;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapCheck
 *
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapCheckTest {

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_02.map
     */
    @Test
    public void testOne() {
        File l_file = new File("domination//test_02.map");
        MapCheck l_case1 = new MapCheck();
        int l_a = l_case1.check(l_file);
        assertEquals(0, l_a);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_04_incorrect.map
     */
    @Test
    public void testTwo() {
        File l_file = new File("domination//test_04_incorrect.map");
        MapCheck l_case1 = new MapCheck();
        int l_a = l_case1.check(l_file);
        assertEquals(6, l_a);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_05_incorrect.map
     */
    @Test
    public void testThree() {
        File l_file = new File("domination//test_05_incorrect.map");
        MapCheck l_case1 = new MapCheck();
        int l_a = l_case1.check(l_file);
        assertEquals(4, l_a);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_06_incorrect.map
     */
    @Test
    public void testFour() {
        File l_file = new File("domination//test_06_incorrect.map");
        MapCheck l_case1 = new MapCheck();
        int l_a = l_case1.check(l_file);
        assertEquals(5, l_a);
    }

}
