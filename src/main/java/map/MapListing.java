package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is to get some lists and correspondence from the map file
 * Including: the continent list, the total country list , the country list from a continent and the list of neighbour countries.
 *
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapListing extends MapDetailAccess {

    /**
     * This method returns the continent list
     *
     * @param p_File contains the file path of a map
     * @return l_list the continent list
     */
    public ArrayList<String> getContinentList(File p_File) {
        ArrayList<String> l_List = new ArrayList<String>();
        String l_Search = " ";
        Scanner l_Sc = null;
        try {
            l_Sc = new Scanner(p_File);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_I, l_J = 0;
        // Number of continents obtained.
        int l_continentnumber = getContinentNumber(p_File);
        while (l_Sc.hasNextLine()) {
            if (l_J == 1) {
                break;
            }
            l_Search = l_Sc.nextLine();
            if (l_Search.equals("[continents]")) {
                for (l_I = 0; l_I < l_continentnumber; l_I++) {
                    if (!l_Sc.hasNextLine()) {
                        break;
                    }
                    // Get only the name of the continent in the map file.
                    String l_Text = l_Sc.nextLine();
                    String[] l_A = l_Text.split(" ");
                    l_List.add(l_A[0]);
                }
                l_J++;
            }
        }
        return l_List;
    }

    /**
     * This method returns the country list
     *
     * @param p_File contains the file path of a map
     * @return l_list the continent list
     */
    public ArrayList<String> getCountryList(File p_File) {
        ArrayList<String> l_List = new ArrayList<String>();
        String l_Search = " ";
        Scanner l_Sc = null;
        try {
            l_Sc = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        int l_I, l_J = 0, l_K;
        // Number of countries obtained.
        int l_Countrynumber = getCountryNumber(p_File);
        while (l_Sc.hasNextLine()) {
            if (l_J == 1) {
                break;
            }
            l_Search = l_Sc.nextLine();
            if (l_Search.equals("[countries]")) {
                for (l_I = 0; l_I < l_Countrynumber; l_I++) {
                    if (!l_Sc.hasNextLine()) {
                        break;
                    }
                    // Get only the name of the country in the map file.
                    String l_Text = l_Sc.nextLine();
                    String[] l_A = l_Text.split(" ");
                    l_List.add(l_A[1]);
                }
                l_J++;
            }
        }
        return l_List;
    }

    /**
     * This method returns continent and countries
     *
     * @param p_File contains the file path of a map
     * @return continentcountry continent name and no of countries in continent
     */
    public HashMap<String, Integer> getContinentInclude(File p_File) {
        ArrayList<String> l_Continent = new ArrayList<String>();
        l_Continent = getContinentList(p_File);
        HashMap<String, Integer> l_Continentcountry = new HashMap<String, Integer>();
        int l_I = 0;
        String l_Search = " ";
        Scanner l_Sc = null;
        try {
            l_Sc = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        int l_J = 0, l_K;
        // Number of countries obtained.
        int l_Countrynumber = getCountryNumber(p_File);

        while (l_Sc.hasNextLine()) {
            if (l_J == 1) {
                break;
            }
            l_Search = l_Sc.nextLine();
            //Locating where the country is stored in the file.
            if (l_Search.equals("[countries]")) {
                for (l_I = 0; l_I < l_Countrynumber; l_I++) {
                    if (!l_Sc.hasNextLine()) {
                        break;
                    }
                    String l_Text = l_Sc.nextLine();
                    String[] l_A = l_Text.split(" ");
                    int l_Continentno = Integer.parseInt((String) l_A[2]);

                    //If there is no continent in the l_continentcountry list, then get the name of continent.
                    if (l_Continentcountry.containsKey(l_Continent.get(l_Continentno - 1))) {
                        l_Continentcountry.put(l_Continent.get(l_Continentno - 1), l_Continentcountry.get(l_Continent.get(l_Continentno - 1)) + 1);
                    } else {
                        //If the continent is existd, the number of countries included in the continent plus one.
                        l_Continentcountry.put(l_Continent.get(l_Continentno - 1), 1);
                    }
                }
                l_J++;
            }
        }
        return l_Continentcountry;
    }

    /**
     * This method returns country from a continent
     *
     * @param p_File contains the file path of a map
     * @return countrycontinent country name of a continent
     */
    public HashMap<String, String> getCountryContinent(File p_File) {
        ArrayList<String> l_Continent = new ArrayList<String>();
        l_Continent = getContinentList(p_File);
        HashMap<String, String> l_Countrycontinent = new HashMap<String, String>();
        int l_I = 0;
        String l_Search = " ";
        Scanner l_Sc = null;
        try {
            l_Sc = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        int l_J = 0;
        int l_Countrynumber = getCountryNumber(p_File);
        // To get the location of [countries] in the map file.
        while (l_Sc.hasNextLine()) {
            if (l_J == 1) {
                break;
            }
            l_Search = l_Sc.nextLine();
            //Locating where the country is stored in the file.
            if (l_Search.equals("[countries]")) {
                for (l_I = 0; l_I < l_Countrynumber; l_I++) {
                    if (!l_Sc.hasNextLine()) {
                        break;
                    }
                    String l_Text = l_Sc.nextLine();
                    String[] l_A = l_Text.split(" ");
                    //Get the country's name and the continent which is included this country,
                    int l_Continentno = Integer.parseInt((String) l_A[2]);
                    l_Countrycontinent.put(l_A[1], l_Continent.get(l_Continentno - 1));
                }
                l_J++;
            }
        }
        return l_Countrycontinent;
    }

    /**
     * This method is used to get neighbour countries for given country.
     *
     * @param p_File   This is the map file.
     * @param p_Search This parameter contains country name.
     * @return neighbour This method will return the array list of neighbour countries.
     */
    public ArrayList<String> getNeighbour(File p_File, String p_Search) {

        int l_A = 0;
        int l_Countryno = 0;
        MapDetailAccess l_Mda = new MapDetailAccess();
        ArrayList<String[]> l_Totalneighbourlist = new ArrayList<String[]>();
        l_Totalneighbourlist = l_Mda.getNeighbourList(p_File);
        ArrayList<String> l_Neighbour = new ArrayList<String>();
        ArrayList<String> l_Country = getCountryList(p_File);

        for (int l_I = 0; l_I < l_Country.size(); l_I++) {
            if (l_Country.get(l_I).equals(p_Search)) {
                // To get the location of the country
                l_A = l_Country.indexOf(p_Search);

                for (int l_J = 0; l_J < l_Totalneighbourlist.get(l_A).length; l_J++) {
                    //To change the ID of country to the couontry's name, because the ID is stored in the [borders] parts.
                    l_Countryno = Integer.parseInt((String) l_Totalneighbourlist.get(l_A)[l_J]);
                    try {
                        l_Neighbour.add(l_Country.get(l_Countryno - 1));
                    } catch (IndexOutOfBoundsException l_E) {
                        l_E.printStackTrace();
                    }
                }
            }
        }
        return l_Neighbour;
    }
}