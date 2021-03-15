package mapTest;

import model.map.MapDetailAccess;
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
     * This method is test case1 for total lines in the model.map file.
     */
    @Test
    public void testOne() {
        File l_File = new File("domination//test_02.map");
        MapDetailAccess l_Case1 = new MapDetailAccess();
        int l_A = l_Case1.getLines(l_File);
        assertEquals(25, l_A);
    }

    /**
     * This method is test case1 for continent lines in the model.map file.
     */
    @Test
    public void testTwo() {
        File l_File = new File("domination//test_02.map");
        MapDetailAccess l_Case1 = new MapDetailAccess();
        int l_A = l_Case1.getContinentLines(l_File);
        assertEquals(8, l_A);
    }

    /**
     * This method is test case1 for country lines in the model.map file.
     */
    @Test
    public void testThree() {
        File l_File = new File("domination//test_02.map");
        MapDetailAccess l_Case1 = new MapDetailAccess();
        int l_A = l_Case1.getCountryLines(l_File);
        assertEquals(11, l_A);
    }

    /**
     * This method is test case1 for borders lines in the model.map file.
     */
    @Test
    public void testFour() {
        File l_File = new File("domination//test_02.map");
        MapDetailAccess l_Case1 = new MapDetailAccess();
        int l_A = l_Case1.getBorderLines(l_File);
        assertEquals(16, l_A);
    }

    /**
     * Pre-made test model.map file
     */
    File d_File = new File("domination//test_02.map");
    /**
     * Test Map information access object
     */
    MapDetailAccess d_Case1 = new MapDetailAccess();

    /**
     * This method is test case1 for continent lines in the model.map file.
     */
    @Test
    public void testFive() {
        int l_A = d_Case1.getContinentNumber(d_File);
        assertEquals(2, l_A);
    }

    /**
     * This method is test case1 for country lines in the model.map file.
     */
    @Test
    public void testSix() {
        int l_A = d_Case1.getCountryNumber(d_File);
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the neighbour list.
     */
    @Test
    public void testSeven() {
        ArrayList<String[]> l_NeighbourList = new ArrayList<String[]>();
        l_NeighbourList = d_Case1.getNeighbourList(d_File);
        assertEquals(4, l_NeighbourList.size());
    }

}
