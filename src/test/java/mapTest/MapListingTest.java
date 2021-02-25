package mapTest;

import map.MapListing;
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

        ArrayList<String> l_List = new ArrayList<String>();
        l_List = d_Case.getCountryList(d_File);
        int l_A = l_List.size();
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the continent connection in the map file.
     */
    @Test
    public void testTwo() {

        ArrayList<String> l_List = new ArrayList<String>();
        l_List = d_Case.getContinentList(d_File);
        int l_A = l_List.size();
        assertEquals(2, l_A);
    }

    /**
     * This method is test case1 for the correspondence between countries and continents in the map file.
     */
    @Test
    public void testThree() {

        HashMap<String, String> l_CountryContinent = new HashMap<String, String>();
        l_CountryContinent = d_Case.getCountryContinent(d_File);
        int l_A = l_CountryContinent.size();
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the country's neighbour list in the map file.
     */
    @Test
    public void testFour() {

        ArrayList<String> l_List = new ArrayList<String>();
        l_List = d_Case.getNeighbour(d_File, "China");
        int l_A = l_List.size();
        assertEquals(4, l_A);
    }
}
