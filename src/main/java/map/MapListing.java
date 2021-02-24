package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is to get some lists and correspondence from the map file
 * Including: the continent list, the total country list , the country list from a continent and the list of neighbour countries.
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapListing extends MapDetailAccess{

    /**
     * This method returns the continent list
     * @param p_file contains the file path of a map
     * @return l_list the continent list
     */
    public ArrayList<String> getContinentList(File p_file){
        ArrayList<String> l_list = new ArrayList<String>();
        String l_search = " ";
        Scanner l_sc = null;
        try {
            l_sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_i,l_j = 0,l_k;
        int  l_continentnumber = getContinentNumber(p_file);
        while(l_sc.hasNextLine()){
            if (l_j == 1){
                break;
            }
            l_search = l_sc.nextLine();
            if(l_search.equals("[continents]")){
                for(l_i = 0; l_i < l_continentnumber; l_i++){
                    if (!l_sc.hasNextLine()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    String[] l_a = l_text.split(" ");
                    l_list.add(l_a[0]);
                }
                l_j++;
            }
        }
        return l_list;
    }

    /**
     * This method returns the country list
     * @param p_file contains the file path of a map
     * @return l_list the continent list
     */
    public ArrayList<String> getCountryList(File p_file){
        ArrayList<String> l_list = new ArrayList<String>();
        String l_search = " ";
        Scanner l_sc = null;
        try {
            l_sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_i,l_j = 0,l_k;
        int  l_countrynumber = getCountryNumber(p_file);
        while(l_sc.hasNextLine()){
            if (l_j == 1){
                break;
            }
            l_search = l_sc.nextLine();
            if(l_search.equals("[countries]")){
                for(l_i = 0; l_i < l_countrynumber; l_i++){
                    if (!l_sc.hasNextLine()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    String[] l_a = l_text.split(" ");
                    l_list.add(l_a[1]);
                }
                l_j++;
            }
        }
        return l_list;
    }

    /**
     * This method returns continent and countries
     * @param p_file contains the file path of a map
     * @return continentcountry continent name and no of countries in continent
     */
    public HashMap<String, Integer> getContinentInclude(File p_file){

        ArrayList<String> continent = new ArrayList<String>();
        continent = getContinentList(p_file);
        HashMap<String, Integer> continentcountry = new HashMap<String, Integer>();
        int l_i = 0;

        String l_search = " ";
        Scanner l_sc = null;
        try {
            l_sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_j = 0,l_k;
        int  l_countrynumber = getCountryNumber(p_file);

        while(l_sc.hasNextLine()){
            if (l_j == 1){
                break;
            }
            l_search = l_sc.nextLine();
            if(l_search.equals("[countries]")){
                for(l_i = 0; l_i < l_countrynumber; l_i++){
                    if (!l_sc.hasNextLine()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    String[] l_a = l_text.split(" ");
                    int continentno =  Integer.parseInt((String)l_a[2]);

                    if (continentcountry.containsKey(continent.get(continentno-1))){
                        continentcountry.put(continent.get(continentno-1),continentcountry.get(continent.get(continentno-1))+1);
                    }
                    else{
                        continentcountry.put(continent.get(continentno-1), 1);
                    }
                }
                l_j++;
            }
        }
        return continentcountry;
    }

    /**
     * This method returns country from a continent
     * @param p_file contains the file path of a map
     * @return countrycontinent country name of a continent
     */
    public HashMap<String, String> getCountryContinent(File p_file){
        ArrayList<String> continent = new ArrayList<String>();
        continent = getContinentList(p_file);
        HashMap<String, String> countrycontinent = new HashMap<String, String>();
        int l_i = 0;

        String l_search = " ";
        Scanner l_sc = null;
        try {
            l_sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_j = 0,l_k;
        int  l_countrynumber = getCountryNumber(p_file);

        while(l_sc.hasNextLine()){
            if (l_j == 1){
                break;
            }
            l_search = l_sc.nextLine();
            if(l_search.equals("[countries]")){
                for(l_i = 0; l_i < l_countrynumber; l_i++){
                    if (!l_sc.hasNextLine()){
                        break;
                    }
                    String l_text = l_sc.nextLine();
                    String[] l_a = l_text.split(" ");
                    int continentno =  Integer.parseInt((String)l_a[2]);
                    countrycontinent.put(l_a[1], continent.get(continentno-1));
                }
                l_j++;
            }
        }
        return countrycontinent;
    }

    /**
     * This method is used to get neighbour countries for given country.
     * @param p_file This is the map file.
     * @param p_search This parameter contains country name.
     * @return neighbour This method will return array list of neighbour countries.
     */
    public ArrayList<String> getNeighbour(File p_file, String p_search){

        int l_a = 0;
        int l_countryno = 0;
        MapDetailAccess l_mda = new MapDetailAccess();
        ArrayList<String[]> l_totalneighbourlist = new ArrayList<String[]>();
        l_totalneighbourlist = l_mda.getNeighbourList(p_file);
        ArrayList<String> neighbour = new ArrayList<String>();
        ArrayList<String> country = getCountryList(p_file);

        for(int l_i = 0; l_i < country.size(); l_i++){
            if (country.get(l_i).equals(p_search)){
                l_a = country.indexOf(p_search);



                for(int l_j = 0; l_j < l_totalneighbourlist.get(l_a).length; l_j++){

                    l_countryno = Integer.parseInt((String)l_totalneighbourlist.get(l_a)[l_j]);


                    try {
                        neighbour.add(country.get(l_countryno-1));
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();

                    }

                }
            }
        }
        return neighbour;
    }
}