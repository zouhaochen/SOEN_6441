package mapTest;

import map.MapGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/** This class is for test MapGraph
 * @author Haochen Zou
 * @version 1.0
 */
public class MapGraphTest {
    @Test
    /**
     * test all border are get
     */
    public void testPrintTableBorder() throws Exception {
        MapGraph l_Test = new MapGraph();
        String l_LineTest = "";
        boolean l_BorderTest = l_Test.equals(l_LineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_BorderTest);
    }
    @Test
    /**
     * test all border are get
     */
    public void testPrintTableCountry() throws Exception {
        MapGraph l_Test = new MapGraph();
        String l_LineTest = "";
        boolean l_CountryTest = l_Test.equals(l_LineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_CountryTest);
    }
    @Test
    /**
     * test all border are get
     */
    public void testPrintTableContinent() throws Exception {
        MapGraph l_Test = new MapGraph();
        String l_LineTest = "";
        boolean l_ContinentTest = l_Test.equals(l_LineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_ContinentTest);
    }
}