package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to get the number of continents , countries and the border list.
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapDetailAccess {


    public MapLineAccess d_Mla = new MapLineAccess();

    /**
     This method is to calculate continent lines in text file
     * @param p_file the map file
     * @return number of continent lines
     *
     */
    public int getContinentNumber(File p_file){
        int l_tlines = d_Mla.getLines(p_file);
        int l_continentlines = d_Mla.getContinentLines(p_file);
        int l_countrylines = d_Mla.getCountryLines(p_file);
        int l_borderlines = d_Mla.getBorderLines(p_file);
        int l_continentnumber = l_countrylines - l_continentlines - 1;
        return l_continentnumber;
    }

    /**
     *This method is to calculate country lines in text file
     * @param p_file the map file
     * @return number of continent lines
     */
    public int getCountryNumber(File p_file){
        int l_tlines = d_Mla.getLines(p_file);
        int l_continentlines = d_Mla.getContinentLines(p_file);
        int l_countrylines = d_Mla.getCountryLines(p_file);
        int l_borderlines = d_Mla.getBorderLines(p_file);
        int l_countrynumber = l_borderlines - l_countrylines - 1;
        return l_countrynumber;
    }

    /**
     * This method is to get the neighbour of countries in text file
     * @param p_file the map file
     * @return the list of the neighbour of countries
     */
    public ArrayList<String[]> getNeighbourList(File p_file){

        ArrayList<String[]> l_neighbourlist = new ArrayList<String[]>();

        int l_a = d_Mla.getBorderLines(p_file);
        int l_i = 0;

        Scanner sc = null;
        try {
             sc = new Scanner(p_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int l_n = d_Mla.getCountryLines(p_file);
        while(sc.hasNextLine()) {
            if (sc.nextLine().equals("[borders]")) {
                for (int i = 0; i < l_a - 1; i++) {

                    if (!sc.hasNext()) {
                        break;
                    }

                    String l_text = sc.nextLine();
                    String[] l_one = l_text.split(" ");
                    l_neighbourlist.add(l_one);
                }

            }
        }
        return l_neighbourlist;
    }

}
