package gameelements.order;

import gameelements.Country;
import gameelements.Player;

public class OrderInfo {

	private Player d_player;
	private Country d_departure;
	private String d_destination;
	private int d_numberOfArmy;

	public Player getPlayer() {
		return d_player;
	}

	public void setPlayer(Player p_player) {
		this.d_player = p_player;
	}

	public Country getDeparture() {
		return d_departure;
	}

	public void setDeparture(Country p_departure) {
		this.d_departure = p_departure;
	}

	public String getDestination() {
		return d_destination;
	}

	public void setDestination(String p_destination) {
		this.d_destination = p_destination;
	}

	public int getNumberOfArmy() {
		return d_numberOfArmy;
	}

	public void setNumberOfArmy(int p_numberOfArmy) {
		this.d_numberOfArmy = p_numberOfArmy;
	}
}
