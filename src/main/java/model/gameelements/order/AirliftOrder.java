package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

/**
 * The type Airlift order.
 */
public class AirliftOrder extends Order {

    /**
     * The D departure country.
     */
    private Country d_DepartureCountry;
    /**
     * The D destination country.
     */
    private Country d_DestinationCountry;
    /**
     * The D army number.
     */
    private int d_ArmyNumber;
    /**
     * The D player.
     */
    private Player d_Player;

    /**
     * Instantiates a new Airlift order.
     *
     * @param p_Player         the player
     * @param p_Departure      the departure
     * @param p_Destination    the destination
     * @param p_NumberOfArmies the number of armies
     */
    public AirliftOrder(Player p_Player, Country p_Departure, Country p_Destination, int p_NumberOfArmies) {
        super();
        setType("Airlift");
        d_Player = p_Player;
        d_DepartureCountry = p_Departure;
        d_DestinationCountry = p_Destination;
        d_ArmyNumber = p_NumberOfArmies;

        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Player);
        l_OrderInfo.setDeparture(p_Departure);
        l_OrderInfo.setDestination(p_Destination);
        l_OrderInfo.setNumberOfArmy(p_NumberOfArmies);
        setOrderInfo(l_OrderInfo);
    }

    /**
     * Instantiates a new Airlift order.
     *
     * @param p_OrderInfo the p order info
     */
    public AirliftOrder(OrderInfo p_OrderInfo) {
        super();
        setType("Airlift");
        d_DepartureCountry = p_OrderInfo.getDeparture();
        d_DestinationCountry = p_OrderInfo.getDestination();
        d_ArmyNumber = p_OrderInfo.getNumberOfArmy();
        d_Player = p_OrderInfo.getInitiator();
    }

    /**
     * get the player of the order
     *
     * @return the player
     */
    public Player getPlayer() {
        return d_Player;
    }

    /**
     * set the player of the order
     *
     * @param p_player the player
     */
    public void setPlayer(Player p_player) {
        d_Player = p_player;
    }

    /**
     * Executes an airlift order.
     */
    @Override
    public boolean execute() {
        if (!valid()) {
            return false;
        }

        d_ArmyNumber = Math.min(d_ArmyNumber, d_DepartureCountry.getArmies());
        int l_armyInTarget = d_DestinationCountry.getArmies() + d_ArmyNumber;
        int l_armyInSource = d_DepartureCountry.getArmies() - d_ArmyNumber;
        d_DestinationCountry.setArmies(l_armyInTarget);
        d_DepartureCountry.setArmies(l_armyInSource);

        // Remove card from player cards list
        d_Player.removeTargetCard(Card.AIRLIFT);

        // Print success information
        System.out.println("Success applying Airlift");

        printOrder();
        return true;
    }

    /**
     * check validate
     *
     * @return true if valid
     */
    public boolean valid() {
        // Check if the player has a airlift card
        if (!d_Player.getCards().contains(Card.AIRLIFT)) {
            System.out.println("Player " + d_Player.getColour() + " does not have an airlift card");
            return false;
        }

        // Check if departure country and destination country are null
        if (d_DepartureCountry == null || d_DestinationCountry == null) {
            System.out.println("Invalid country name");
            return false;
        }

        // Check if departure country and destination belong to the player
        if (!d_Player.getCountriesInControl().containsKey(d_DepartureCountry.getName().toLowerCase())
                || !d_Player.getCountriesInControl().containsKey(d_DestinationCountry.getName().toLowerCase())) {
            System.out.println("Source country or target country does not belongs to the player.");
            return false;
        }

        // Check if army number is more than 0
        if (d_ArmyNumber <= 0) {
            System.out.println("The number of airlift army should be larger than 0.");
            return false;
        }

        return true;
    }

    /**
     * print the order
     */
    public void printOrder() {
        System.out.println("Airlift order issued by player " + this.d_Player.getColour());
        System.out.println("Airlift " + this.d_ArmyNumber + " from country " + d_DepartureCountry.getName() + " to " + this.d_DestinationCountry.getName());
    }
}
