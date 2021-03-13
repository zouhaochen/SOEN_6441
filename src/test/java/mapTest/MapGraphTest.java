package mapTest;

import model.map.MapGraph;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is for test MapGraph
 *
 * @author Haochen Zou
 * @version 1.0
 */
public class MapGraphTest {
    /**
     * test all border are get
     * @throws Exception file not find
     */
    @Test
    public void testPrintTableBorder() throws Exception {
        MapGraph l_Test = new MapGraph();
        String l_LineTest = "";
        boolean l_BorderTest = l_Test.equals(l_LineTest);
        MapGraph.printTable("testmap.model.map");
        assertFalse(l_BorderTest);
    }

    /**
     * test all countries are get
     * @throws Exception file not find
     */
    @Test
    public void testPrintTableCountry() throws Exception {
        MapGraph l_Test = new MapGraph();
        String l_LineTest = "";
        boolean l_CountryTest = l_Test.equals(l_LineTest);
        MapGraph.printTable("testmap.model.map");
        assertFalse(l_CountryTest);
    }

    /**
     * test all continents are get
     * @throws Exception file not find
     */
    @Test
    public void testPrintTableContinent() throws Exception {
        MapGraph l_Test = new MapGraph();
        String l_LineTest = "";
        boolean l_ContinentTest = l_Test.equals(l_LineTest);
        MapGraph.printTable("testmap.model.map");
        assertFalse(l_ContinentTest);
    }
}