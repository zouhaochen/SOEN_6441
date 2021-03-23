package model.state.play;

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
public abstract class MainPlay extends Play {

    public MainPlay(MainPlayController p_ml){
        super(p_ml);

    }
    public void deploy() {


        int l_TempReinforcementArmy;
        // Issue order phase,Loop through all players, until all players finish issuing the instructions, and save the order in player`s order list.
        for (Player l_Player : d_ml.d_GameEngineController.d_GameData.getPlayerList()) {
            System.out.println(d_ml.d_GameEngineController.d_GameData.getCurrentPhase());

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
        d_ml.d_GameEngineController.phaseProcess();

        System.out.println("reinforcement done");

        d_ml.getDLogEntryBuffer().updateFile();

    }

}
