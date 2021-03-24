package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;

import java.util.Map;
import java.util.Random;

/**
 * The type Advance order.
 */
public class AdvanceOrder extends Order {

    private Country d_AttackCountry;
    private Country d_DefendCountry;
    private int d_NumberOfArmies;
    private Player d_Player;

    /**
     * Instantiates a new object of type AdvanceOrder.
     *
     * @param p_Player         the p player
     * @param p_AttackCountry  the p attack country
     * @param p_DefenceCountry the p defend country
     * @param p_NumberOfArmies the p number of armies
     */
    public AdvanceOrder(Player p_Player, Country p_AttackCountry, Country p_DefenceCountry, int p_NumberOfArmies
    ) {
        super();
        setType("Advance");
        d_Player = p_Player;
        d_AttackCountry = p_AttackCountry;
        d_DefendCountry = p_DefenceCountry;
        d_NumberOfArmies = p_NumberOfArmies
        ;
    }

    /**
     * Instantiates a new Advance order.
     *
     * @param p_OrderInfo the order info
     */
    public AdvanceOrder(OrderInfo p_OrderInfo) {
        this(p_OrderInfo.getInitiator(), p_OrderInfo.getDeparture(), p_OrderInfo.getDestination(), p_OrderInfo.getNumberOfArmy());
        this.setOrderInfo(p_OrderInfo);
    }

    /**
     * Get attack country
     *
     * @return attack country
     */
    public Country getAttackCountry() {
        return d_AttackCountry;
    }

    /**
     * Set attack country
     *
     * @param p_AttackCountry the attack country
     */
    public void setAttackCountry(Country p_AttackCountry) {
        this.d_AttackCountry = p_AttackCountry;
    }

    /**
     * Get defend country
     *
     * @return defend country
     */
    public Country getDefendCountry() {
        return d_DefendCountry;
    }

    /**
     * Set defend country
     *
     * @param p_DefendCountry the defend country
     */
    public void setDefendCountry(Country p_DefendCountry) {
        this.d_DefendCountry = p_DefendCountry;
    }

    /**
     * Get the number of armies the attacker wants to send out
     *
     * @return numberOfArmies number of army
     */
    public int setNumberOfArmy() {
        return d_NumberOfArmies;
    }

    /**
     * Set the number of armies the attacker wants to send out
     *
     * @param numberOfArmies The number of armies the attacker wants to send out
     */
    public void setNumberOfArmies(int numberOfArmies) {
        this.d_NumberOfArmies = numberOfArmies;
    }

    /**
     * Get the player that initiated the advance order (the attacker)
     *
     * @return player
     */
    public Player getPlayer() {
        return d_Player;
    }

    /**
     * Set the player that initiated the advance order (the attacker)
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.d_Player = player;
    }

    /**
     * Executes an advance order.
     *
     * @return true if the order execute successfully; false otherwise
     */
    @Override
    public boolean execute() {

        if (valid()) {

            //check armies number to advance
            if (d_AttackCountry.getArmies() < d_NumberOfArmies) {
                d_NumberOfArmies = d_AttackCountry.getArmies();
            }

            //if toCountry is owned by the player then advance armies
            if (d_DefendCountry.getOwner().getId() == d_Player.getId()) {
                //move armies
                d_AttackCountry.setArmies(d_AttackCountry.getArmies() - d_NumberOfArmies);
                d_DefendCountry.setArmies(d_DefendCountry.getArmies() + d_NumberOfArmies);
            }

            //else toCountry is owned by opponent then attack
            else {
                Random l_randomNumber = new Random();
                int l_SurvivingArmies = d_NumberOfArmies;
                for (int i = 0; i < d_NumberOfArmies; i++) {
                    if (d_DefendCountry.getArmies() == 0) {
                        exchangeCountryOwner(d_DefendCountry, d_Player, d_NumberOfArmies);
                        printOrder();
                        return true;
                    }

                    //the attack army has a 60% chance to defeat the defend army
                    if ((l_randomNumber.nextInt(10) + 1) <= 6) {
                        //random int between 1 and 10 (inclusive)
                        //defeat defend army
                        d_DefendCountry.setArmies(d_DefendCountry.getArmies() - 1);
                    }

                    //the defend army has a 70% chance to defeat the attack army
                    if ((l_randomNumber.nextInt(10) + 1) <= 7) {
                        //random int between 1 and 10 (inclusive)
                        //defeat attack army
                        d_AttackCountry.setArmies(d_AttackCountry.getArmies() - 1);
                        l_SurvivingArmies--;
                    }
                }
                d_NumberOfArmies = l_SurvivingArmies;

                if (d_DefendCountry.getArmies() == 0 && d_NumberOfArmies > 0) {
                    exchangeCountryOwner(d_DefendCountry, d_Player, d_NumberOfArmies);
                }
            }


            printOrder();
            return true;
        }
        return false;
    }

    /**
     * When attacker control defender's country,
     * this method performs the exchange of the countries and armies.
     *
     * @param p_TargetCountry  target country
     * @param p_NewOwner       new owner of the country
     * @param p_NumberOfArmies armies to move to the country
     */
    private void exchangeCountryOwner(Country p_TargetCountry, Player p_NewOwner, int p_NumberOfArmies) {
        // get previous player from country obj
        Player l_PreviousOwner = p_TargetCountry.getOwner();
        Map<String, Country> l_PreOwnerCountryList = l_PreviousOwner.getCountriesInControl();
        for (Map.Entry<String, Country> l_Entry : l_PreOwnerCountryList.entrySet()) {
            // check if country name in previous owner country list, then remove it.
            if (l_Entry.getKey().equalsIgnoreCase(p_TargetCountry.getName())) {
                l_PreOwnerCountryList.remove(l_Entry.getKey());
                break;
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

        // randomly give new owner a new card
        p_NewOwner.receiveNewCard(Card.getRandomCard());
    }

    /**
     * check validate
     *
     * @return true if the order is valid; false otherwise
     */
    public boolean valid() {
        boolean l_Valid = true;
        Player l_targetPlayer = d_DefendCountry.getOwner();
        if (l_targetPlayer == null || !l_targetPlayer.getPlayerExist()) {
            System.out.println("player of target country does not exist");
            l_Valid = false;
        }

        if (d_AttackCountry == null || d_DefendCountry == null) {
            System.out.println("Invalid country name.");
            l_Valid = false;
        }

        //check whether player is fromCountry owner
        if (!d_AttackCountry.getOwner().equals(d_Player)) {
            System.out.println("Could not perform the advance order moving " + d_NumberOfArmies + " armies from " +
                    d_AttackCountry.getName() + " to " + d_DefendCountry.getName() + " because " + d_Player.getColour() + " does not own " + d_AttackCountry + ".");
            l_Valid = false;
        }

        // if target country is un-attackable, because of diplomacy
        // only two countries with diplomacy cant attack each other
        if (d_Player.getUnattackablePlayers().contains(l_targetPlayer.getId())) {
            l_Valid = false;
        }


        // check if fromCountry and toCountry are neighbors
        if (!d_AttackCountry.getBorderCountries().containsKey(d_DefendCountry.getName().toLowerCase())) {
            System.out.println("Could not perform the advance order moving " + d_NumberOfArmies + " armies from " +
                    d_AttackCountry.getName() + " to " + d_DefendCountry.getName() + " because they are not neighbors.");
            l_Valid = false;
        }

        // check if the number of armies is larger than zero
        if (d_NumberOfArmies <= 0) {
            l_Valid = false;
        }

        return l_Valid;
    }

    /**
     * print the order
     */
    @Override
    public void printOrder() {
        System.out.println("Advance order issued by player " + d_Player.getColour());
        System.out.println("Advanced " + d_NumberOfArmies + " armies from " + d_AttackCountry.getName() + " to " + d_DefendCountry.getName());
    }
}
