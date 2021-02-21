package gameelements.order;

import gameelements.Country;
import gameelements.Player;

public class DeployOrder extends Order{

	public DeployOrder() {
		super();
		setName("Deploy");
	}

	@Override
	public boolean execute() {

		if (getOrderInfo().getPlayer() == null || getOrderInfo().getDestination() == null) {
			System.out.println("Fail to execute Deploy order: Invalid order information.");
			return false;
		}

		Player l_player = getOrderInfo().getPlayer();
		String l_destination = getOrderInfo().getDestination();

		// If the player decides to deploy armies to the country he/she doesn't control, the player will lost the armies.
		if (!l_player.getCountriesInControl().containsKey(l_destination.toLowerCase())) {
			System.out.println("\nFail to execute Deploy order: the country " + l_destination + " is not in the control of player " + l_player.getColour() + ".");
			return false;
		}

		// check if the subtraction manages to execute
		int l_armiesToDeploy = getOrderInfo().getNumberOfArmy();
		if (!l_player.deployReinforcementArmies(getOrderInfo().getNumberOfArmy())) {
			System.out.println("\nFail to execute Deploy order: the player " + l_player.getColour() + " doesn't have adequate armies to deploy!\n");
			return false;
		}

		Country l_country = l_player.getCountriesInControl().get(l_destination.toLowerCase());
		l_country.deployArmies(l_armiesToDeploy);
		System.out.println("\nExecution is completed: deploy " + l_armiesToDeploy + " armies to Country " + l_country.getName() + ".\n");

		return true;
	}
}
