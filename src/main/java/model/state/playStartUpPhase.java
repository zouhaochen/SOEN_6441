package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class playStartUpPhase extends GameplayPhase {

    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    playStartUpPhase(GameEngineController p_gameData) {
        super(p_gameData);
    }


    //问题1。 如果此方法为static参数传不进来
    /*
    public static void startUp() {
        Scanner l_scanner = new Scanner(System.in);

        boolean l_isTrue = true;
        while (l_isTrue) {
            System.out.println("do you want to add or remove player (Number of player limit is 2 to 5)? (y/n) ");
            String l_askUser = l_scanner.nextLine().trim();

            if (l_askUser.equalsIgnoreCase("y")) {
                if (d_GameData.getPlayerList().size() < 5) {
                    d_GameEngine.gamePlayerCommand();
                }
                // since the number of player range is 2 to 5. no more player can be add in.
                else if (d_GameData.getPlayerList().size() >= 5) {
                    System.out.println("number of player out of limit ");
                    continue;
                }
            } else if (l_askUser.equalsIgnoreCase("n")) {
                // since the number of player range is 2 to 5. no more player can be remove in.
                if (d_GameData.getPlayerList().size() < 2) {
                    System.out.println("number of player is not enough, please add more ");
                } else {
                    System.out.println("All player have already set ");
                    l_isTrue = false;
                }
            } else {
                System.out.println("Invalid command, please try again (y/n): ");
            }
        }

    }
     */



    /**
     * invoke method to issue deploy order
     *
     * @param p_player         The player who issued the order
     * @param p_countryID      ID of the country to which to deploy the armies
     * @param p_numberOfArmies Number of armies to deploy
     */
    //DeployOrder.java
    public  void deployOrder(Player p_player, String p_countryID, int p_numberOfArmies){
        printInvalidCommand(this);
    }

    /**
     * invoke method to issue advance order
     *
     * @param p_player         The player who issued the order
     * @param p_countryIDFrom  ID of the country from which to deploy the armies
     * @param p_countryIDTo    ID of the country to which to deploy the armies
     * @param p_numberOfArmies Number of armies to advance
     */
    public  void advanceOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies){
        printInvalidCommand(this);
    }

    /**
     * invoke method to issue bomb order
     *
     * @param p_player    The player who issued the order
     * @param p_countryID ID of the country to bomb
     */
    public  void bombOrder(Player p_player, String p_countryID){
        printInvalidCommand(this);
    }

    /**
     * invoke method to issue blockade order
     *
     * @param p_player    The player who issued the order
     * @param p_countryID ID of the country to block
     */
    public  void blockadeOrder(Player p_player, String p_countryID){
        printInvalidCommand(this);
    }

    /**
     * This function is used to issue airlift order
     *
     * @param p_player         The player who issued the order
     * @param p_countryIDFrom  ID of the country from which to deploy the armies
     * @param p_countryIDTo    ID of the country to which to deploy the armies
     * @param p_numberOfArmies Number of armies to advance
     */
    public  void airliftOrder(Player p_player, String p_countryIDFrom, String p_countryIDTo, int p_numberOfArmies){
        printInvalidCommand(this);
    }


    /**
     * This function is used to issue negotiate order
     *
     * @param p_player   The player who issued the order
     * @param p_playerID ID of the player to negotiate with
     */
    public  void dilpomacyOrder(Player p_player, String p_playerID){
        printInvalidCommand(this);
    }
}
