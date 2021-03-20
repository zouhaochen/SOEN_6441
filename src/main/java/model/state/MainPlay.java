package model.state;

import controller.MainPlayController;
import model.gameelements.Player;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior
 *  for commands that are valid in this state, and for the others signifies
 *  that the command is invalid.
 *
 *  This state represents a group of states, and defines the behavior
 *  that is common to all the states in its group. All the states in its
 *  group need to extend this class.
 *
 */
public class MainPlay extends Play {

    MainPlay(MainPlayController p_ml){
        super(p_ml);

    }
    public void deploy() {


        int l_TempReinforcementArmy;
        // Issue order phase,Loop through all players, until all players finish issuing the instructions, and save the order in player`s order list.
        for (Player l_Player : d_ml.d_GameEngine.d_GameData.getPlayerList()) {
            //d_ml.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
            System.out.println(d_ml.d_GameEngine.d_GameData.getCurrentPhase());

            l_TempReinforcementArmy = l_Player.getReinforcementArmies();
            while (l_TempReinforcementArmy > 0) {
                System.out.println("==== Now Player [" + l_Player.getColour() + "]'s turn to issue order ====");
                System.out.println(" Player [" + l_Player.getColour() + "] have " + l_TempReinforcementArmy
                        + " Reinforcement Armies.");
                l_Player.issueOrder();

                //Reduce the corresponding amount of reinforcements after deploying.
                l_TempReinforcementArmy -= l_Player.getLastOrderFromQueue().getOrderInfo().getNumberOfArmy();
            }
        }
        //execute orders phase,  execute player`s order, assigning a number of armies to move towards the target country.
        d_ml.d_GameEngine.phaseProcess();

        System.out.println("reinforcement done");

    }

    public void advance() {
        printInvalidCommandMessage();
    }

    public void cards() {
        printInvalidCommandMessage();
    }

    public void next() {
        d_ml.setPhase(new End(d_ml));
    }
    public void previous() {
        printInvalidCommandMessage();
    }

    public void loadMap() {
        this.printInvalidCommandMessage();
    }

    public void setPlayers() {
        this.printInvalidCommandMessage();
    }

    public void assignCountries() {
        this.printInvalidCommandMessage();
    }
}
