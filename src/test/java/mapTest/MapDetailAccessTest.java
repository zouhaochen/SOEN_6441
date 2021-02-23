package mapTest;

import map.MapDetailAccess;
import map.MapLineAccess;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapLineAccess
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapDetailAccessTest {

    File l_file = new File("test_02.map");
    MapDetailAccess l_case1 = new MapDetailAccess();

    /**
     * This method is test case1 for continent lines in the map file.
     */
    @Test
    public void test1() {
        int l_a = l_case1.continentnumber(l_file);
        assertEquals(2, l_a);
    }

    /**
     * This method is test case1 for country lines in the map file.
     */
    @Test
    public void test2() {
        int l_a = l_case1.countrynumber(l_file);
        assertEquals(4, l_a);
    }

}
