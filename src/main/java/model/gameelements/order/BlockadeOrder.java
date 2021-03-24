package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

/**
 * The type Blockade order.
 */
public class BlockadeOrder extends Order {

    /**
     * The D target country.
     */
    private Country d_targetCountry;
    /**
     * The D player.
     */
    private Player d_player;

    /**
     * Instantiates a new object of type BlockadeOrder.
     *
     * @param p_Player        current player
     * @param p_TargetCountry target country id
     */
    public BlockadeOrder(Player p_Player, Country p_TargetCountry) {
        super();
        setType("Blockade");
        d_player = p_Player;
        d_targetCountry = p_TargetCountry;
        setOrderInfo(new OrderInfo());
        getOrderInfo().setInitiator(p_Player);
        getOrderInfo().setDestination(p_TargetCountry);
    }

    /**
     * Instantiates a new Blockade order.
     *
     * @param p_OrderInfo the order info
     */
    public BlockadeOrder(OrderInfo p_OrderInfo) {
        super();
        setType("Blockade");
        d_targetCountry = p_OrderInfo.getDestination();
        d_player = p_OrderInfo.getInitiator();
        setOrderInfo(p_OrderInfo);
    }

    /**
     * Executes a blockade order.
     */
    @Override
    public boolean execute() {
        if (!this.valid()) {
            return false;
        }
        //triple the number of armies on one of the current player's territories
        d_targetCountry.setArmies(d_targetCountry.getArmies() * 3);
        //remove target country from conquered countries
        d_targetCountry.getOwner().getCountriesInControl().remove(d_targetCountry.getName().toLowerCase());
        // check if there is a Player NEUTRAL
        if (getOrderInfo().getGameData().getPlayerByName("NEUTRAL") == null) {
            getOrderInfo().getGameData().getPlayerList().add(new Player("NEUTRAL"));
        }
        //set owner to Player NEUTRAL
        d_targetCountry.setOwner(getOrderInfo().getGameData().getPlayerByName("NEUTRAL"));
        return true;
    }

    /**
     * check validate
     *
     * @return true if valid
     */
    public boolean valid() {

        if (!d_player.getCards().contains(Card.BLOCKADE)) {
            System.out.println("Invalid Blockade Order: Player " + d_player.getColour() + " does not have a blockade card");
            return false;
        }

        if (!d_player.getPlayerExist()) {
            System.out.println("Invalid Blockade Order: Player " + d_player.getColour() + " does not exist");
            return false;
        }

        if (d_targetCountry == null) {
            System.out.println("Invalid Blockade Order: Invalid country");
            return false;
        }

        if (!d_player.getCountriesInControl().containsKey(d_targetCountry.getName().toLowerCase())) {
            System.out.println("Invalid Blockade Order: Player " + d_player.getColour() + " doesn't own Country " + d_targetCountry.getName());
            return false;
        }

        return true;
    }

    /**
     * print the order
     */
    public void printOrder() {
        System.out.println("Blockade order issued by player " + this.d_player.getColour());
        System.out.println("Blockade " + this.d_targetCountry.getName());
    }

}
