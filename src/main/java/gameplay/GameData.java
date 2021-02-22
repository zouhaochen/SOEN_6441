package gameplay;

import gameelements.Continent;
import gameelements.Country;
import gameelements.Player;
import map.MapDetailAccess;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is a wrapper class containing the elements and the current states of the game
 */
public class GameData {

    /**
     * Map file
     */
    File d_MapFile;

    /**
     * Map Object
     */
    public MapDetailAccess d_MapDetailAccess;

    /**
     * Game current phase
     */
    private GamePhase d_CurrentPhase;
    /**
     * Total player now in game
     */
    private int d_TotalPlayer;
    /**
     * how many countries now in game
     */
    private int d_TotalCountry;


    /**
     * Country object array list
     */
    private ArrayList<Country> d_CountryList;
    /**
     * Continent object array list
     */
    private ArrayList<Continent> d_ContinentList;


    /**
     * Countries' border list
     */
    private ArrayList<String[]> d_BorderList;
    /**
     * Player object array list
     */
    private ArrayList<Player> d_PlayerList;


    /**
     * game data constructor
     */
    public GameData(File p_MapFile) {

        this.d_PlayerList = new ArrayList<>();
        this.d_MapDetailAccess = new MapDetailAccess();
        this.d_MapDetailAccess.countrynumber(p_MapFile);
        this.d_ContinentList = new ArrayList<>();
        this.d_CountryList = new ArrayList<>();
        this.d_BorderList = new ArrayList<>();
    }

    /**
     * Border list getter
     *
     * @return Border list
     */
    public ArrayList<String[]> getBorderList() {
        return d_BorderList;
    }

    /**
     * Border list setter
     *
     * @param d_BorderList set new Border list
     */
    public void setBorderList(ArrayList<String[]> d_BorderList) {
        this.d_BorderList = d_BorderList;
    }

    /**
     * Country list getter
     *
     * @return Country object array list
     */
    public ArrayList<Country> getCountryList() {
        return d_CountryList;
    }

    /**
     * Country List setter, replace old object list
     *
     * @param d_CountryList new country object list
     */
    public void setCountryList(ArrayList<Country> d_CountryList) {
        this.d_CountryList = d_CountryList;
    }

    /**
     * continent list getter
     *
     * @return Continent object list
     */
    public ArrayList<Continent> getContinentList() {
        return d_ContinentList;
    }

    /**
     * Continent list setter, replace old object list
     *
     * @param d_ContinentList new Continent object list
     */
    public void setContinentList(ArrayList<Continent> d_ContinentList) {
        this.d_ContinentList = d_ContinentList;
    }

    /**
     * Total players number in game setter
     *
     * @param p_PlayerNum new player number in game
     */
    public void setTotalPlayer(int p_PlayerNum) {
        this.d_TotalPlayer = p_PlayerNum;
    }

    /**
     * Total player number integer getter
     *
     * @return Total player number integer
     */
    public int getTotalPlayer() {
        return d_TotalPlayer;
    }

    /**
     * Total country number integer getter
     *
     * @return Total country number integer
     */
    public int getTotalCountry() {
        return d_TotalCountry;
    }

    /**
     * Total countries number setter,replace old one
     *
     * @param d_TotalCountry new total country number integer
     */
    public void setTotalCountry(int d_TotalCountry) {
        this.d_TotalCountry = d_TotalCountry;
    }


    /**
     * Player object list getter
     *
     * @return current Player object list
     */
    public ArrayList<Player> getPlayerList() {
        return d_PlayerList;
    }

    /**
     * Player obejct list setter, use new to replace old player object list
     *
     * @param d_PlayerList new Player object list
     */
    public void setPlayerList(ArrayList<Player> d_PlayerList) {
        // set total player number to list length
        this.d_PlayerList = d_PlayerList;
//        d_TotalPlayer = d_PlayerList.size();
    }


    /**
     * game phase object getter, you can get current game phase.
     *
     * @return current Game phase object
     */
    public GamePhase getCurrentPhase() {
        return d_CurrentPhase;
    }

    /**
     * current game phase setter, you can set the game phase here.
     *
     * @param p_currentPhase new Game phase you want to set
     */
    public void setCurrentPhase(GamePhase p_currentPhase) {
        d_CurrentPhase = p_currentPhase;
    }

    /**
     * @param p_Player the player you want in player list
     * @return <ul>
     * <li>specific player that you want</li>
     * <li>player no found return null</li>
     * </ul>
     */
    public Player getTargetPlayer(Player p_Player) {
        for (Player l_Player : d_PlayerList) {
            if (l_Player.getId() == p_Player.getId()) {
                return l_Player;
            }
        }
        System.out.println("ERROR: Player No Found.");
        return null;
    }

    /**
     * add new country to country list
     *
     * @param p_CountryName String new country name
     * @param p_CountryId   String new country id
     */
    public void addCountry(String p_CountryName, String p_CountryId, String p_CountryArmy) {
        Country l_NewCountry = new Country(p_CountryName);
        l_NewCountry.setCountryId(Integer.parseInt(p_CountryId));
        l_NewCountry.setArmies(Integer.parseInt(p_CountryArmy));
        System.out.println("NOTICE: the new country [" + l_NewCountry.getName() + "] has been added to the map, ID:"
                + l_NewCountry.getCountryId() + " Armies:" + l_NewCountry.getArmies());
        d_CountryList.add(l_NewCountry);
    }

    /**
     * add new continent to continent list
     *
     * @param p_ContinentName  String new continent name
     * @param p_ContinentValue String new continent id
     */
    public void addContinent(String p_ContinentName, String p_ContinentValue) {
        Continent l_NewContinent = new Continent(p_ContinentName, Integer.parseInt(p_ContinentValue));
        System.out.println("NOTICE: the new continent [" + l_NewContinent.getName() + "] has been added to the map, Value:"
                + l_NewContinent.getContinentValue());
        d_ContinentList.add(l_NewContinent);
    }


    public void loadMap(File p_MapFile) throws FileNotFoundException {
        Scanner l_MapScanner;
        String[] l_one;
        l_MapScanner = new Scanner(p_MapFile);
        ArrayList<String[]> l_StringList = new ArrayList<>();
        while (l_MapScanner.hasNextLine()) {
            switch (l_MapScanner.nextLine()) {
                case "[continents]":
                    while (l_MapScanner.hasNextLine()) {
                        l_one = l_MapScanner.nextLine().split(" ");
                        if (l_one.length == 1) {
                            break;
                        } else {
                            // format continentName continentValue
                            this.addContinent(l_one[0], l_one[1]);
                        }
                    }
                    break;
                case "[countries]":
                    while (l_MapScanner.hasNextLine()) {
                        l_one = l_MapScanner.nextLine().split(" ");
                        if (l_one.length == 1) {
                            break;
                        } else {
                            //format id countryName countryArmy
                            this.addCountry(l_one[1], l_one[0], l_one[2]);
                        }

                    }
                    break;
                case "[borders]":
                    while (l_MapScanner.hasNextLine()) {

                        l_one = l_MapScanner.nextLine().split(" ");
                        d_BorderList.add(l_one);
                        if (!l_MapScanner.hasNext()) {
                            break;
                        }
                    }
                    break;
            }
        }
        for(String[] l_StringArray:d_BorderList){
            System.out.println(Arrays.deepToString(l_StringArray));
        }

    }

}

