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

    public File d_MapFile = new File("test_02.map");
    public ArrayList<Player> d_PlayerList = new ArrayList<>();

    public ArrayList<Continent> d_ContinentList = new ArrayList<>();
    public ArrayList<Country> d_CountryList = new ArrayList<>();
    public ArrayList<String[]>  d_BorderList = new ArrayList<>();
    //public MapListing d_MapListing = new MapListing();
    public Player d_NeutralPlayer = null;
}
