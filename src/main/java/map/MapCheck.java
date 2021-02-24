package map;

import java.io.File;
import java.util.ArrayList;

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
        l_neighbourlist = l_mdl.getNeighbourList(p_file);

        int l_state = 0;

        int l_a = l_mdl.getContinentNumber(p_file);

        //Checking the number of continents
        if(l_a == 0){
            System.out.println("There is no continents!");
            l_state = 1;
            return l_state;

        }

        //Checking the number of countries
        int l_b = l_mdl.getCountryNumber(p_file);
        if(l_b == 0){
            System.out.println("There is no countries!");
            l_state = 2;
            return l_state;
        }

        //Checking the number of borders
        int l_c = l_neighbourlist.size();
        if(l_c == 0){
            System.out.println("There is no borders!");
            l_state = 3;
            return l_state;
        }
        if(l_c != l_b){
            System.out.println("The borders is incomplete!");
            l_state = 4;
            return l_state;
        }

        //Checking countries Connectivity
        int l_countryconnected = l_new.validateCountryConnection(p_file);
        if(l_countryconnected == -1){
            l_state = 5;
            System.out.println("Countries are not connected!");
            return l_state;
        }
        if(l_countryconnected == 1){
            System.out.println("Countries are connected!");
        }

        //Checking Continental Connectivity
        int l_continentconnected = l_new.validateContinentConnection(p_file);
        if(l_continentconnected == 0){
            l_state = 6;
            System.out.println("Continents are not connected!");
            return l_state;
        }
        if(l_continentconnected == 1){
            System.out.println("Continents are connected!");
        }

        return l_state;
    }

}
