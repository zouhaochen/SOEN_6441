package mapTest;

import model.MapCheck;
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
     * Map file: test_02.model.map
     */
    @Test
    public void testOne() {
        File l_File = new File("domination//test_02.model.map");
        MapCheck l_Case1 = new MapCheck();
        int l_A = l_Case1.check(l_File);
        assertEquals(0, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_04_incorrect.model.map
     */
    @Test
    public void testTwo() {
        File l_File = new File("domination//test_04_incorrect.model.map");
        MapCheck l_Case1 = new MapCheck();
        int l_A = l_Case1.check(l_File);
        assertEquals(6, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_05_incorrect.model.map
     */
    @Test
    public void testThree() {
        File l_File = new File("domination//test_05_incorrect.model.map");
        MapCheck l_Case1 = new MapCheck();
        int l_A = l_Case1.check(l_File);
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_06_incorrect.model.map
     */
    @Test
    public void testFour() {
        File l_File = new File("domination//test_06_incorrect.model.map");
        MapCheck l_Case1 = new MapCheck();
        int l_A = l_Case1.check(l_File);
        assertEquals(5, l_A);
    }

}
