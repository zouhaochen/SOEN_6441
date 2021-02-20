package gameplay;

import command.CommandValidator;
import gameelements.Player;

import java.util.ArrayList;

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
        d_GameData = new GameData();
        // command validator initialize here
        d_CommandValidator = new CommandValidator(d_GameData);
    }


    /**
     * add new player to the game
     *
     * @param p_colour           you custom player color
     */
    public void addNewPlayer(String p_colour) {
        //new temp player list
        ArrayList<Player> l_NewPlayerList = d_GameData.getD_PlayerList();
        Player l_NewPlayer = new Player(p_colour, d_CommandValidator);
        // add to temp player list
        l_NewPlayerList.add(l_NewPlayer);
        // set to game data player list
        d_GameData.setD_PlayerList(l_NewPlayerList);
        System.out.println("NOTICE: New Player " + p_colour + " has been added to the game.");
    }

    /**
     * remove player from the game
     *
     * @param p_Player the player you want to remove
     */
    public void RemovePlayer(Player p_Player) {
        System.out.println("NOTICE: The Player " + p_Player.getColour() + " has been removed from the game.");
        //new temp player list
        ArrayList<Player> l_PlayerList = d_GameData.getD_PlayerList();
        // remove it
        l_PlayerList.remove(p_Player);
        // set to game data player list
        d_GameData.setD_PlayerList(l_PlayerList);
    }


    /**
     * this method is current player phase process, player's order will be processed here.
     */
    public void phaseProcess() {

    }

    /**
     * this method used to show map in game
     */
    public void showMap() {

    }

}
