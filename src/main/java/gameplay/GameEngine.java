package gameplay;

import command.CommandValidator;
import gameelements.Country;
import gameelements.Player;

import java.util.ArrayList;
import java.util.Map;

/**
 * this is game main controller
 */
public class GameEngine {


    /**
     * the GameData you design from the MainLoop
     */
    public GameData d_GameData;

    /**
     * the commandValidator each player will use.
     */
    CommandValidator d_CommandValidator;


    /**
     * GameEngine Constructor
     *
     * @param p_GameData you should pass reference that Game Data you used
     */
    public GameEngine(GameData p_GameData) {

        // new gameData here
        d_GameData = p_GameData;
        // command validator initialize here
        d_CommandValidator = new CommandValidator(d_GameData);
    }


    /**
     * add new player to the game
     *
     * @param p_colour you custom player color
     */
    public void addNewPlayer(String p_colour) {
        //new temp player list
        ArrayList<Player> l_NewPlayerList = d_GameData.getD_PlayerList();
        Player l_NewPlayer = new Player(p_colour, d_CommandValidator);
        // add to temp player list
        l_NewPlayerList.add(l_NewPlayer);
        // set to game data player list
        d_GameData.setPlayerList(l_NewPlayerList);
        System.out.println("NOTICE: New Player " + l_NewPlayer.getId() + " [" + l_NewPlayer.getColour() + "] has been added to the game.");
    }

    /**
     * remove player from the game
     *
     * @param p_Player the player you want to remove
     */
    public void removePlayer(Player p_Player) {
        System.out.println("NOTICE: The Player " + p_Player.getId() + " [" + p_Player.getColour() + "] has been removed from the game.");
        //new temp player list
        ArrayList<Player> l_PlayerList = d_GameData.getD_PlayerList();
        // remove it
        l_PlayerList.remove(p_Player);
        // set to game data player list
        d_GameData.setPlayerList(l_PlayerList);
    }


    /**
     * this method is current player phase process, player's order will be processed here.
     */
    public void phaseProcess() {

        // set game phase to "attack"
        d_GameData.setCurrentPhase(GamePhase.ATTACK);
        for (Player l_Player : d_GameData.getD_PlayerList()) {
            // player executes order till order list is empty
            while (l_Player.nextOrder() != null) {
                l_Player.nextOrder().execute();
            }

        }
        d_GameData.setCurrentPhase(GamePhase.WAITING_TO_TURN);
    }


    /**
     * this method used to player show map in game
     * @param p_Player input the playerID you want to show its map
     */
    public void showMap(Player p_Player) {
        Player l_Player = d_GameData.getTargetPlayer(p_Player);
        System.out.println("Player [" + l_Player.getColour() + "] your current army force show below: ");
        // iterate each country in player's map
        for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
            String l_CountryName = l_CountryEntry.getKey();
            Country l_Country = l_CountryEntry.getValue();
            int ArmyNum = l_Country.getArmies();
            System.out.println(l_CountryName + " has " + ArmyNum + " Armies.");
        }

    }

}
