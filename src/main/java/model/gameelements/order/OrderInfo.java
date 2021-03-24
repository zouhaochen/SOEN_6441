package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;

/**
 * The type Order info.
 */
public class OrderInfo {

    /**
     * The action player.
     */
    private Player d_Initiator;
    /**
     * The target player.
     */
    private Player d_TargetPlayer;
    /**
     * The departure country.
     */
    private Country d_Departure;
    /**
     * The destination country.
     */
    private Country d_Destination;
    /**
     * The number of army to be moved.
     */
    private int d_NumberOfArmy;

    /**
     * Gets initiator.
     *
     * @return the initiator
     */
    public Player getInitiator() {
        return d_Initiator;
    }

    /**
     * Sets initiator.
     *
     * @param p_Player the initiator
     */
    public void setInitiator(Player p_Player) {
        this.d_Initiator = p_Player;
    }

    /**
     * Gets target player.
     *
     * @return the target player
     */
    public Player getTargetPlayer() {
        return d_TargetPlayer;
    }

    /**
     * Sets target player.
     *
     * @param p_TargetPlayer the target player
     */
    public void setTargetPlayer(Player p_TargetPlayer) {
        this.d_TargetPlayer = p_TargetPlayer;
    }

    /**
     * Gets departure country.
     *
     * @return the departure
     */
    public Country getDeparture() {
        return d_Departure;
    }

    /**
     * Sets departure country.
     *
     * @param p_Departure the p departure
     */
    public void setDeparture(Country p_Departure) {
        this.d_Departure = p_Departure;
    }

    /**
     * Gets destination country.
     *
     * @return the destination
     */
    public Country getDestination() {
        return d_Destination;
    }

    /**
     * Sets destination country.
     *
     * @param p_Destination the destination
     */
    public void setDestination(Country p_Destination) {
        this.d_Destination = p_Destination;
    }

    /**
     * Gets number of army.
     *
     * @return the number of army
     */
    public int getNumberOfArmy() {
        return d_NumberOfArmy;
    }

    /**
     * Sets number of army.
     *
     * @param p_NumberOfArmy the p number of army
     */
    public void setNumberOfArmy(int p_NumberOfArmy) {
        this.d_NumberOfArmy = p_NumberOfArmy;
    }
}
