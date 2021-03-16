package model.state;

import controller.GameEngineController;
import model.gameelements.Player;

import java.io.File;

public class GamePlay extends Phase {

    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     * @param p_mlObj
     */
    GamePlay(GameEngineController p_gameData, MainLoop p_mlObj) {
        super(p_gameData, p_mlObj);
    }

    @Override
    public void editMap(String p_Command) {

    }

    @Override
    public void editContinent(String[] p_command) {

    }

    @Override
    public void editCountry(String[] p_command) {

    }

    @Override
    public void editNeighbor(String[] p_command) {

    }

    @Override
    public void showMap(File p_filename) {

    }

    @Override
    public void saveMap(String p_command) {

    }

    @Override
    public void validate(File p_filename) {

    }

    @Override
    public void loadMap() {

    }

    @Override
    public void addNewPlayer(String p_colour) {

    }

    @Override
    public void removePlayer(Player p_player) {

    }

    @Override
    public void showMap(Player p_player) {

    }

    @Override
    public void assignCountries() {

    }

    @Override
    public void deployOrder(Player p_player, String p_countryID, int p_numberOfArmies) {

    }

    @Override
    public void advanceOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies) {

    }

    @Override
    public void bombOrder(Player p_player, String p_countryID) {

    }

    @Override
    public void blockadeOrder(Player p_player, String p_countryID) {

    }

    @Override
    public void airliftOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies) {

    }

    @Override
    public void dilpomacyOrder(Player p_player, String p_playerID) {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void next() {

    }
}
