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
     * test all contents are get
     */
    public void testPrintTable() throws Exception {
        MapGraph l_test = new MapGraph();
        String l_lineTest = "";

        boolean l_borderTest = l_test.equals(l_lineTest);
        boolean l_countryTest = l_test.equals(l_lineTest);
        boolean l_continentTest = l_test.equals(l_lineTest);
        map.MapGraph.printTable("testmap.map");
        assertFalse(l_borderTest);
        assertFalse(l_countryTest);
        assertFalse(l_continentTest);
    }
}