package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

public class BlockadeOrder extends Order {

	/**
	 * Define target country ID variables with int
	 */
    private int d_targetCountryId;
	/**
	 * Define target country variables with country type.
	 */
    private Country d_targetCountry;

	/**
	 * Define game player variables with player type
	 */
    private Player d_player;
    
    /**
     * Instantiates a new object of type BlockadeOrder.
	 *
     * @param p_player current player
     * @param p_targetCountryId target country id
     */
    public BlockadeOrder(Player p_player, int p_targetCountryId) {
		super();
		setType("Blockade");
		d_targetCountryId = p_targetCountryId;
        d_player=p_player;
    }

    /**
     *  Executes a blockade order.
     */
	@Override
	public boolean execute() {
		if(!this.valid())
			return false;
		//triple the number of armies on one of the current player's territories
		d_targetCountry.setArmies(d_targetCountry.getArmies()*3);
		//remove target country from conquered countries
		d_targetCountry.getOwner().getCountriesInControl().remove(d_targetCountryId);
		//set owner to null
		d_targetCountry.setOwner(null);
		return true;
	}

    /**
     * check validate
	 *
     * @return true if valid
     */
	public boolean valid() {

		if(!d_player.getCards().contains(Card.BLOCKADE)){
			System.out.println("Player " + d_player.getColour() + " does not have a blockade card");
			return false;
		}

		if(!d_player.getPlayerExist()) {
			System.out.println("Player "+d_player.getColour()+" does not exist");
			return false;
		}

		d_targetCountry=d_player.getCountriesInControl().get(d_targetCountryId);
		if(d_targetCountry!=null) {
			return true;
		}else {
			System.out.println("Blockade order invalid: target country does not belong to current player!");
			return false;
		}
	}

    /**
     * print the order
     */
	public void printOrder() {
		System.out.println("Blockade order issued by player " + this.d_player.getColour());
		System.out.println("Blockade " + this.d_targetCountry.getName());
	}

}
