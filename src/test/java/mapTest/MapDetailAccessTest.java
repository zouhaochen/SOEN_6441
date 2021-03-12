package mapTest;

import model.MapDetailAccess;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapLineAccess
 *
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapDetailAccessTest {

    /**
     * Pre-made test model.map file
     */
    File d_File = new File("domination//test_02.model.map");
    /**
     * Test Map information access object
     */
    MapDetailAccess d_Case1 = new MapDetailAccess();

    /**
     * This method is test case1 for continent lines in the model.map file.
     */
    @Test
    public void testOne() {
        int l_A = d_Case1.getContinentNumber(d_File);
        assertEquals(2, l_A);
    }

    /**
     * This method is test case1 for country lines in the model.map file.
     */
    @Test
    public void testTwo() {
        int l_A = d_Case1.getCountryNumber(d_File);
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the neighbour list.
     */
    @Test
    public void testThree() {
        ArrayList<String[]> l_NeighbourList = new ArrayList<String[]>();
        l_NeighbourList = d_Case1.getNeighbourList(d_File);
        assertEquals(4, l_NeighbourList.size());
    }

}
