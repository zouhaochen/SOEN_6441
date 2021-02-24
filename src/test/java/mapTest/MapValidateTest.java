package mapTest;

import map.MapValidate;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapValidate
 *
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapValidateTest {

    /**
     * This method is test case1 for the country connection in the map file.
     */
    @Test
    public void testOne() {
        File l_file = new File("domination//test_02.map");
        MapValidate l_case1 = new MapValidate();
        int l_a = l_case1.validateCountryConnection(l_file);
        assertEquals(1, l_a);
    }

    /**
     * This method is test case1 for the continent connection in the map file.
     */
    @Test
    public void testTwo() {
        File l_file = new File("domination//test_02.map");
        MapValidate l_case1 = new MapValidate();
        int l_a = l_case1.validateContinentConnection(l_file);
        assertEquals(1, l_a);
    }

    @Test
    public void testThree() {
        File l_file = new File("domination//test_06_incorrect.map");
        MapValidate l_case1 = new MapValidate();
        int l_a = l_case1.validateCountryConnection(l_file);
        assertEquals(-1, l_a);
    }

    /**
     * This method is test case1 for the continent connection in the map file.
     */
    @Test
    public void testFour() {
        File l_file = new File("domination//test_04_incorrect.map");
        MapValidate l_case1 = new MapValidate();
        int l_a = l_case1.validateContinentConnection(l_file);
        assertEquals(0, l_a);
    }


}
