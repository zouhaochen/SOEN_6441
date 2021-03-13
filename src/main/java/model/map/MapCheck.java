package model.map;

import java.io.File;
import java.util.ArrayList;

/**
 * This class implements check the verification of model.map correctness.
 *
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapCheck {

    /**
     * This method implements check the verification of model.map correctness.
     *
     * @param p_File the model.map file.
     * @return return the model.state of the model.map correction.
     */
    public static int check(File p_File) {

        MapValidate l_New = new MapValidate();
        MapDetailAccess l_Mdl = new MapDetailAccess();
        ArrayList<String[]> l_Neighbourlist = new ArrayList<String[]>();
        l_Neighbourlist = l_Mdl.getNeighbourList(p_File);

        int l_State = 0;

        int l_A = l_Mdl.getContinentNumber(p_File);

        //Checking the number of continents
        if (l_A == 0) {
            System.out.println("There is no continents!");
            l_State = 1;
            return l_State;

        }

        //Checking the number of countries
        int l_B = l_Mdl.getCountryNumber(p_File);
        if (l_B == 0) {
            System.out.println("There is no countries!");
            l_State = 2;
            return l_State;
        }

        //Checking the number of borders
        int l_C = l_Neighbourlist.size();
        if (l_C == 0) {
            System.out.println("There is no borders!");
            l_State = 3;
            return l_State;
        }
        if (l_C != l_B) {
            System.out.println("The borders is incomplete!");
            l_State = 4;
            return l_State;
        }

        //Checking countries Connectivity
        int l_Countryconnected = l_New.validateCountryConnection(p_File);
        if (l_Countryconnected == -1) {
            l_State = 5;
            System.out.println("Countries are not connected!");
            return l_State;
        }
        if (l_Countryconnected == 1) {
            System.out.println("Countries are connected!");
        }

        //Checking Continental Connectivity
        int l_Continentconnected = l_New.validateContinentConnection(p_File);
        if (l_Continentconnected == 0) {
            l_State = 6;
            System.out.println("Continents are not connected!");
            return l_State;
        }
        if (l_Continentconnected == 1) {
            System.out.println("Continents are connected!");
        }

        return l_State;
    }

}
