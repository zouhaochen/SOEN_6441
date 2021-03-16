package model.state;

import controller.GameEngineController;
import model.gameelements.Player;

import java.io.File;


public abstract class Phase {
    GameEngineController d_gameDataObject;
    MainLoop d_mainLoopObject;

    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    Phase(GameEngineController p_gameData, MainLoop p_mlObj) {
        d_gameDataObject = p_gameData;
        d_mainLoopObject = p_mlObj;
    }

    /**
     * display invalid command
     *
     * @param p_phase Object of the current phase
     */
    public void printInvalidCommand(Phase p_phase) {
        System.out.println("Invalid command in state");
    }

    //---------------------------map editor---------------------------
    /**
     * invoke editMap function
     *
     * @param p_Command command user input
     */
    public abstract void editMap(String p_Command);

    /**
     * invoke edit continent function
     *
     * @param p_command command user input
     */
    public abstract void editContinent(String[] p_command);

    /**
     * invoke edit country function
     *
     * @param p_command command user input
     */
    public abstract void editCountry(String[] p_command);

    /**
     * invoke edit neighbor function
     *
     * @param p_command command user input
     */
    public abstract void editNeighbor(String[] p_command);

    /**
     * invoke function to display map in a connected directed graph
     *
     * @param p_filename filename user input in command
     */
    public abstract void showMap(File p_filename);

    /**
     * invoke save function to save a map to a text file exactly as edited
     *
     * @param p_command command user input
     */
    public abstract void saveMap(String p_command);

    /**
     * invoke function to implement check the verification of map correctness.
     *
     * @param p_filename filename user input in command
     */
    //MapCheck.java
    public abstract void validate(File p_filename);

    //---------------------------game play---------------------------

    /**
     * invoke load map function from map file
     */
    //GameData.java
    public abstract void loadMap();

    /**
     * invoke function to add new player to the game
     *
     * @param p_colour you custom player color
     */
    //GameEngineController.java
    public abstract void addNewPlayer(String p_colour);

    /**
     * invoke function to remove player from the game
     *
     * @param p_player the player you want to remove
     */
    //GameEngineController.java
    public abstract void removePlayer(Player p_player);

    /**
     * invoke method for player to show map in game
     *
     * @param p_player input the playerID you want to show its map
     */
    //GameEngineController.java
    public abstract void showMap(Player p_player);

    /**
     * invoke method to randomly assign all the countries to the players
     */
    //GameEngineController.java
    public abstract void assignCountries();

    //---------------------------build 02---------------------------

    /**
     * invoke method to issue deploy order
     *
     * @param p_player         The player who issued the order
     * @param p_countryID      ID of the country to which to deploy the armies
     * @param p_numberOfArmies Number of armies to deploy
     */
    //DeployOrder.java
    public abstract void deployOrder(Player p_player, String p_countryID, int p_numberOfArmies);

    /**
     * invoke method to issue advance order
     *
     * @param p_player         The player who issued the order
     * @param p_countryIDFrom  ID of the country from which to deploy the armies
     * @param p_countryIDTo    ID of the country to which to deploy the armies
     * @param p_numberOfArmies Number of armies to advance
     */
    public abstract void advanceOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies);

    /**
     * invoke method to issue bomb order
     *
     * @param p_player    The player who issued the order
     * @param p_countryID ID of the country to bomb
     */
    public abstract void bombOrder(Player p_player, String p_countryID);

    /**
     * invoke method to issue blockade order
     *
     * @param p_player    The player who issued the order
     * @param p_countryID ID of the country to block
     */
    public abstract void blockadeOrder(Player p_player, String p_countryID);

    /**
     * This function is used to issue airlift order
     *
     * @param p_player         The player who issued the order
     * @param p_countryIDFrom  ID of the country from which to deploy the armies
     * @param p_countryIDTo    ID of the country to which to deploy the armies
     * @param p_numberOfArmies Number of armies to advance
     */
    public abstract void airliftOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies);


    /**
     * This function is used to issue negotiate order
     *
     * @param p_player   The player who issued the order
     * @param p_playerID ID of the player to negotiate with
     */
    public abstract void dilpomacyOrder(Player p_player, String p_playerID);

    public abstract void endGame();

    public abstract void next();
}