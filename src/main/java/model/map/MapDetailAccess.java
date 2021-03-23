package model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to get the number of continents , countries and the border list.
 *
 * @author Zitao Wang
 * @version 1.0.0
 */
public class MapDetailAccess extends MapGraph {


    /**
     * This method is to count the number of lines
     *
     * @param p_File the model.map file
     * @return count the total lines
     */
    public int getLines(File p_File) {
        Scanner l_Scan = null;
        try {
            l_Scan = new Scanner(p_File);
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
     *
     * @param p_File the model.map file
     * @return return the continent lines
     */
    public int getContinentLines(File p_File) {

        int l_ContinentLines = 0;
        Scanner l_Sca = null;
        try {
            l_Sca = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        //To get the location of the continents in the model.map file.
        while (l_Sca.hasNextLine()) {
            l_ContinentLines++;

            if (l_Sca.next().equals("[continents]")) {
                break;
            }
            l_Sca.nextLine();
        }
        return l_ContinentLines;
    }

    /**
     * This method is to return the country lines
     *
     * @param p_File the model.map file
     * @return return the country lines
     */
    public int getCountryLines(File p_File) {

        int l_CountryLine = 0;
        Scanner l_Scan = null;
        try {
            l_Scan = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        //To get the location of the countries in the model.map file.
        while (l_Scan.hasNextLine()) {
            l_CountryLine++;

            if (l_Scan.next().equals("[countries]")) {
                break;
            }
            l_Scan.nextLine();
        }
        return l_CountryLine;
    }

    /**
     * This method is to return the borders lines
     *
     * @param p_File the model.map file
     * @return return the borders lines
     */
    public int getBorderLines(File p_File) {

        int l_Borderline = 0;
        Scanner l_Scan = null;
        try {
            l_Scan = new Scanner(p_File);
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        //To get the location of the borders in the model.map file.
        while (l_Scan.hasNextLine()) {
            l_Borderline++;

            if (l_Scan.next().equals("[borders]")) {
                break;
            }
            l_Scan.nextLine();
        }
        return l_Borderline;
    }


    /**
     * This method is to calculate continent lines in text file
     *
     * @param p_File the model.map file
     * @return number of continent lines
     */
    public int getContinentNumber(File p_File) {
        int l_TLines = getLines(p_File);
        int l_ContinentLines = getContinentLines(p_File);
        int l_CountryLines = getCountryLines(p_File);
        int l_BorderLines = getBorderLines(p_File);
        int l_ContinentNumber = l_CountryLines - l_ContinentLines - 1;
        return l_ContinentNumber;
    }

    /**
     * This method is to calculate country lines in text file
     *
     * @param p_File the model.map file
     * @return number of continent lines
     */
    public int getCountryNumber(File p_File) {
        int l_TLines = getLines(p_File);
        int l_ContinentLines = getContinentLines(p_File);
        int l_CountryLines = getCountryLines(p_File);
        int l_Borderlines = getBorderLines(p_File);
        int l_CountryNumber = l_Borderlines - l_CountryLines - 1;
        return l_CountryNumber;
    }

    /**
     * This method is to get the neighbour of countries in text file
     *
     * @param p_File the model.map file
     * @return the list of the neighbour of countries
     */
    public ArrayList<String[]> getNeighbourList(File p_File) {

        ArrayList<String[]> l_NeighbourList = new ArrayList<String[]>();
        int l_A = getBorderLines(p_File);
        Scanner l_Sc = null;
        try {
            l_Sc = new Scanner(p_File);
        } catch (FileNotFoundException l_Exception) {
            l_Exception.printStackTrace();
        }
        //To get the location of borders in the model.map file.
        while (l_Sc.hasNextLine()) {
            if (l_Sc.nextLine().equals("[borders]")) {
                for (int l_I = 0; l_I < l_A - 1; l_I++) {
                    if (!l_Sc.hasNext()) {
                        break;
                    }
                    // Getting the connection table for the country
                    String l_Text = l_Sc.nextLine();
                    String[] l_One = l_Text.split(" ");
                    l_NeighbourList.add(l_One);
                }
            }
        }
        //To return the whole neighbour list
        return l_NeighbourList;
    }

}
