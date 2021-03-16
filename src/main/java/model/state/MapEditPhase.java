package model.state;

import controller.GameEngineController;

import java.io.File;

public abstract class MapEditPhase extends Phase {
    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    MapEditPhase(GameEngineController p_gameData, MainLoop p_mlObj) {
        super(p_gameData,p_mlObj);
    }

    /**
     * invoke editMap function
     *
     * @param p_command command user input
     */
    public void editMap (String p_command){
        printInvalidCommand(this);
    }

    /**
     * invoke function to get map file from an existing "domination" map file
     *
     * @param p_fileName path to file
     * @return the map file
     */
    public abstract File readMap(String p_fileName);

    /**
     * invoke edit continent function
     *
     * @param p_command command user input
     */
    public void editContinent (String p_command){
        printInvalidCommand(this);
    }

    /**
     * invoke edit country function
     *
     * @param p_command command user input
     */
    public void editCountry (String p_command){
        printInvalidCommand(this);
    }

    /**
     * invoke edit neighbor function
     *
     * @param p_command command user input
     */
    public void editNeighbor (String p_command){
        printInvalidCommand(this);
    }

    /**
     * invoke function to display map in a connected directed graph
     *
     * @param p_filename filename user input in command
     */
    public void displayMap (File p_filename){
        printInvalidCommand(this);
    }

    /**
     * invoke save function to save a map to a text file exactly as edited
     *
     * @param p_command command user input
     */
    public void saveMap(String p_command){
        printInvalidCommand(this);
    }

    /**
     * invoke function to implement check the verification of map correctness.
     *
     * @param p_filename filename user input in command
     */
    public void validate(File p_filename){
        printInvalidCommand(this);
    }

    public void next(){
        d_mainLoopObject.setPhase(new playStartUp(d_gameDataObject,d_mainLoopObject));
    }
}

