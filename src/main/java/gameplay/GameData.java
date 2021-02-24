package gameplay;

import gameelements.Continent;
import gameelements.Country;
import gameelements.Player;
import map.MapDetailAccess;
import map.MapListing;


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
     * Map Object,read only
     */
    public MapDetailAccess d_MapDetailAccess;

    /**
     * used to get/set current map
     */
    public MapListing d_MapListing;

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
     *
     * @param p_MapFile the custom map that needed to build this game data
     */
    public GameData(File p_MapFile) {
        this.d_MapFile = p_MapFile;
        this.d_PlayerList = new ArrayList<>();
        this.d_MapDetailAccess = new MapDetailAccess();
        this.d_MapDetailAccess.getCountryNumber(p_MapFile);
        this.d_ContinentList = new ArrayList<>();
        this.d_CountryList = new ArrayList<>();
        this.d_BorderList = new ArrayList<>();
        this.d_MapListing = new MapListing();
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
     * Gets a Player object by the name(colour) of the player
     *
     * @param p_Name the player's name(colour)
     * @return a Player object
     */
    public Player getPlayerByName(String p_Name) {
        return d_PlayerList.stream().filter(p -> p.getColour().equalsIgnoreCase(p_Name)).findFirst().orElse(null);
    }

    /**
     * add new country to country list
     *
     * @param p_CountryName String new country name
     * @param p_CountryId   String new country id
     */
    public void addCountry(String p_CountryName, String p_CountryId) {
        Country l_NewCountry = new Country(p_CountryName);
        l_NewCountry.setCountryId(Integer.parseInt(p_CountryId));
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


    /**
     * load map function
     *
     * @throws FileNotFoundException if file not found
     */
    public void loadMap() throws FileNotFoundException {
        Scanner l_MapScanner;
        String[] l_One;
        l_MapScanner = new Scanner(d_MapFile);
        while (l_MapScanner.hasNextLine()) {
            switch (l_MapScanner.nextLine()) {
                case "[continents]":
                    while (l_MapScanner.hasNextLine()) {
                        l_One = l_MapScanner.nextLine().split(" ");
                        if (l_One.length == 1) {
                            break;
                        } else {
                            // format continentName continentValue
                            this.addContinent(l_One[0], l_One[1]);
                        }
                    }
                    break;
                case "[countries]":
                    while (l_MapScanner.hasNextLine()) {
                        l_One = l_MapScanner.nextLine().split(" ");
                        if (l_One.length == 1) {
                            break;
                        } else {
                            //format countryName id
                            this.addCountry(l_One[1], l_One[0]);

                            // get the country object and add it to the hash map of the corresponding continent object
                            int l_CountryId = Integer.parseInt(l_One[0]);
                            int l_ContinentId = Integer.parseInt(l_One[2]);
                            Continent l_Continent = d_ContinentList.get(l_ContinentId-1);
                            Country l_CountryToAdd = d_CountryList.get(l_CountryId-1);
                            l_Continent.getCountries().put(l_CountryToAdd.getName(), l_CountryToAdd);
                        }

                    }
                    break;
                case "[borders]":
                    while (l_MapScanner.hasNextLine()) {

                        // read the boarders section to add border countries to an object of type Country
                        l_One = l_MapScanner.nextLine().split(" ");
                        if (l_One.length > 1) {
                            int l_CountryId = Integer.parseInt(l_One[0]);
                            Country l_Country = d_CountryList.get(l_CountryId-1);

                            // add the border countries to the hash map of boarderCountries
                            for (int l_Index = 1; l_Index < l_One.length; ++l_Index) {
                                int l_BorderIndex = Integer.parseInt(l_One[l_Index]);
                                Country l_BorderCountry = d_CountryList.get(l_BorderIndex-1);
                                l_Country.getBorderCountries().put(l_BorderCountry.getName(), l_BorderCountry);
                            }
                        }
                        d_BorderList.add(l_One);
                        if (!l_MapScanner.hasNext()) {
                            break;
                        }
                    }
                    break;
            }
        }
        for (String[] l_StringArray : d_BorderList) {
            System.out.println(Arrays.deepToString(l_StringArray));
        }

    }

}

