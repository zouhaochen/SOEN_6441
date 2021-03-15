package mapTest;

import model.map.MapValidate;
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
     * This method is test case1 for the country connection in the model.map file.
     */
    @Test
    public void testCountryConnection1() {
        File l_File = new File("domination//test_02.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.validateCountryConnection(l_File);
        assertEquals(1, l_A);
    }

    /**
     * This method is test case1 for the continent connection in the model.map file.
     */
    @Test
    public void testCountryConnection2() {
        File l_File = new File("domination//test_02.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.validateContinentConnection(l_File);
        assertEquals(1, l_A);
    }

    /**
     * This method is test case1 for the continent connection in the model.map file.
     */
    @Test
    public void testCountryConnection3() {
        File l_File = new File("domination//test_06_incorrect.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.validateCountryConnection(l_File);
        assertEquals(-1, l_A);
    }

    /**
     * This method is test case1 for the continent connection in the model.map file.
     */
    @Test
    public void testContinentConnection1() {
        File l_File = new File("domination//test_04_incorrect.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.validateContinentConnection(l_File);
        assertEquals(0, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_02.model.map
     */
    @Test
    public void testContinentConnection2() {
        File l_File = new File("domination//test_02.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.check(l_File);
        assertEquals(0, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_04_incorrect.model.map
     */
    @Test
    public void testMapValidate1() {
        File l_File = new File("domination//test_04_incorrect.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.check(l_File);
        assertEquals(6, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_05_incorrect.model.map
     */
    @Test
    public void testMapValidate2() {
        File l_File = new File("domination//test_05_incorrect.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.check(l_File);
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the Map connectivity.
     * Map file: test_06_incorrect.model.map
     */
    @Test
    public void testMapValidate3() {
        File l_File = new File("domination//test_06_incorrect.map");
        MapValidate l_Case1 = new MapValidate();
        int l_A = l_Case1.check(l_File);
        assertEquals(5, l_A);
    }

}
