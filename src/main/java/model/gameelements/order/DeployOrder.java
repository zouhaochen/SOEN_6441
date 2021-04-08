package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;

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

    public DeployOrder(Player p_Initiator, Country p_Destination, int p_ArmiesToDeploy) {
        this();
        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Initiator);
        l_OrderInfo.setDestination(p_Destination);
        l_OrderInfo.setNumberOfArmy(p_ArmiesToDeploy);

        this.setOrderInfo(l_OrderInfo);
    }

    /**
     * Executes an deploy order.
     *
     * @return if the order executes successfully, false otherwise
     */
    @Override
    public boolean execute() {

        if (!valid()) {
            return false;
        }

        Player l_Player = getOrderInfo().getInitiator();
        int l_ArmiesToDeploy = getOrderInfo().getNumberOfArmy();
        l_ArmiesToDeploy = Math.min(l_ArmiesToDeploy, l_Player.getReinforcementArmies());
        getOrderInfo().setNumberOfArmy(l_ArmiesToDeploy);

        // deploy the armies, if there not enough armies left, deploy all the armies.
        Country l_Country = getOrderInfo().getDestination();
        l_Country.deployArmies(l_ArmiesToDeploy);
        if (!l_Player.deployReinforcementArmies(getOrderInfo().getNumberOfArmy())) {
            System.out.println("\nWarning: insufficient armies to deploy. Deploy only " + l_ArmiesToDeploy + " armies to Country " + l_Country.getName() + ".");
        }

        printOrder();
        return true;
    }

    /**
     * Check if the order is valid.
     *
     * @return true if the order is valid; false otherwise
     */
    @Override
    public boolean valid() {
        if (getOrderInfo().getInitiator() == null || getOrderInfo().getDestination() == null) {
            System.out.println("Invalid DEPLOY order: missing order information.");
            return false;
        }

        Player l_Player = getOrderInfo().getInitiator();
        String l_Destination = getOrderInfo().getDestination().getName();
        int l_ArmiesToDeploy = getOrderInfo().getNumberOfArmy();

        if (l_ArmiesToDeploy <= 0) {
            System.out.println("Invalid DEPLOY order: the number of armies is not positive.");
            return false;
        }

        l_ArmiesToDeploy = Math.min(l_ArmiesToDeploy, l_Player.getReinforcementArmies());

        // If the player decides to deploy armies to the country he/she doesn't control, the player will lost the armies.
        if (!l_Player.getCountriesInControl().containsKey(l_Destination.toLowerCase())) {
            l_Player.setReinforcementArmies(l_Player.getReinforcementArmies() - l_ArmiesToDeploy);
            System.out.println("Invalid DEPLOY order: the country " + l_Destination + " is not in the control of player " + l_Player.getColour() + ".");
            System.out.println("The player " + l_Player.getColour() + " will lose " + l_ArmiesToDeploy + " armies.");
            return false;
        }

        return true;
    }

    /**
     * print the order
     */
    @Override
    public void printOrder() {
        System.out.println("Deploy order issued by player " + getOrderInfo().getInitiator().getColour());
        System.out.println("Deploy " + getOrderInfo().getNumberOfArmy() + " armies to " + getOrderInfo().getDestination().getName());
    }
}
