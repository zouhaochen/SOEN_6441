package gameelements.order;

import gameelements.Country;
import gameelements.Player;

/**
 * The class to represent a Deploy order, which inherits from class of type Order .
 */
public class DeployOrder extends Order {

    /**
     * Instantiates a new object of type DeployOrder.
     */
    public DeployOrder() {
        super();
        setType("Deploy");
    }

    /**
     * Executes an deploy order.
     *
     * @return if the order executes successfully, false otherwise
     */
    @Override
    public boolean execute() {

        if (getOrderInfo().getPlayer() == null || getOrderInfo().getDestination() == null) {
            System.out.println("Fail to execute Deploy order: Invalid order information.");
            return false;
        }

        Player l_Player = getOrderInfo().getPlayer();
        String l_Destination = getOrderInfo().getDestination();

        // If the player decides to deploy armies to the country he/she doesn't control, the player will lost the armies.
        if (!l_Player.getCountriesInControl().containsKey(l_Destination.toLowerCase())) {
            System.out.println("\nFail to execute Deploy order: the country " + l_Destination + " is not in the control of player " + l_Player.getColour() + ".");
            return false;
        }

        // check if the subtraction manages to execute
        int l_ArmiesToDeploy = getOrderInfo().getNumberOfArmy();
        if (!l_Player.deployReinforcementArmies(getOrderInfo().getNumberOfArmy())) {
            System.out.println("\nFail to execute Deploy order: the player " + l_Player.getColour() + " doesn't have adequate armies to deploy!\n");
            return false;
        }

        Country l_Country = l_Player.getCountriesInControl().get(l_Destination.toLowerCase());
        l_Country.deployArmies(l_ArmiesToDeploy);

        System.out.println("\nExecution is completed: deploy " + l_ArmiesToDeploy + " armies to Country " + l_Country.getName() + ".");
        System.out.println("\nArmies left: " + l_Player.getReinforcementArmies());

        return true;
    }
}
