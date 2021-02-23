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

    public static void check(File p_file){

        MapValidate l_new = new MapValidate();

        int l_continentconnected = 0;
        int l_countryconnected = 0;
        l_continentconnected = l_new.validatecontinentconnection(p_file);
        l_countryconnected = l_new.validatecountryconnection(p_file);

        if(l_continentconnected == 1){
            System.out.println("All continents are connected!");
        }
        if(l_countryconnected == 1){
            System.out.println("All countries are connected!");
        }

        if(l_continentconnected != 1){
            System.out.println("!!!Continents are NOT connected!");
        }
        if(l_countryconnected != 1){
            System.out.println("!!!Countries are NOT connected!");
        }

    }

    public static void main(String arg[]){

        MapCheck m = new MapCheck();
        File l_file = new File("test_08_incorrect.map");

        m.check(l_file);

    }


}
