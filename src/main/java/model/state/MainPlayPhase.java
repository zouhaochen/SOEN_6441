package model.state;

import controller.GameEngineController;
import model.gameelements.Player;

public class MainPlayPhase extends GamePlay {

    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     * @param p_mlObj
     */
    MainPlayPhase(GameEngineController p_gameData, MainLoop p_mlObj) {
        super(p_gameData, p_mlObj);
    }
    /**
     * invoke load map function from map file
     */
    //GameData.java
    public void loadMap(){
        printInvalidCommand(this);
    }

    /**
     * invoke function to add new player to the game
     *
     * @param p_colour you custom player color
     */
    //GameEngineController.java
    public void addNewPlayer(String p_colour){
        printInvalidCommand(this);
    }

    /**
     * invoke function to remove player from the game
     *
     * @param p_player the player you want to remove
     */
    //GameEngineController.java
    public void removePlayer(Player p_player){
        printInvalidCommand(this);
    }

    /**
     * invoke method for player to show map in game
     *
     * @param p_player input the playerID you want to show its map
     */
    //GameEngineController.java
    public void showMap(Player p_player){
        printInvalidCommand(this);
    }

    /**
     * invoke method to randomly assign all the countries to the players
     */
    //GameEngineController.java
    public void assignCountries(){
        printInvalidCommand(this);
    }

}
