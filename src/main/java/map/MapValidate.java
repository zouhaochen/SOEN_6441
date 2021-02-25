package map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class implements check the verification of map correctness.
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapValidate {

    MapDetailAccess d_Mdl = new MapDetailAccess();
    MapListing d_Ml = new MapListing();

    /**
     * This method is to get the list of neighbour of continents in text file
     * @param p_file the map file
     * @return the list of the neighbour of continents
     */
    public ArrayList<String[]> getContinentBorderList(File p_file){
        int l_state = 1;

        ArrayList<String> l_continentlist = new ArrayList<String>();
        l_continentlist = d_Ml.getContinentList(p_file);

        ArrayList<String> l_countrylist = new ArrayList<String>();
        ArrayList<String> l_continentconnection = new ArrayList<String>();
        l_countrylist = d_Ml.getCountryList(p_file);

        HashMap<String,String> l_continentcountry = new HashMap<String,String>();
        l_continentcountry = d_Ml.getCountryContinent(p_file);

        ArrayList<String[]> l_totalcontinentconnection = new ArrayList<String[]>();
        // To get the continent connection list.
        for(int l_i = 0; l_i < l_countrylist.size(); l_i++){

            ArrayList<String> l_neighbourlist = new ArrayList<String>();
            //To get each country's connection list.
            l_neighbourlist = d_Ml.getNeighbour(p_file,l_countrylist.get(l_i));


            l_continentconnection.clear();
            for(int l_j = 0; l_j < l_neighbourlist.size(); l_j++) {

                //To get the ID of continent which the country is included by get of the form in which countries are stored in the map file.
                String l_con = l_continentcountry.get(l_neighbourlist.get(l_j));
                int l_conno = l_continentlist.indexOf(l_con);
                String l_no = Integer.toString(l_conno);
                if(!l_continentconnection.contains(l_no)){
                    l_continentconnection.add(l_no);
                }
            }
            //To get the whole list of the continent connection.
            String[] l_connection = new String[l_continentconnection.size()];
            l_connection =(String[]) l_continentconnection.toArray(l_connection);
            l_totalcontinentconnection.add(l_connection);
        }

        return l_totalcontinentconnection;
    }


    /**
     * This method checks if all the countries given in the map is connected or not
     * @param p_file This parameter contains file path.
     * @return This method returns value 1 if countries are connected or else -1.
     */
    public int validateCountryConnection(File p_file){

        int l_state = 0;
        int l_flag = 0;
        int l_totalsize = 0;
        int l_borderlength = d_Mdl.getCountryNumber(p_file);
        int l_couuntrylength = d_Mdl.getCountryNumber(p_file);
        //To get the whole country connection list.
        ArrayList<String[]> l_neighbourlist = new ArrayList<String[]>();
        l_neighbourlist = d_Mdl.getNeighbourList(p_file);

        for(int l_i = 0; l_i < l_couuntrylength; l_i++){
            if(l_couuntrylength > l_neighbourlist.size()){
                break;
            }

            //First, get one country's neighbour list.
            String[] l_string = l_neighbourlist.get(l_i);
            l_totalsize += l_string.length;
            //Iterate through this list.
            for(int l_j = 1; l_j < l_string.length; l_j++){

                int l_x = Integer.parseInt((String)l_string[l_j]);

                if(l_x > l_neighbourlist.size()){
                    break;
                }
                String[] l_stringconnected = l_neighbourlist.get(l_x-1);

                for(int l_k = 0; l_k < l_stringconnected.length; l_k++){
                    //If country A's neighbour list has country B and Country B's neighbour list has country A,
                    //then we can say A and B is connected.
                    if (l_string[0].equals(l_stringconnected[l_k])) {
                        l_flag++;

                        break;
                    }
                }
            }
        }

        if (l_flag == l_totalsize-l_borderlength){
            l_state = 1;
        }
        else{
            l_state = -1;
        }

        return l_state;
    }

    /**
     * This method checks if all the continents given in the map is connected or not
     * @param p_file This parameter contains file path.
     * @return This method returns value 1 if countries are connected or else 0.
     */
    public int validateContinentConnection(File p_file){
        ArrayList<String> l_neighbourcountry = new ArrayList<String>();
        ArrayList<String> l_continentlist = d_Ml.getContinentList(p_file);
        ArrayList<String> l_newcontinentlist = l_continentlist;
        ArrayList<String> country_list = d_Ml.getCountryList(p_file);
        HashMap<String,String> country_continent = d_Ml.getCountryContinent(p_file);
        int l_n = country_list.size();
        String l_country=" ", continent = " ", continent1 = " ";
        int l_flag=0, l_a=0;

        //To ensure all continent are connected, Each continent have to connect with another new continent.
        //First, get the country list and iterate the list.
        for(int l_i = 0; l_i < l_n; l_i++){

            if(l_newcontinentlist.isEmpty()) {
                //If new_continent_list is empty, all continents are connected.
                l_a = 1;
                break;
            }
            l_country = country_list.get(l_i);

            //Get the first data of <country, continent>.
            if(l_flag == 0) {
                continent = country_continent.get(l_country);
                l_flag = 1;
            }

            l_neighbourcountry = d_Ml.getNeighbour(p_file, l_country);

            l_neighbourcountry.remove(0);

            for(int l_j = 0; l_j < l_neighbourcountry.size(); l_j++){

                continent1 = country_continent.get(l_neighbourcountry.get(l_j));

                //If the neighbour of country A belongs to another continent, the remove the continent which included country A.
                //Then, Let the similar continent be the target to check the connection.
                 if(country_continent.get(l_country) != country_continent.get(l_neighbourcountry.get(l_j))){
                     l_newcontinentlist.remove(continent);

                    continent = continent1;
                }
            }
        }
        if(l_newcontinentlist.isEmpty()) {
            //If new_continent_list is empty, all continents are connected.
            l_a = 1;
        }

        return l_a;
    }

}
