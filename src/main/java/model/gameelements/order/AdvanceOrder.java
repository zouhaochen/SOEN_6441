package model.gameelements.order;

import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;

import java.util.Random;

public class AdvanceOrder extends Order {

    private Country d_fromCountry;
    private Country d_toCountry;
    private int d_numberOfArmies;
    private Player d_player;

    /**
     * Instantiates a new object of type AdvanceOrder.
     *
     * @param p_player
     * @param p_fromCountry
     * @param p_toCountry
     * @param p_numberOfArmies
     */
    public AdvanceOrder(Player p_player, Country p_fromCountry, Country p_toCountry, int p_numberOfArmies) {
        super();
        setType("Advance");
        d_player = p_player;
        d_fromCountry = p_fromCountry;
        d_toCountry = p_toCountry;
        d_numberOfArmies = p_numberOfArmies;
    }

    /**
     * Get attack country
     *
     * @return
     */
    public Country getFromCountry() {
        return d_fromCountry;
    }

    /**
     * Set attack country
     *
     * @param fromCountry
     */
    public void setFromCountry(Country fromCountry) {
        this.d_fromCountry = fromCountry;
    }

    /**
     * Get defend country
     *
     * @return
     */
    public Country getToCountry() {
        return d_toCountry;
    }

    /**
     * Set defend country
     *
     * @param toCountry
     */
    public void setToCountry(Country toCountry) {
        this.d_toCountry = toCountry;
    }

    /**
     * Get the number of armies the attacker wants to send out
     *
     * @return numberOfArmies
     */
    public int setNumberOfArmy() {
        return d_numberOfArmies;
    }

    /**
     * Set the number of armies the attacker wants to send out
     *
     * @param numberOfArmies The number of armies the attacker wants to send out
     */
    public void setNumberOfArmies(int numberOfArmies) {
        this.d_numberOfArmies = numberOfArmies;
    }

    /**
     * Get the player that initiated the advance order (the attacker)
     *
     * @return
     */
    public Player getPlayer() {
        return d_player;
    }

    /**
     * Set the player that initiated the advance order (the attacker)
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.d_player = player;
    }

    /**
     * Executes an advance order.
     *
     * @return
     */
    @Override
    public boolean execute() {

        if(valid()) {

            //check armies number to advance
            if(d_fromCountry.getArmies() < d_numberOfArmies) {
                d_numberOfArmies = d_fromCountry.getArmies();
            }

            //if toCountry is owned by the player then advance armies
            if(d_toCountry.getOwner().equals(d_player)) {
                //move armies
                d_fromCountry.setArmies(d_fromCountry.getArmies() - d_numberOfArmies);
                d_toCountry.setArmies(d_toCountry.getArmies() + d_numberOfArmies);
            }

            //else toCountry is owned by opponent then attack
            else {
                Random l_randomNumber = new Random();
                for(int i = 0; i < d_numberOfArmies; i++) {
                    if(d_toCountry.getArmies() == 0) {
                        exchangeCountryOwner(d_toCountry, d_fromCountry, d_numberOfArmies);
                        return true;
                    }

                    //the attack army has a 60% chance to defeat the defend army
                    if((l_randomNumber.nextInt(10) + 1) <= 6) {
                        //random int between 1 and 10 (inclusive)
                        //defeat defend army
                        d_toCountry.setArmies(d_toCountry.getArmies() - 1);
                        return true;
                    }

                    //the defend army has a 70% chance to defeat the attack army
                    if((l_randomNumber.nextInt(10) + 1) <= 7) {
                        //random int between 1 and 10 (inclusive)
                        //defeat attack army
                        d_fromCountry.setArmies(d_fromCountry.getArmies() - 1);
                        d_numberOfArmies--;
                        i--;
                    }
                }

                if(d_toCountry.getArmies() == 0 && d_numberOfArmies > 0) {
                    exchangeCountryOwner(d_toCountry, d_fromCountry, d_numberOfArmies);
                }
            }
        }
        return true;
    }

    /**
     * When attacker control defender's country,
     * this method performs the exchange of the countries and armies.
     *
     * @param p_toCountry
     * @param p_fromCountry
     * @param p_numberOfArmies
     */
    private void exchangeCountryOwner(Country p_toCountry, Country p_fromCountry, int p_numberOfArmies) {
    }

    /**
     * check validate
     *
     * @return
     */
    public boolean valid(){
        boolean l_valid = true;
        Player l_targetPlayer = d_fromCountry.getOwner();
        if(l_targetPlayer == null || !l_targetPlayer.getPlayerExist()){
            System.out.println("player of target country does not exist" );
            l_valid = false;
        }

        //check whether player is fromCountry owner
        if(!d_fromCountry.getOwner().equals(d_player)) {
            System.out.println("Could not perform the advance order moving " + d_numberOfArmies + " armies from " +
                    d_fromCountry.getCountryName() + " to " + d_toCountry.getCountryName() + " because " + d_player.getColour() + " does not own " + d_fromCountry + ".");
            l_valid =  false;
        }

        //check if DIPLOMACY
        /*if(){

        }*/

        //check if fromCountry and toCountry are neighbors
        if(d_fromCountry.getBorderCountries().get(d_toCountry.getCountryId()) == null) {
            System.out.println("Could not perform the advance order moving " + d_numberOfArmies + " armies from " +
                    d_fromCountry.getCountryName() + " to " + d_toCountry.getCountryName() + " because they are not neighbors.");
            l_valid =  false;
        }

        return l_valid;
    }

    /**
     * print the order
     */
    public void printOrder(){

        System.out.println("Advance Order created: " + d_player.getColour() + " is sending " + d_numberOfArmies + " armies from " + d_fromCountry.getCountryName() + " to " + d_toCountry.getCountryName());
    }
}
