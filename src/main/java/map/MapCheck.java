package map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements check the verification of map correctness.
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapCheck {

    public static int check(File p_file){

        MapValidate l_new = new MapValidate();
        MapDetailAccess l_mdl = new MapDetailAccess();
        ArrayList<String[]> l_neighbourlist = new ArrayList<String[]>();
        l_neighbourlist = l_mdl.neighbourlist(p_file);

        int l_a = l_mdl.continentnumber(p_file);

        if(l_a == 0){
            System.out.println("There is no continents!");
            return 0;
        }
        int l_b = l_mdl.countrynumber(p_file);
        if(l_b == 0){
            System.out.println("There is no countries!");
            return 0;
        }

        int l_c = l_neighbourlist.size();
        if(l_c == 0){
            System.out.println("There is no borders!");
            return 0;
        }
        if(l_c != l_b){
            System.out.println("The borders is incomplete!");
            return 0;
        }

        int l_countryconnected = l_new.validatecountryconnection(p_file);
        if(l_countryconnected == -1){
            System.out.println("Countries are not connected!");
        }
        if(l_countryconnected == 1){
            System.out.println("Countries are connected!");
        }

        int l_continentconnected = l_new.validateConnectedContinents(p_file);
        if(l_continentconnected == 0){
            System.out.println("Continents are not connected!");
        }
        if(l_continentconnected == 1){
            System.out.println("Continents are connected!");
        }

        return 0;
    }

    public static void main(String arg[]){

        MapCheck m = new MapCheck();
        File l_file = new File("test_06_incorrect.map");

        m.check(l_file);
    }


}
