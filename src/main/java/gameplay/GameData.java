package gameplay;

import gameelements.Continent;
import gameelements.Country;
import gameelements.Player;

import java.util.ArrayList;

/**
 * This class is a wrapper class containing the elements and the current states of the game
 */
public class GameData {

    private GamePhase d_CurrentPhase;
    private int d_TotalPlayer;
    private int d_TotalCountry;


    private ArrayList<Country> d_CountryList;
    private ArrayList<Continent> d_ContinentList;
    private ArrayList<Player> d_PlayerList;


    /**
     * game data constructor
     */
    public GameData() {

        this.d_PlayerList = new ArrayList<>();
    }

    public ArrayList<Country> getCountryList() {
        return d_CountryList;
    }

    public void setCountryList(ArrayList<Country> d_CountryList) {
        this.d_CountryList = d_CountryList;
    }

    public ArrayList<Continent> getContinentList() {
        return d_ContinentList;
    }

    public void setContinentList(ArrayList<Continent> d_ContinentList) {
        this.d_ContinentList = d_ContinentList;
    }

    public int getTotalPlayer() {
        return d_TotalPlayer;
    }

    public int getTotalCountry() {
        return d_TotalCountry;
    }

    public void setTotalCountry(int d_TotalCountry) {
        this.d_TotalCountry = d_TotalCountry;
    }


    public ArrayList<Player> getD_PlayerList() {
        return d_PlayerList;
    }

    public void setPlayerList(ArrayList<Player> d_PlayerList) {
        // set total player number to list length
        this.d_PlayerList = d_PlayerList;
        d_TotalPlayer = d_PlayerList.size();
    }


    public GamePhase getCurrentPhase() {
        return d_CurrentPhase;
    }

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

