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
public class MapDetailAccess {

    /**
     * The d_Mla MapLineAccess.
     */
    public MapLineAccess d_Mla = new MapLineAccess();

    /**
     * This method is to calculate continent lines in text file
     *
     * @param p_File the model.map file
     * @return number of continent lines
     */
    public int getContinentNumber(File p_File) {
        int l_TLines = d_Mla.getLines(p_File);
        int l_ContinentLines = d_Mla.getContinentLines(p_File);
        int l_CountryLines = d_Mla.getCountryLines(p_File);
        int l_BorderLines = d_Mla.getBorderLines(p_File);
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
        int l_TLines = d_Mla.getLines(p_File);
        int l_ContinentLines = d_Mla.getContinentLines(p_File);
        int l_CountryLines = d_Mla.getCountryLines(p_File);
        int l_Borderlines = d_Mla.getBorderLines(p_File);
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
        int l_A = d_Mla.getBorderLines(p_File);
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
