package model.map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class implements check the verification of model.map correctness.
 *
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapValidate {

    /**
     * The mdl MapDetailAccess.
     */
    MapDetailAccess d_Mdl = new MapDetailAccess();
    /**
     * The ml MapListing.
     */
    MapListing d_Ml = new MapListing();


    /**
     * This method checks if all the countries given in the model.map is connected or not
     *
     * @param p_File This parameter contains file path.
     * @return This method returns value 1 if countries are connected or else -1.
     */
    public int validateCountryConnection(File p_File) {

        int l_State = 0;
        int l_Flag = 0;
        int l_TotalSize = 0;
        int l_BorderLength = d_Mdl.getCountryNumber(p_File);
        int l_CountryLength = d_Mdl.getCountryNumber(p_File);
        //To get the whole country connection list.
        ArrayList<String[]> l_NeighbourList = new ArrayList<String[]>();
        l_NeighbourList = d_Mdl.getNeighbourList(p_File);

        for (int l_I = 0; l_I < l_CountryLength; l_I++) {
            if (l_CountryLength > l_NeighbourList.size()) {
                break;
            }

            //First, get one country's neighbour list.
            String[] l_String = l_NeighbourList.get(l_I);
            l_TotalSize += l_String.length;
            //Iterate through this list.
            for (int l_J = 1; l_J < l_String.length; l_J++) {

                int l_X = Integer.parseInt((String) l_String[l_J]);

                if (l_X > l_NeighbourList.size()) {
                    break;
                }
                String[] l_StringConnected = l_NeighbourList.get(l_X - 1);

                for (int l_K = 0; l_K < l_StringConnected.length; l_K++) {
                    //If country A's neighbour list has country B and Country B's neighbour list has country A,
                    //then we can say A and B is connected.
                    if (l_String[0].equals(l_StringConnected[l_K])) {
                        l_Flag++;

                        break;
                    }
                }
            }
        }

        if (l_Flag == l_TotalSize - l_BorderLength) {
            l_State = 1;
        } else {
            l_State = -1;
        }

        return l_State;
    }

    /**
     * Validate continent connection int.
     *
     * @param p_File the p file
     * @return the int
     */
    public int validateContinentConnection(File p_File) {
        ArrayList<String> l_NeighbourCountry = new ArrayList<String>();
        ArrayList<String> l_ContinentList = d_Ml.getContinentList(p_File);
        ArrayList<String> l_NewContinentList = l_ContinentList;
        ArrayList<String> l_CountryList = d_Ml.getCountryList(p_File);
        HashMap<String, String> l_CountryContinent = d_Ml.getCountryContinent(p_File);
        int l_N = l_CountryList.size();
        String l_Country = " ", l_Continent = " ", l_Continent1 = " ";
        int l_Flag = 0, l_A = 0;

        //To ensure all continent are connected, Each continent have to connect with another new continent.
        //First, get the country list and iterate the list.
        for (int l_I = 0; l_I < l_N; l_I++) {

            if (l_NewContinentList.isEmpty()) {
                //If new_continent_list is empty, all continents are connected.
                l_A = 1;
                break;
            }
            l_Country = l_CountryList.get(l_I);

            //Get the first data of <country, continent>.
            if (l_Flag == 0) {
                l_Continent = l_CountryContinent.get(l_Country);
                l_Flag = 1;
            }

            l_NeighbourCountry = d_Ml.getNeighbour(p_File, l_Country);

            l_NeighbourCountry.remove(0);

            for (int l_j = 0; l_j < l_NeighbourCountry.size(); l_j++) {

                l_Continent1 = l_CountryContinent.get(l_NeighbourCountry.get(l_j));

                //If the neighbour of country A belongs to another continent, the remove the continent which included country A.
                //Then, Let the similar continent be the target to check the connection.
                if (l_CountryContinent.get(l_Country) != l_CountryContinent.get(l_NeighbourCountry.get(l_j))) {
                    l_NewContinentList.remove(l_Continent);

                    l_Continent = l_Continent1;
                }
            }
        }
        if (l_NewContinentList.isEmpty()) {
            //If new_continent_list is empty, all continents are connected.
            l_A = 1;
        }

        return l_A;
    }

    /**
     * This method implements check the verification of model.map correctness.
     *
     * @param p_File the model.map file.
     * @return return the model.state of the model.map correction.
     */
    public static int check(File p_File) {

        MapValidate l_New = new MapValidate();
        MapDetailAccess l_Mdl = new MapDetailAccess();
        ArrayList<String[]> l_Neighbourlist = new ArrayList<String[]>();
        l_Neighbourlist = l_Mdl.getNeighbourList(p_File);

        int l_State = 0;

        int l_A = l_Mdl.getContinentNumber(p_File);

        //Checking the number of continents
        if (l_A == 0) {
            System.out.println("There is no continents!");
            l_State = 1;
            return l_State;

        }

        //Checking the number of countries
        int l_B = l_Mdl.getCountryNumber(p_File);
        if (l_B == 0) {
            System.out.println("There is no countries!");
            l_State = 2;
            return l_State;
        }

        //Checking the number of borders
        int l_C = l_Neighbourlist.size();
        if (l_C == 0) {
            System.out.println("There is no borders!");
            l_State = 3;
            return l_State;
        }
        if (l_C != l_B) {
            System.out.println("The borders is incomplete!");
            l_State = 4;
            return l_State;
        }

        //Checking countries Connectivity
        int l_Countryconnected = l_New.validateCountryConnection(p_File);
        if (l_Countryconnected == -1) {
            l_State = 5;
            System.out.println("Countries are not connected!");
            return l_State;
        }
        if (l_Countryconnected == 1) {
            System.out.println("Countries are connected!");
        }

        //Checking Continental Connectivity
        int l_Continentconnected = l_New.validateContinentConnection(p_File);
        if (l_Continentconnected == 0) {
            l_State = 6;
            System.out.println("Continents are not connected!");
            return l_State;
        }
        if (l_Continentconnected == 1) {
            System.out.println("Continents are connected!");
        }

        return l_State;
    }
}
