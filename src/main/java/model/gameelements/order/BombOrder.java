package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

public class BombOrder extends Order {

    private int d_previousArmyNumber;
    private Country d_targetCountry;
    private Player d_player;

    public BombOrder(OrderInfo p_OrderInfo) {
        super();
        setType("Bomb");
        this.d_targetCountry = p_OrderInfo.getDestination();
        this.d_player = p_OrderInfo.getInitiator();
        this.setOrderInfo(p_OrderInfo);
    }

    /**
     * Get the player of the order
     *
     * @return the player
     */
    public Player getPlayer() {
        return d_player;
    }

    /**
     * Set the player of the order
     *
     * @param p_player the player
     */
    public void setPlayer(Player p_player) {
        d_player = p_player;
    }

    /**
     * Executes a bomb order.
     */
    @Override
    public boolean execute() {
        if (!valid()) {
            return false;
        }
        d_previousArmyNumber = d_targetCountry.getArmies();
        d_targetCountry.setArmies(d_previousArmyNumber / 2);

        printOrder();
        return true;
    }

    /**
     * check validate
     *
     * @return false if the current order is invalid
     */
    public boolean valid() {
        //check if the player has a bomb card
        if (!d_player.getCards().contains(Card.BOMB)) {
            System.out.println("Player " + d_player.getColour() + " does not have a bomb card");
            return false;
        }

        if (d_targetCountry == null) {
            System.out.println("Invalid country name.");
            return false;
        }

        //check whether player is the target country owner
        if (d_player.getCountriesInControl().containsKey(d_targetCountry.getName().toLowerCase())) {
            System.out.println("Can not bomb player's own country");
            return false;
        }

        //check whether the target country is adjacent to player's country
        boolean l_isAdjacent = false;
        for (Country l_Country : d_player.getCountriesInControl().values()) {
            if (l_Country.getBorderCountries().containsKey(d_targetCountry.getName().toLowerCase())) {
                l_isAdjacent = true;
                break;
            }
        }
        if (!l_isAdjacent) {
            System.out.println("The target country is not adjacent to one of the countries that belong to the player.");
            return false;
        }
        return true;
    }

    /**
     * print the order
     */
    @Override
    public void printOrder() {
        System.out.println("Bomb order issued by player " + this.d_player.getColour());
        System.out.println("Destroyed the armies in [COUNTRY-" + d_targetCountry.getName() + "] " + "from " + d_previousArmyNumber + " to " + d_targetCountry.getArmies() + ".");
    }

}
