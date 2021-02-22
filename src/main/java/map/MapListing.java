package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is to get country and continent list from map file
 *
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapListing extends MapDetailAccess{

    /**
     * This method returns the continent list
     * @return returns total continents
     * @return
     */
    public ArrayList<String> continentlist(File p_file){
        ArrayList<String> l_list = new ArrayList<String>();
        String l_search = " ";
        Scanner l_sc = null;
        try {
            l_sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_i,l_j = 0,l_k;
        int  l_continentnumber = continentnumber(p_file);
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

    public ArrayList<String> countrylist(File p_file){
        ArrayList<String> l_list = new ArrayList<String>();
        String l_search = " ";
        Scanner l_sc = null;
        try {
            l_sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int l_i,l_j = 0,l_k;
        int  l_countrynumber = countrynumber(p_file);
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
     * @return returns continent name and no of countries in continent
     */
    public HashMap<String, Integer> getcontinentinclude(File p_file){

        ArrayList<String> continent = new ArrayList<String>();
        continent = continentlist(p_file);
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
        int  l_countrynumber = countrynumber(p_file);

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
     * @return returns country name of a continent
     */
    public HashMap<String, String> getcountrycontinent(File p_file){
        ArrayList<String> continent = new ArrayList<String>();
        continent = continentlist(p_file);
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
        int  l_countrynumber = countrynumber(p_file);

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
     * @return This method will return array list of neighbour countries.
     */
    public ArrayList<String> getneighbour(File p_file, String p_search){

        int l_a = 0;
        int l_countryno = 0;
        MapDetailAccess l_mda = new MapDetailAccess();
        ArrayList<String[]> l_totalneighbourlist = new ArrayList<String[]>();
        l_totalneighbourlist = l_mda.neighbourlist(p_file);
        ArrayList<String> neighbour = new ArrayList<String>();
        ArrayList<String> country = countrylist(p_file);

        for(int l_i = 0; l_i < country.size(); l_i++){
            if (country.get(l_i).equals(p_search)){

                l_a = country.indexOf(p_search);

                for(int l_j = 0; l_j < l_totalneighbourlist.get(l_a).length; l_j++){
                    l_countryno = Integer.parseInt((String)l_totalneighbourlist.get(l_a)[l_j]);
                    neighbour.add(country.get(l_countryno-1));
                }
            }
        }
        return neighbour;
    }
    /**
    public static void main(String arg[]){

        MapListing m = new MapListing();
        File l_file = new File("test_02.map");
        ArrayList<String> a = new ArrayList<String>();
        try {
            a = m.countrylist(l_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String,Integer> country_continent = new HashMap<String,Integer>();
        country_continent = m.getcontinentinclude(l_file);
        System.out.println(country_continent);

        HashMap<String,String> continentcountry = new HashMap<String,String>();
        continentcountry = m.getcountrycontinent(l_file);
        System.out.println(continentcountry);



        ArrayList<String> neighbour = new ArrayList<String>();
        neighbour = m.getneighbour(l_file,"Japan");
        System.out.println(neighbour);
        for (int i = 0; i < a.size(); i++){
            neighbour = m.getneighbour(l_file,a.get(i));
            System.out.println(neighbour);
        }
    }
     **/
}
