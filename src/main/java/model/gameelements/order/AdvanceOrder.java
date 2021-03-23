package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;

import java.util.Map;
import java.util.Random;

public class AdvanceOrder extends Order {

    private Country d_attackCountry;
    private Country d_defendCountry;
    private int d_numberOfArmies;
    private Player d_player;

    /**
     * Instantiates a new object of type AdvanceOrder.
     *
     * @param p_player
     * @param p_attackCountry
     * @param p_defendCountry
     * @param p_numberOfArmies
     */
    public AdvanceOrder(Player p_player, Country p_attackCountry, Country p_defendCountry, int p_numberOfArmies) {
        super();
        setType("Advance");
        d_player = p_player;
        d_attackCountry = p_attackCountry;
        d_defendCountry = p_defendCountry;
        d_numberOfArmies = p_numberOfArmies;
    }

    /**
     * Get attack country
     *
     * @return
     */
    public Country getAttackCountry() {
        return d_attackCountry;
    }

    /**
     * Set attack country
     *
     * @param attackCountry
     */
    public void setAttackCountry(Country attackCountry) {
        this.d_attackCountry = attackCountry;
    }

    /**
     * Get defend country
     *
     * @return
     */
    public Country getDefendCountry() {
        return d_defendCountry;
    }

    /**
     * Set defend country
     *
     * @param defendCountry
     */
    public void setDefendCountry(Country defendCountry) {
        this.d_defendCountry = defendCountry;
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
            if(d_attackCountry.getArmies() < d_numberOfArmies) {
                d_numberOfArmies = d_attackCountry.getArmies();
            }

            //if toCountry is owned by the player then advance armies
            if(d_defendCountry.getOwner().equals(d_player)) {
                //move armies
                d_attackCountry.setArmies(d_attackCountry.getArmies() - d_numberOfArmies);
                d_defendCountry.setArmies(d_defendCountry.getArmies() + d_numberOfArmies);
            }

            //else toCountry is owned by opponent then attack
            else {
                Random l_randomNumber = new Random();
                for(int i = 0; i < d_numberOfArmies; i++) {
                    if(d_defendCountry.getArmies() == 0) {
                        exchangeCountryOwner(d_defendCountry, d_player, d_numberOfArmies);
                        return true;
                    }

                    //the attack army has a 60% chance to defeat the defend army
                    if((l_randomNumber.nextInt(10) + 1) <= 6) {
                        //random int between 1 and 10 (inclusive)
                        //defeat defend army
                        d_defendCountry.setArmies(d_defendCountry.getArmies() - 1);
                        return true;
                    }

                    //the defend army has a 70% chance to defeat the attack army
                    if((l_randomNumber.nextInt(10) + 1) <= 7) {
                        //random int between 1 and 10 (inclusive)
                        //defeat attack army
                        d_attackCountry.setArmies(d_attackCountry.getArmies() - 1);
                        d_numberOfArmies--;
                        i--;
                    }
                }

                if(d_defendCountry.getArmies() == 0 && d_numberOfArmies > 0) {
                    exchangeCountryOwner(d_defendCountry, d_player, d_numberOfArmies);
                }
            }
        }
        return true;
    }

    /**
     * When attacker control defender's country,
     * this method performs the exchange of the countries and armies.
     *
     * @param p_TargetCountry
     * @param p_NewOwner
     * @param p_NumberOfArmies
     */
    private void exchangeCountryOwner(Country p_TargetCountry, Player p_NewOwner, int p_NumberOfArmies) {
        // get previous player from country obj
        Player l_PreviousOwner=p_TargetCountry.getOwner();
        Map<String, Country> l_PreOwnerCountryList=l_PreviousOwner.getCountriesInControl();
        for (Map.Entry<String,Country> l_Entry : l_PreOwnerCountryList.entrySet()){
            // check if country name in previous owner country list, then remove it.
            if (l_Entry.getKey().equalsIgnoreCase(p_TargetCountry.getCountryName())){
                l_PreOwnerCountryList.remove(l_Entry.getKey());
            }
        }
        // set previous owner his new country list
        l_PreviousOwner.setCountriesInControl(l_PreOwnerCountryList);
        // now change the target country's owner
        p_TargetCountry.setOwner(p_NewOwner);
        // assign the number of armies
        p_TargetCountry.setArmies(p_NumberOfArmies);
        // add target country to new owner's country list
        p_NewOwner.assignCountry(p_TargetCountry);
    }

    /**
     * check validate
     *
     * @return
     */
    public boolean valid(){
        boolean l_valid = true;
        Player l_targetPlayer = d_attackCountry.getOwner();
        if(l_targetPlayer == null || !l_targetPlayer.getPlayerExist()){
            System.out.println("player of target country does not exist" );
            l_valid = false;
        }

        //check whether player is fromCountry owner
        if(!d_attackCountry.getOwner().equals(d_player)) {
            System.out.println("Could not perform the advance order moving " + d_numberOfArmies + " armies from " +
                    d_attackCountry.getCountryName() + " to " + d_defendCountry.getCountryName() + " because " + d_player.getColour() + " does not own " + d_attackCountry + ".");
            l_valid =  false;
        }

        // if target country is un-attackable, because of diplomacy
        // only two countries with diplomacy cant attack each other
        // getPlayerDiplomacy()[1] is target player id that register during diploma order.
        if (l_targetPlayer.getPlayerDiplomacy()[1]==d_player.getId()){
            l_valid = false;
        }



        //check if fromCountry and toCountry are neighbors
        if(d_attackCountry.getBorderCountries().get(d_defendCountry.getCountryId()) == null) {
            System.out.println("Could not perform the advance order moving " + d_numberOfArmies + " armies from " +
                    d_attackCountry.getCountryName() + " to " + d_defendCountry.getCountryName() + " because they are not neighbors.");
            l_valid =  false;
        }

        return l_valid;
    }

    /**
     * print the order
     */
    public void printOrder(){

        System.out.println("Advance Order created: " + d_player.getColour() + " is sending " + d_numberOfArmies + " armies from " + d_attackCountry.getCountryName() + " to " + d_defendCountry.getCountryName());
    }
}
