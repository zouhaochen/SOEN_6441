package mapTest;

import model.MapListing;
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

    /**
     * This method is test case1 for the country connection in the model.map file.
     */
    @Test
    public void testOne() {

        File l_file = new File("domination//test_02.model.map");
        MapListing l_case = new MapListing();
        ArrayList<String> l_List;
        l_List = l_case.getCountryList(l_file);
        int l_A = l_List.size();
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the continent connection in the model.map file.
     */
    @Test
    public void testTwo() {
        File l_file = new File("domination//test_02.model.map");
        MapListing l_case = new MapListing();
        ArrayList<String> l_List;
        l_List = l_case.getContinentList(l_file);
        int l_A = l_List.size();
        assertEquals(2, l_A);
    }

    /**
     * This method is test case1 for the correspondence between countries and continents in the model.map file.
     */
    @Test
    public void testThree() {
        File l_file = new File("domination//test_02.model.map");
        MapListing l_case = new MapListing();
        HashMap<String, String> l_CountryContinent;
        l_CountryContinent = l_case.getCountryContinent(l_file);
        int l_A = l_CountryContinent.size();
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the country's neighbour list in the model.map file.
     */
    @Test
    public void testFour() {
        File l_file = new File("domination//test_02.model.map");
        MapListing l_case = new MapListing();
        ArrayList<String> l_List;
        l_List = l_case.getNeighbour(l_file, "China");
        int l_A = l_List.size();
        assertEquals(4, l_A);
    }
}
