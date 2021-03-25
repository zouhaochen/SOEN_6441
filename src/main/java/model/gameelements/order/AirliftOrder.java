package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

/**
 * The type Airlift order.
 */
public class AirliftOrder extends Order {

    /**
     * The D source country.
     */
    private Country d_SourceCountry;
    /**
     * The D target country.
     */
    private Country d_TargetCountry;
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
     * @param p_OrderInfo the p order info
     */
    public AirliftOrder(OrderInfo p_OrderInfo) {
        super();
        setType("Airlift");
        d_SourceCountry = p_OrderInfo.getDeparture();
        d_TargetCountry = p_OrderInfo.getDestination();
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

        d_ArmyNumber = Math.min(d_ArmyNumber, d_SourceCountry.getArmies());
        int l_armyInTarget = d_TargetCountry.getArmies() + d_ArmyNumber;
        int l_armyInSource = d_SourceCountry.getArmies() - d_ArmyNumber;
        d_TargetCountry.setArmies(l_armyInTarget);
        d_SourceCountry.setArmies(l_armyInSource);

        //remove card from player cards list
        d_Player.removeTargetCard(Card.AIRLIFT);

        //print success information
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
        //check if the player has a airlift card
        if (!d_Player.getCards().contains(Card.AIRLIFT)) {
            System.out.println("Player " + d_Player.getColour() + " does not have an airlift card");
            return false;
        }

        if (d_SourceCountry == null || d_TargetCountry == null) {
            System.out.println("Invalid country name");
            return false;
        }

        //check if countries belongs to the player
        if (!d_Player.getCountriesInControl().containsKey(d_SourceCountry.getName().toLowerCase())
                || !d_Player.getCountriesInControl().containsKey(d_TargetCountry.getName().toLowerCase())) {
            System.out.println("Source country or target country does not belongs to the player.");
            return false;
        }

        //check if army number is more than 0
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
        System.out.println("Airlift " + this.d_ArmyNumber + " from country " + d_SourceCountry.getName() + " to " + this.d_TargetCountry.getName());
    }
}
