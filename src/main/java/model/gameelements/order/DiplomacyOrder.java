package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Player;

/**
 * The type Diplomacy order.
 */
public class DiplomacyOrder extends Order {

    /**
     * The player.
     */
    private Player d_Player;
    /**
     * The target player.
     */
    private Player d_TargetPlayer;

    /**
     * Instantiates a new object of type DiplomacyOrder.
     *
     * @param p_Player       the p player
     * @param p_TargetPlayer the p target player
     */
    public DiplomacyOrder(Player p_Player, Player p_TargetPlayer) {
        super();
        setType("Diplomacy");
        d_TargetPlayer = p_TargetPlayer;
        d_Player = p_Player;
    }

    /**
     * Instantiates a new Diplomacy order.
     *
     * @param p_OrderInfo the order info
     */
    public DiplomacyOrder(OrderInfo p_OrderInfo) {
        this(p_OrderInfo.getInitiator(), p_OrderInfo.getTargetPlayer());
        this.setOrderInfo(p_OrderInfo);
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
     * get the target player of the order
     *
     * @return the Target player
     */
    public Player getTargetPlayer() {
        return this.d_TargetPlayer;
    }

    /**
     * Executes a diplomacy order.
     */
    public boolean execute() {
        if (!valid()) {
            return false;
        }

        // Setup diplomacy to each other
        d_Player.getUnattackablePlayers().add(d_TargetPlayer.getId());
        d_TargetPlayer.getUnattackablePlayers().add(d_Player.getId());

        // Print success information
        System.out.println("Success applying diplomacy");

        // Remove card from player cards list
        d_Player.removeTargetCard(Card.NEGOTIATE);

        printOrder();
        return true;
    }

    /**
     * check validate
     *
     * @return true if valid
     */
    public boolean valid() {

        // Check whether the player has a negotiate card
        if (!d_Player.getCards().contains(Card.NEGOTIATE)) {
            System.out.println("Invalid Diplomacy Order: Player " + d_Player.getColour() + " does not have a diplomacy card.");
            return false;
        }

        // Check whether the player is not null and exist
        if ((d_Player != null && d_Player.getPlayerExist())
                && (d_TargetPlayer != null && d_TargetPlayer.getPlayerExist())
                && d_Player != d_TargetPlayer) {

            return true;
        } else {
            System.out.println("Invalid Diplomacy Order: Target player is not valid.");
            return false;
        }
    }

    /**
     * print the order
     */
    @Override
    public void printOrder() {
        System.out.println("Diplomacy order issued by player " + d_Player.getColour());
        System.out.println(this.toString());
    }

    /**
     * Put the information of the order in a string
     *
     * @return a string
     */
    @Override
    public String toString() {
        return String.format("Diplomacy order for player %s and player %s ", this.d_Player.getColour(), this.d_TargetPlayer.getColour());
    }

}
