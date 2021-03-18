package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.map.MapGraph;

import java.util.Map;


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

    MainPlay(MainLoop p_ml){ super(p_ml); }



    public void loadMap() {
        printInvalidCommandMessage();
    }

    public void showMap() {
        printInvalidCommandMessage();
    }
    public void editMap() {
        printInvalidCommandMessage();
    }

    public void setPlayers() {
        printInvalidCommandMessage();
    }

    public void assignCountries() {
        printInvalidCommandMessage();
    }

    public void reinforce() {

        int l_CurrentReinforcement = 5;
        int l_TempReinforcementArmy;

        // Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
        d_ml.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
        System.out.println(d_ml.d_GameData.getCurrentPhase());

        for (Player l_Player : d_ml.d_GameEngine.d_GameData.getPlayerList()) {
            l_CurrentReinforcement += d_ml.d_GameEngine.getReinforcementBonus(l_Player);
            l_Player.setReinforcementArmies(l_CurrentReinforcement);
            System.out.println(l_CurrentReinforcement + " Reinforcement Armies are assigned to " + " Player [" + l_Player.getColour() + "]  ");

            for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
                System.out.println("Controlling countries: " + l_CountryEntry.getKey());
            }
        }
        System.out.println("Reinforcement armies for each player have been assigne, please Enter 8  continue to Issue order " );
    }

    public void IssureOrder() {
        int l_TempReinforcementArmy;
        // Issue order phase,Loop through all players, until all players finish issuing the instructions, and save the order in player`s order list.
        for (Player l_Player : d_ml.d_GameEngine.d_GameData.getPlayerList()) {
            d_ml.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
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
    }

    public void execute() {
        //execute orders phase,  execute player`s order, assigning a number of armies to move towards the target country.
        d_ml.d_GameEngine.phaseProcess();

        System.out.println("reinforcement done");
        //d_ml.setPhase(new End(d_ml));
    }

    public void endGame() {
        printInvalidCommandMessage();
    }

    public void next() {
        d_ml.setPhase(new End(d_ml));
    }

    public void previous() {
        d_ml.setPhase(new PostLoad(d_ml));
    }
}
