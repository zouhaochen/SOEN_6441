package gameplay;

import gameelements.Continent;
import gameelements.Country;
import gameelements.Player;

import java.util.ArrayList;

/**
 * This class is a wrapper class containing the elements and the current states of the game
 */
public class GameData {

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
     * Player object array list
     */
    private ArrayList<Player> d_PlayerList;


    /**
     * game data constructor
     */
    public GameData() {

        this.d_PlayerList = new ArrayList<>();
    }

    /**
     * Country list getter
     * @return Country object array list
     */
    public ArrayList<Country> getCountryList() {
        return d_CountryList;
    }

    /**
     * Country List setter, replace old object list
     * @param d_CountryList new country object list
     */
    public void setCountryList(ArrayList<Country> d_CountryList) {
        this.d_CountryList = d_CountryList;
    }

    /**
     * continent list getter
     * @return Continent object list
     */
    public ArrayList<Continent> getContinentList() {
        return d_ContinentList;
    }

    /**
     * Continent list setter, replace old object list
     * @param d_ContinentList new Continent object list
     */
    public void setContinentList(ArrayList<Continent> d_ContinentList) {
        this.d_ContinentList = d_ContinentList;
    }

    /**
     * Total players number in game setter
     * @param p_PlayerNum new player number in game
     */
    public void setTotalPlayer(int p_PlayerNum) {
        this.d_TotalPlayer=p_PlayerNum;
    }

    /**
     * Total player number integer getter
     * @return Total player number integer
     */
    public int getTotalPlayer() {
        return d_TotalPlayer;
    }

    /**
     * Total country number integer getter
     * @return Total country number integer
     */
    public int getTotalCountry() {
        return d_TotalCountry;
    }

    /**
     * Total countries number setter,replace old one
     * @param d_TotalCountry new total country number integer
     */
    public void setTotalCountry(int d_TotalCountry) {
        this.d_TotalCountry = d_TotalCountry;
    }


    /**
     * Player object list getter
     * @return current Player object list
     */
    public ArrayList<Player> getPlayerList() {
        return d_PlayerList;
    }

    /**
     * Player obejct list setter, use new to replace old player object list
     * @param d_PlayerList new Player object list
     */
    public void setPlayerList(ArrayList<Player> d_PlayerList) {
        // set total player number to list length
        this.d_PlayerList = d_PlayerList;
        d_TotalPlayer = d_PlayerList.size();
    }


    /**
     * game phase object getter, you can get current game phase.
     * @return current Game phase object
     */
    public GamePhase getCurrentPhase() {
        return d_CurrentPhase;
    }

    /**
     * current game phase setter, you can set the game phase here.
     * @param p_currentPhase new Game phase you want to set
     */
    public void setCurrentPhase(GamePhase p_currentPhase) {
        d_CurrentPhase = p_currentPhase;
    }

    /**
     * @param p_Player the player you want in player list
     * @return <ul>
     *              <li>specific player that you want</li>
     *              <li>player no found return null</li>
     *          </ul>
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


}

