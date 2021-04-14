package model.gameelements;

import model.map.MapDetailAccess;
import model.map.MapListing;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is object for game elements to save and load the game
 * @author Zitao Wang
 *
 */

public class GameElements implements Serializable {

    /**
     * map file as data object
     */
    public File d_MapFile = new File("test_02.map");
    /**
     * Player List as data object
     */
    public ArrayList<Player> d_PlayerList = new ArrayList<>();

    /**
     * continent List as data object
     */
    public ArrayList<Continent> d_ContinentList = new ArrayList<>();
    /**
     * Country List as data object
     */
    public ArrayList<Country> d_CountryList = new ArrayList<>();
    /**
     * Border List as data object
     */
    public ArrayList<String[]>  d_BorderList = new ArrayList<>();


    /**
     * Neutral Player as data object
     */
    public Player d_NeutralPlayer = null;
}
