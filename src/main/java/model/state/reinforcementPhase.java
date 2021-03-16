package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;

import java.io.File;
import java.util.Map;

public class reinforcementPhase extends GameplayPhase {

    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    reinforcementPhase(GameEngineController p_gameData) {
        super(p_gameData);
    }

    //同样static的问题
    /*
     public static void assignArmy() {
         // randomly assign countries for each player
         d_GameEngine.assignCountries();

         //start up the game, according to the game rules to start game engine, and determine if any players are eliminated at the end of each round.
         int l_CurrentReinforcement = 5;
         while (d_GameData.getCurrentPhase() != GamePhase.END_OF_GAME) {
             int l_TempReinforcementArmy;

             // Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
             d_GameEngine.d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
             System.out.println(d_GameData.getCurrentPhase());

             for (Player l_Player : d_GameEngine.d_GameData.getPlayerList()) {
                 l_CurrentReinforcement += d_GameEngine.getReinforcementBonus(l_Player);
                 l_Player.setReinforcementArmies(l_CurrentReinforcement);
                 System.out.println(l_CurrentReinforcement + " Reinforcement Armies are assigned to " + " Player [" + l_Player.getColour() + "]  ");

                 for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
                     System.out.println("Controlling countries: " + l_CountryEntry.getKey());
                 }
             }

             System.out.println("---------REINFORCEMENT PHASE COMPLETE-----------\n");
         }
     }*/

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
