package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to get the details from the map file
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapLineAccess {

    /**
     *This method is to count the number of lines
     * @return count the total lines
     */
    public int getLines(File p_file) {

        Scanner l_scan = null;
        try {
            l_scan = new Scanner(p_file);
        } catch (FileNotFoundException l_e) {
            l_e.printStackTrace();
        }
        int l_count = 0;
        while (l_scan.hasNextLine()) {
            l_count++;
            l_scan.nextLine();
        }
        return l_count;
    }

    /**
     * This method is to return the continent lines
     * @param p_file the map file
     * @return return the continent lines
     */
    public int getContinentLines(File p_file){

        int l_continentlines = 0;
        Scanner l_sca = null;
        try {
            l_sca = new Scanner(p_file);
        } catch (FileNotFoundException l_e) {
            l_e.printStackTrace();
        }

        while(l_sca.hasNextLine()){
            l_continentlines++;

            if (l_sca.next().equals("[continents]")){
                break;
            }
            l_sca.nextLine();
        }
        return l_continentlines;
    }


    /***
     * This method is to return the country lines
     * @param p_file the map file
     * @return return the country lines
     */
    public int getCountryLines(File p_file){

        int l_countryline = 0;
        Scanner l_scan = null;
        try {
            l_scan = new Scanner(p_file);
        } catch (FileNotFoundException l_e) {
            l_e.printStackTrace();
        }

        while(l_scan.hasNextLine()){
            l_countryline++;

            if (l_scan.next().equals("[countries]")){
                break;
            }
            l_scan.nextLine();
        }
        return l_countryline;
    }

    /***
     * This method is to return the borders lines
     * @param p_file the map file
     * @return return the borders lines
     */
    public int getBorderLines(File p_file){

        int l_borderline = 0;
        Scanner l_scan = null;
        try {
            l_scan = new Scanner(p_file);
        } catch (FileNotFoundException l_e) {
            l_e.printStackTrace();
        }

        while(l_scan.hasNextLine()){
            l_borderline++;

            if (l_scan.next().equals("[borders]")){
                break;
            }
            l_scan.nextLine();
        }
        return l_borderline;
    }

}


