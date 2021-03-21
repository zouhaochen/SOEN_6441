package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

public class AirliftOrder extends Order{

    private int d_sourceCountryId;
    private int d_targetCountryId;
    private Country d_sourceCountry;
    private Country d_targetCountry;
    private int d_armyNumber;
    private Player d_player;

    /**
     * Instantiates a new object of type AirliftOrder.
     *
     * @param p_sourceCountryId source country
     * @param p_tragetCountryId target country
     * @param p_armyNumber army number
     */
    public AirliftOrder(int p_sourceCountryId, int p_tragetCountryId, int p_armyNumber){
        super();
        setType("Airlift");
        d_sourceCountryId = p_sourceCountryId;
        d_targetCountryId = p_tragetCountryId;
        d_armyNumber = p_armyNumber;
    }

    /**
     * set the player of the order
     *
     * @param p_player the player
     */
    public void setPlayer(Player p_player){
        d_player = p_player;
    }

    /**
     * get the player of the order
     *
     * @return the player
     */
    public Player getPlayer() {
        return  d_player;
    }

    /**
     *  Executes an airlift order.
     */
    @Override
    public boolean execute(){
        if(!valid())
            return false;
        int l_armyInTarget = d_targetCountry.getArmies() + d_armyNumber;
        int l_armyInSource = d_sourceCountry.getArmies() - d_armyNumber;
        d_targetCountry.setArmies(l_armyInTarget);
        d_sourceCountry.setArmies(l_armyInSource);
        System.out.println("Success airlifting army.");
        printOrder();
        return true;
    }

    /**
     * check validate
     *
     * @return true if valid
     */
    public boolean valid(){
        //check if the player has a airlift card
        if(!d_player.getCards().contains(Card.AIRLIFT)){
            System.out.println("Player " + d_player.getColour() + " does not have an airlift card");
            return false;
        }
        //check if countries belongs to the player
        if(!d_player.getCountriesInControl().containsKey(d_sourceCountryId) || !d_player.getCountriesInControl().containsKey(d_targetCountryId)){
            System.out.println("Source country or target country does not belongs to the player.");
            return false;
        }
        //get the countries
        d_sourceCountry = d_player.getCountriesInControl().get(d_sourceCountryId);
        d_targetCountry = d_player.getCountriesInControl().get(d_targetCountryId);
        //check if army number is more than 0
        if(d_armyNumber <= 0){
            System.out.println("The number of airlift army shoud more than 0.");
            return false;
        }
        //check if army number is more that they own
        if(d_sourceCountry.getArmies() < d_armyNumber) {
            System.out.println("Player does not have enough army in country "+ d_sourceCountry.getCountryName());
            return false;
        }
        return true;
    }

    /**
     * print the order
     */
    public void printOrder(){
        System.out.println("Airlift order issued by player " + this.d_player.getColour());
        System.out.println("Airlift " + this.d_armyNumber + " from country " + d_sourceCountry.getCountryName() + " to " + this.d_targetCountry.getCountryName());
    }
}
