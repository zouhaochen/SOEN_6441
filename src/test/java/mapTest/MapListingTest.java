package mapTest;

import map.MapListing;
import map.MapValidate;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapListing
 *
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapListingTest {

    File d_File = new File("domination//test_02.map");
    MapListing d_Case = new MapListing();
    /**
     * This method is test case1 for the country connection in the map file.
     */
    @Test
    public void testOne() {

        ArrayList<String> l_list = new ArrayList<String>();
        l_list = d_Case.getCountryList(d_File);
        int l_a = l_list.size();
        assertEquals(4, l_a);
    }

    /**
     * This method is test case1 for the continent connection in the map file.
     */
    @Test
    public void testTwo() {

        ArrayList<String> l_list = new ArrayList<String>();
        l_list = d_Case.getContinentList(d_File);
        int l_a = l_list.size();
        assertEquals(2, l_a);
    }

    /**
     * This method is test case1 for the correspondence between countries and continents in the map file.
     */
    @Test
    public void testThree() {

        HashMap<String, String> l_countrycontinent = new HashMap<String, String>();
        l_countrycontinent = d_Case.getCountryContinent(d_File);
        int l_a = l_countrycontinent.size();
        assertEquals(4, l_a);
    }

    /**
     * This method is test case1 for the country's neighbour list in the map file.
     */
    @Test
    public void testFour() {

        ArrayList<String> l_list = new ArrayList<String>();
        l_list = d_Case.getNeighbour(d_File, "China");
        int l_a = l_list.size();
        assertEquals(4, l_a);
    }
}
