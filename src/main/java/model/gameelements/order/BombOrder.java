package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

public class BombOrder extends Order{

    private int d_targetCountryId;
    private int d_previousArmyNumber;
    private Country d_targetCountry;
    private Player d_player;

    /**
     * Instantiates a new object of type BombOrder.
     *
     * @param p_targetCountryId the target country id
     */
    public BombOrder(int p_targetCountryId) {
        super();
        setType("Bomb");
        this.d_targetCountryId = p_targetCountryId;
    }

    /**
     * Set the player of the order
     *
     * @param p_player the player
     */
    public void setPlayer(Player p_player){
        d_player = p_player;
    }

    /**
     * Get the player of the order
     *
     * @return the player
     */
    public Player getPlayer() {
        return  d_player;
    }

    /**
     * Executes a bomb order.
     */
    @Override
    public boolean execute() {
        if(!valid())
            return false;
        d_previousArmyNumber = d_targetCountry.getArmies();
        d_targetCountry.setArmies( d_previousArmyNumber/ 2);
        System.out.println("bomb the target country");
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
        if(!d_player.getCards().contains(Card.BOMB)){
            System.out.println("Player " + d_player.getColour() + " does not have a bomb card");
            return false;
        }

        //check whether player is the target country owner
        if(d_player.getCountriesInControl().containsKey(d_targetCountryId)){
            System.out.println("Can not bomb player's own country");
            return false;
        }

        //check whether the target country is adjacent to player's country
        boolean l_isAdjacent = false;
        for (String l_conqueredCountryId : d_player.getCountriesInControl().keySet()) {
            if (d_player.getCountriesInControl().get(l_conqueredCountryId).getBorderCountries().containsKey(d_targetCountryId)) {
                d_targetCountry = d_player.getCountriesInControl().get(l_conqueredCountryId).getBorderCountries().get(d_targetCountryId);
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
    public void printOrder() {
        System.out.println("Bomb order issued by player " + this.d_player.getColour());
        System.out.println("destryed the armies in [COUNTRY-" + d_targetCountry.getCountryId() + "] " + "from " + d_previousArmyNumber + " to " + d_targetCountry.getArmies() + ".");
    }

}
