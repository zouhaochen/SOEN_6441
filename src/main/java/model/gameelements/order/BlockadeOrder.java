package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

/**
 * The type Blockade order.
 */
public class BlockadeOrder extends Order {

    /**
     * The target country.
     */
    private Country d_TargetCountry;
    /**
     * The player.
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
        // Triple the number of armies
        // On one target country of player
        d_TargetCountry.setArmies(d_TargetCountry.getArmies() * 3);
        // Remove target country from controlled countries
        d_TargetCountry.getOwner().getCountriesInControl().remove(d_TargetCountry.getName().toLowerCase());
        // Check if there is a Player NEUTRAL
        if (getOrderInfo().getGameData().getNeutralPlayer() == null) {
            getOrderInfo().getGameData().setNeutralPlayer(new Player("NEUTRAL"));
        }
        // Set owner to Player NEUTRAL
        d_TargetCountry.setOwner(getOrderInfo().getGameData().getNeutralPlayer());
        getOrderInfo().getGameData().getNeutralPlayer().assignCountry(d_TargetCountry);

        // Remove card from player cards list
        d_Player.removeTargetCard(Card.BLOCKADE);

        // Print success information
        System.out.println("Success applying Blockage");

        printOrder();
        return true;
    }

    /**
     * check validate
     *
     * @return true if valid
     */
    public boolean valid() {

        // Check if the player has a blockade card
        if (!d_Player.getCards().contains(Card.BLOCKADE)) {
            System.out.println("Invalid Blockade Order: Player " + d_Player.getColour() + " does not have a blockade card");
            return false;
        }

        // Check if the player exist
        if (!d_Player.getPlayerExist()) {
            System.out.println("Invalid Blockade Order: Player " + d_Player.getColour() + " does not exist");
            return false;
        }

        // Check if the target country is null
        if (d_TargetCountry == null) {
            System.out.println("Invalid Blockade Order: Invalid country");
            return false;
        }

        // Check if player own the target country
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
