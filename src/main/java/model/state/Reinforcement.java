package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Player;

import java.io.File;

/**
 *	
 */
public class Reinforcement extends MainPlay {

	Reinforcement(MainLoop p_ml) {
		super(p_ml);

	}



	public void reinforce() {


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
		//execute orders phase,  execute player`s order, assigning a number of armies to move towards the target country.
		d_ml.d_GameEngine.phaseProcess();

		System.out.println("reinforcement done");
		//d_ml.setPhase(new End(d_ml));
	}

	public void attack() {
		printInvalidCommandMessage(); 
	}

	public void fortify() {
		printInvalidCommandMessage(); 
	}

	public void next() {
		d_ml.setPhase(new End(d_ml));
	}
}
