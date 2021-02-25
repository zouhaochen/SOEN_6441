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
     * @param p_file the map file
     * @return count the total lines
     */
    public int getLines(File p_file) {
        Scanner l_Scan = null;
        try {
            l_Scan = new Scanner(p_file);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        int l_Count = 0;
        while (l_Scan.hasNextLine()) {
            l_Count++;
            l_Scan.nextLine();
        }
        return l_Count;
    }

    /**
     * This method is to return the continent lines
     * @param p_File the map file
     * @return return the continent lines
     */
    public int getContinentLines(File p_File){

        int l_continentlines = 0;
        Scanner l_Sca = null;
        try {
            l_Sca = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        //To get the location of the continents in the map file.
        while(l_Sca.hasNextLine()){
            l_continentlines++;

            if (l_Sca.next().equals("[continents]")){
                break;
            }
            l_Sca.nextLine();
        }
        return l_continentlines;
    }

    /***
     * This method is to return the country lines
     * @param p_file the map file
     * @return return the country lines
     */
    public int getCountryLines(File p_file){

        int l_Countryline = 0;
        Scanner l_Scan = null;
        try {
            l_Scan = new Scanner(p_file);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        //To get the location of the countries in the map file.
        while(l_Scan.hasNextLine()){
            l_Countryline++;

            if (l_Scan.next().equals("[countries]")){
                break;
            }
            l_Scan.nextLine();
        }
        return l_Countryline;
    }

    /***
     * This method is to return the borders lines
     * @param p_File the map file
     * @return return the borders lines
     */
    public int getBorderLines(File p_File){

        int l_Borderline = 0;
        Scanner l_Scan = null;
        try {
            l_Scan = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        //To get the location of the borders in the map file.
        while(l_Scan.hasNextLine()){
            l_Borderline++;

            if (l_Scan.next().equals("[borders]")){
                break;
            }
            l_Scan.nextLine();
        }
        return l_Borderline;
    }

}


