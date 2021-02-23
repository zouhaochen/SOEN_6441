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

    MapLineAccess d_mla = new MapLineAccess();
    MapDetailAccess d_mdl = new MapDetailAccess();
    MapListing d_ml = new MapListing();

    /**
     * This method is to get the list of neighbour of continents in text file
     * @param p_file the map file
     * @return the list of the neighbour of continents
     */
    public ArrayList<String[]> continentlist(File p_file){
        int l_state = 1;

        ArrayList<String> l_continentlist = new ArrayList<String>();
        l_continentlist = d_ml.continentlist(p_file);

        ArrayList<String> l_countrylist = new ArrayList<String>();
        ArrayList<String> l_continentconnection = new ArrayList<String>();

        l_countrylist = d_ml.countrylist(p_file);
        /*System.out.println(l_countrylist);*/
        HashMap<String,String> l_continentcountry = new HashMap<String,String>();
        l_continentcountry = d_ml.getcountrycontinent(p_file);

        ArrayList<String[]> l_totalcontinentconnection = new ArrayList<String[]>();
        for(int l_i = 0; l_i < l_countrylist.size(); l_i++){

            ArrayList<String> l_neighbourlist = new ArrayList<String>();
            l_neighbourlist = d_ml.getneighbour(p_file,l_countrylist.get(l_i));
            /*System.out.println(l_neighbourlist);*/

            l_continentconnection.clear();
            for(int l_j = 0; l_j < l_neighbourlist.size(); l_j++) {
                String l_con = l_continentcountry.get(l_neighbourlist.get(l_j));
                int l_conno = l_continentlist.indexOf(l_con);
                String l_no = Integer.toString(l_conno);
                if(!l_continentconnection.contains(l_no)){
                    l_continentconnection.add(l_no);
                }
            }
            String[] l_connection = new String[l_continentconnection.size()];
            l_connection =(String[]) l_continentconnection.toArray(l_connection);
            l_totalcontinentconnection.add(l_connection);
        }
        String[] b;
        /*for(int i = 0;i<l_totalcontinentconnection.size(); i++){
            b = l_totalcontinentconnection.get(i);
            for(int j=0;j <b.length;j++){
                System.out.print(b[j]);
            }
            System.out.println();
        }*/
        return l_totalcontinentconnection;
    }

    /**
     * This method checks if all the continents given in the map is connected or not
     * @param p_file This parameter contains file path.
     * @return This method returns value 1 if continents are connected or else -1.
     */
    public int validatecontinentconnection(File p_file){
        int l_state = 0;
        int l_flag = 0;
        int l_totalsize = 0;
        int l_borderlength = d_mdl.countrynumber(p_file);
        ArrayList<String[]> l_continentneighbourlist = new ArrayList<String[]>();
        l_continentneighbourlist = continentlist(p_file);

        ArrayList<String> l_continentno = new ArrayList<String>();
        l_continentno = d_ml.continentlist(p_file);


        for(int l_i = 0; l_i < l_borderlength; l_i++){

            String[] l_string = l_continentneighbourlist.get(l_i);
            /*for(int y = 0; y < l_continentneighbourlist.get(l_i).length ; y++){
                System.out.print(l_continentneighbourlist.get(l_i)[y] + " ");
            }*/

            l_totalsize += l_string.length;
            /*System.out.println(l_totalsize);*/

            for(int l_j = 1; l_j < l_string.length; l_j++){

                int l_x = Integer.parseInt((String)l_string[l_j]);
                String[] l_stringconnected = l_continentneighbourlist.get(l_x);
                for(int l_k = 0; l_k < l_stringconnected.length; l_k++){
                    if (l_string[0].equals(l_stringconnected[l_k])) {
                        l_flag++;
                        /*System.out.println(l_flag);*/
                        break;
                    }
                }
            }
        }
        /*System.out.println("l_totalsize:"+l_totalsize +"l_flag:"+ l_flag);*/
        if (l_flag*2 >= l_totalsize){
            l_state = 1;
        }
        else{
            l_state = -1;
        }
        return l_state;
    }

    /**
     * This method checks if all the countries given in the map is connected or not
     * @param p_file This parameter contains file path.
     * @return This method returns value 1 if countries are connected or else -1.
     */
    public int validatecountryconnection(File p_file){

        int l_state = 0;
        int l_flag = 0;
        int l_totalsize = 0;
        int l_borderlength = d_mdl.countrynumber(p_file);
        ArrayList<String[]> l_neighbourlist = new ArrayList<String[]>();
        l_neighbourlist = d_mdl.neighbourlist(p_file);

        for(int l_i = 0; l_i < l_borderlength; l_i++){

            String[] l_string = l_neighbourlist.get(l_i);

            /*for(int y = 0; y < l_neighbourlist.get(l_i).length ; y++){
                System.out.print(l_neighbourlist.get(l_i)[y] + " ");
            }*/

            l_totalsize += l_string.length;
            /*System.out.println(l_totalsize);*/


            for(int l_j = 1; l_j < l_string.length; l_j++){

                int l_x = Integer.parseInt((String)l_string[l_j]);
                String[] l_stringconnected = l_neighbourlist.get(l_x-1);
                for(int l_k = 0; l_k < l_stringconnected.length; l_k++){
                    if (l_string[0].equals(l_stringconnected[l_k])) {
                        l_flag++;
                        /*System.out.println(l_flag);*/
                        break;
                    }
                }
            }
        }
        /*System.out.println("l_totalsize:"+l_totalsize +"l_flag:"+ l_flag);*/
        if (l_flag == l_totalsize-l_borderlength){
            l_state = 1;
        }
        else{
            l_state = -1;
        }

        return l_state;
    }

    /**
    public static void main(String arg[]){

        MapValidate m = new MapValidate();
        File l_file = new File("test_04_incorrect.map");
        File r_file = new File("test_02.map");
        m.validatecontinentconnection(l_file);
    }
     **/
}
