package mapTest;

import static org.junit.Assert.*;

import model.MapLineAccess;
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
     * This method is test case1 for total lines in the model.map file.
     */
    @Test
    public void testOne() {
        File l_File = new File("domination//test_02.model.map");
        MapLineAccess l_Case1 = new MapLineAccess();
        int l_A = l_Case1.getLines(l_File);
        assertEquals(25, l_A);
    }

    /**
     * This method is test case1 for continent lines in the model.map file.
     */
    @Test
    public void testTwo() {
        File l_File = new File("domination//test_02.model.map");
        MapLineAccess l_Case1 = new MapLineAccess();
        int l_A = l_Case1.getContinentLines(l_File);
        assertEquals(8, l_A);
    }

    /**
     * This method is test case1 for country lines in the model.map file.
     */
    @Test
    public void testThree() {
        File l_File = new File("domination//test_02.model.map");
        MapLineAccess l_Case1 = new MapLineAccess();
        int l_A = l_Case1.getCountryLines(l_File);
        assertEquals(11, l_A);
    }

    /**
     * This method is test case1 for borders lines in the model.map file.
     */
    @Test
    public void testFour() {
        File l_File = new File("domination//test_02.model.map");
        MapLineAccess l_Case1 = new MapLineAccess();
        int l_A = l_Case1.getBorderLines(l_File);
        assertEquals(16, l_A);
    }

}
