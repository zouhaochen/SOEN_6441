package mapTest;

import map.MapDetailAccess;
import map.MapLineAccess;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapLineAccess
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapDetailAccessTest {

    File d_file = new File("test_02.map");
    MapDetailAccess d_case1 = new MapDetailAccess();

    /**
     * This method is test case1 for continent lines in the map file.
     */
    @Test
    public void testOne() {
        int l_a = d_case1.getContinentNumber(d_file);
        assertEquals(2, l_a);
    }

    /**
     * This method is test case1 for country lines in the map file.
     */
    @Test
    public void testTwo() {
        int l_a = d_case1.getCountryNumber(d_file);
        assertEquals(4, l_a);
    }

    /**
     * This method is test case1 for the neighbour list.
     */
    @Test
    public void testThree() {
        ArrayList<String[]> l_neighbourlist = new ArrayList<String[]>();
        l_neighbourlist = d_case1.getNeighbourList(d_file);
        assertEquals(4, l_neighbourlist.size());
    }

}
