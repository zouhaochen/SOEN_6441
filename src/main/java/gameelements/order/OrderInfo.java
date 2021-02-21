package gameelements.order;

import gameelements.Country;
import gameelements.Player;

/**
 * The type Order info.
 */
public class OrderInfo {

	/**
	 * The player.
	 */
	private Player d_Player;
	/**
	 * The departure country.
	 */
	private Country d_Departure;
	/**
	 * The destination country.
	 */
	private String d_Destination;
	/**
	 * The number of army to be moved.
	 */
	private int d_NumberOfArmy;

	/**
	 * Gets player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return d_Player;
	}

	/**
	 * Sets player.
	 *
	 * @param p_Player the p player
	 */
	public void setPlayer(Player p_Player) {
		this.d_Player = p_Player;
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
	public String getDestination() {
		return d_Destination;
	}

	/**
	 * Sets destination country.
	 *
	 * @param p_Destination the destination
	 */
	public void setDestination(String p_Destination) {
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
