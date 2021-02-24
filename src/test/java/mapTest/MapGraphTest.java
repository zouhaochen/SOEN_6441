package mapTest;

import map.MapGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/** This class is for test MapGraph
 * @Auther: Haochen Zou
 * @version: 1.0
 */
public class MapGraphTest {
    @Test
    /**
     * test all border are get
     */
    public void testPrintTableBorder() throws Exception {
        MapGraph l_test = new MapGraph();
        String l_lineTest = "";
        boolean l_borderTest = l_test.equals(l_lineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_borderTest);
    }
    @Test
    /**
     * test all border are get
     */
    public void testPrintTableCountry() throws Exception {
        MapGraph l_test = new MapGraph();
        String l_lineTest = "";
        boolean l_countryTest = l_test.equals(l_lineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_countryTest);
    }
    @Test
    /**
     * test all border are get
     */
    public void testPrintTableContinent() throws Exception {
        MapGraph l_test = new MapGraph();
        String l_lineTest = "";
        boolean l_continentTest = l_test.equals(l_lineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_continentTest);
    }
}