package map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class implements check the verification of map correctness.
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
     * This method is to get the list of neighbour of continents in text file
     *
     * @param p_File the map file
     * @return the list of the neighbour of continents
     */
    public ArrayList<String[]> getContinentBorderList(File p_File) {
        int l_State = 1;

        ArrayList<String> l_ContinentList = new ArrayList<String>();
        l_ContinentList = d_Ml.getContinentList(p_File);

        ArrayList<String> l_CountryList = new ArrayList<String>();
        ArrayList<String> l_ContinentConnection = new ArrayList<String>();
        l_CountryList = d_Ml.getCountryList(p_File);

        HashMap<String, String> l_ContinentCountry = new HashMap<String, String>();
        l_ContinentCountry = d_Ml.getCountryContinent(p_File);

        ArrayList<String[]> l_TotalContinentConnection = new ArrayList<String[]>();
        // To get the continent connection list.
        for (int l_I = 0; l_I < l_CountryList.size(); l_I++) {

            ArrayList<String> l_NeighbourList = new ArrayList<String>();
            //To get each country's connection list.
            l_NeighbourList = d_Ml.getNeighbour(p_File, l_CountryList.get(l_I));


            l_ContinentConnection.clear();
            for (int l_J = 0; l_J < l_NeighbourList.size(); l_J++) {

                //To get the ID of continent which the country is included by get of the form in which countries are stored in the map file.
                String l_Con = l_ContinentCountry.get(l_NeighbourList.get(l_J));
                int l_Conno = l_ContinentList.indexOf(l_Con);
                String l_No = Integer.toString(l_Conno);
                if (!l_ContinentConnection.contains(l_No)) {
                    l_ContinentConnection.add(l_No);
                }
            }
            //To get the whole list of the continent connection.
            String[] l_Connection = new String[l_ContinentConnection.size()];
            l_Connection = (String[]) l_ContinentConnection.toArray(l_Connection);
            l_TotalContinentConnection.add(l_Connection);
        }

        return l_TotalContinentConnection;
    }


    /**
     * This method checks if all the countries given in the map is connected or not
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

}
