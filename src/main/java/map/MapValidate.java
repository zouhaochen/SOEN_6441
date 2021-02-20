package map;

import java.io.File;
import java.util.ArrayList;

public class MapValidate {

    MapLineAccess l_mla = new MapLineAccess();
    MapDetailAccess l_mdl = new MapDetailAccess();

    /**
     * This method checks if all the countries given in the map is connected or not
     * @param p_file This parameter contains file path.
     * @return This method returns value 1 if continents are connected or else -1.
     */
    public int validatecountryconnection(File p_file){

        int l_state = 0;
        int l_flag = 0;
        int l_totalsize = 0;
        int l_borderlength = l_mdl.countrynumber(p_file);
        ArrayList<String[]> l_neighbourlist = new ArrayList<String[]>();
        l_neighbourlist = l_mdl.neighbourlist(p_file);

        for(int l_i = 0; l_i < l_borderlength; l_i++){

            String[] l_string = l_neighbourlist.get(l_i);
            l_totalsize += l_string.length;
            for(int l_j = 1; l_j < l_string.length; l_j++){

                int l_x = Integer.parseInt((String)l_string[l_j]);
                String[] l_stringconnected = l_neighbourlist.get(l_x-1);
                for(int l_k = 0; l_k < l_stringconnected.length; l_k++){
                    if (l_string[0].equals(l_stringconnected[l_k])) {
                        l_flag++;
                        System.out.println(l_flag);
                        break;
                    }
                }
            }
        }
        /*System.out.println(l_totalsize);*/
        if (l_flag == l_totalsize-l_borderlength){
            l_state = 1;
        }
        else{
            l_state = -1;
        }

        return l_state;
    }

    /*
    public static void main(String arg[]){

        MapValidate m = new MapValidate();
        File l_file = new File("test_02.map");
        ArrayList<String[]> a = new ArrayList<String[]>();
        int c = m.validatecountryconnection(l_file);
        System.out.println(c);

    }*/
}
