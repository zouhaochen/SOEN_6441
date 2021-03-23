package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Player;

public class DiplomacyOrder extends Order {

	private Player d_player;
	private Player d_targetPlayer;

	/**
	 * Instantiates a new object of type DiplomacyOrder.
	 *
	 * @param p_player
	 * @param p_targetPlayer
	 */
	public DiplomacyOrder(Player p_player, Player p_targetPlayer) {
		super();
		setType("Advance");
		d_targetPlayer = p_targetPlayer;
		d_player = p_player;
	}

	/**
	 * get the player of the order
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return d_player;
	}
	
	/**
	 * get the target player of the order
	 *
	 * @return the Target player
	 */
	public Player getTargetPlayer() {
		return this.d_targetPlayer;
	}

	/**
	 * Executes a diplomacy order.
	 */
	public boolean execute() {
		if(!valid()) {
			System.out.println("Diplomacy invalid, player not exist");
			return false;
		}


		// setup diplomacy to each other
		d_player.setPlayerDiplomacy(d_targetPlayer);
		d_targetPlayer.setPlayerDiplomacy(d_player);

		// add order to game engine
/*		if(){

		}*/
		
		//print success information
		System.out.println("Success applying diplomacy");
		
		printOrder();
		return true;
	}

	/**
	 * check validate
	 *
	 * @return true if valid
	 */
	public boolean valid(){
		if(!d_player.getCards().contains(Card.NEGOTIATE)){
			System.out.println("Player " + d_player.getColour() + " does not have a diplomacy card");
			return false;
		}
		if(d_player != null && d_player.getPlayerExist()
				&& d_targetPlayer != null && d_player.getPlayerExist()
				&&  d_player != d_targetPlayer)
			return true;
		else
			return false;
	}

	/**
	 * print the order
	 */
	public void printOrder(){
		System.out.println(this.toString());
	}

	public String toString(){
		return String.format("Diplomacy order for player %s and player %s ",  this.d_player.getColour(), this.d_targetPlayer.getColour());
	}

}
