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
    private Country d_TargetCountry;
    /**
     * The D player.
     */
    private Player d_Player;

    /**
     * Instantiates a new object of type BlockadeOrder.
     *
     * @param p_Player        current player
     * @param p_TargetCountry target country id
     */
    public BlockadeOrder(Player p_Player, Country p_TargetCountry) {
        super();
        setType("Blockade");
        d_Player = p_Player;
        d_TargetCountry = p_TargetCountry;
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
        d_TargetCountry = p_OrderInfo.getDestination();
        d_Player = p_OrderInfo.getInitiator();
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
        d_TargetCountry.setArmies(d_TargetCountry.getArmies() * 3);
        //remove target country from conquered countries
        d_TargetCountry.getOwner().getCountriesInControl().remove(d_TargetCountry.getName().toLowerCase());
        // check if there is a Player NEUTRAL
        if (getOrderInfo().getGameData().getPlayerByName("NEUTRAL") == null) {
            getOrderInfo().getGameData().getPlayerList().add(new Player("NEUTRAL"));
        }
        //set owner to Player NEUTRAL
        d_TargetCountry.setOwner(getOrderInfo().getGameData().getPlayerByName("NEUTRAL"));

		//remove card from player cards list
		d_Player.removeTargetCard(Card.BLOCKADE);

		//print success information
		System.out.println("Success applying Blockage");

        return true;
    }

    /**
     * check validate
     *
     * @return true if valid
     */
    public boolean  valid() {

        if (!d_Player.getCards().contains(Card.BLOCKADE)) {
            System.out.println("Invalid Blockade Order: Player " + d_Player.getColour() + " does not have a blockade card");
            return false;
        }

        if (!d_Player.getPlayerExist()) {
            System.out.println("Invalid Blockade Order: Player " + d_Player.getColour() + " does not exist");
            return false;
        }

        if (d_TargetCountry == null) {
            System.out.println("Invalid Blockade Order: Invalid country");
            return false;
        }

        if (!d_Player.getCountriesInControl().containsKey(d_TargetCountry.getName().toLowerCase())) {
            System.out.println("Invalid Blockade Order: Player " + d_Player.getColour() + " doesn't own Country " + d_TargetCountry.getName());
            return false;
        }

        return true;
    }

    /**
     * print the order
     */
    public void printOrder() {
        System.out.println("Blockade order issued by player " + this.d_Player.getColour());
        System.out.println("Blockade " + this.d_TargetCountry.getName());
    }

}
