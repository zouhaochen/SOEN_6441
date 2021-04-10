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

    /**
     * the Country that being Attacker.
     */
    private Country d_AttackCountry;
    /**
     * the Country that being defender.
     */
    private Country d_DefendCountry;
    /**
     * for how many armies are going to set.
     */
    private int d_NumberOfArmies;
    /**
     * the country ownership is belong to which player.
     */
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
        d_NumberOfArmies = p_NumberOfArmies;

        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Player);
        l_OrderInfo.setDeparture(p_AttackCountry);
        l_OrderInfo.setDestination(p_DefenceCountry);
        l_OrderInfo.setNumberOfArmy(p_NumberOfArmies);
        setOrderInfo(l_OrderInfo);

        d_AttackCountry.setCommittedArmies(d_AttackCountry.getCommittedArmies() + p_NumberOfArmies);
    }

    /**
     * Instantiates a new Advance order.
     *
     * @param p_OrderInfo the order info
     */
    public AdvanceOrder(OrderInfo p_OrderInfo) {
        super();
        setType("Advance");
        d_Player = p_OrderInfo.getInitiator();
        d_AttackCountry = p_OrderInfo.getDeparture();
        d_DefendCountry = p_OrderInfo.getDestination();
        d_NumberOfArmies = p_OrderInfo.getNumberOfArmy();
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

            // Check army number to advance
            if (d_AttackCountry.getArmies() < d_NumberOfArmies) {
                d_NumberOfArmies = d_AttackCountry.getArmies();
            }

            // If defend country is owned by the player then advance armies
            if (d_DefendCountry.getOwner().getId() == d_Player.getId()) {
                // Then move armies from attack country to defend country
                d_AttackCountry.setArmies(d_AttackCountry.getArmies() - d_NumberOfArmies);
                d_DefendCountry.setArmies(d_DefendCountry.getArmies() + d_NumberOfArmies);
            }

            // Else defend country is owned by opponent
            // Then attack
            else {
                // Set a random number
                Random l_RandomNumber = new Random();
                int l_OriginalAttackingArmies = d_NumberOfArmies;
                int l_AttackArmyDestroy = 0;
                int l_DefendArmyDestroy = 0;

                // Attack defend country
                for (int i = 0; i < d_NumberOfArmies; i++) {
                    // There are 10 numbers from 1 to 10
                    // Randomly pick one number from 1 to 10
                    // The probability that the number equal or less than 6 is 60%
                    if ((l_RandomNumber.nextInt(10) + 1) <= 6) {
                        // Every army from attack country has 60%
                        // Of destroy one of the defend army
                        l_DefendArmyDestroy++;
                    }
                }

                // Defend attack country
                for (int i = 0; i < d_DefendCountry.getArmies(); i++) {
                    // There are 10 numbers from 1 to 10
                    // Randomly pick one number from 1 to 10
                    // The probability that the number equal or less than 7 is 70%
                    if ((l_RandomNumber.nextInt(10) + 1) <= 7) {
                        // Every army from defend country has 70%
                        // Of destroy one of the attack army
                        l_AttackArmyDestroy++;
                    }
                }

                // Calculate the loss of both sides
                d_DefendCountry.setArmies(Math.max(0, d_DefendCountry.getArmies() - l_DefendArmyDestroy));
                d_NumberOfArmies = Math.max(0, d_NumberOfArmies - l_AttackArmyDestroy);

                // Exchange ownership between attack country and defend country
                if (d_DefendCountry.getArmies() == 0 && d_NumberOfArmies > 0) {
                    exchangeCountryOwner(d_DefendCountry, d_Player, d_NumberOfArmies);
                    d_AttackCountry.setArmies(d_AttackCountry.getArmies() - l_OriginalAttackingArmies);
                } else if (d_NumberOfArmies > 0) {
                    d_AttackCountry.setArmies(d_AttackCountry.getArmies() - l_AttackArmyDestroy);
                } else if (d_NumberOfArmies == 0) {
                    d_AttackCountry.setArmies(d_AttackCountry.getArmies() - l_OriginalAttackingArmies);
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
        // Get previous player from country obj
        Player l_PreviousOwner = p_TargetCountry.getOwner();
        Map<String, Country> l_PreOwnerCountryList = l_PreviousOwner.getCountriesInControl();
        for (Map.Entry<String, Country> l_Entry : l_PreOwnerCountryList.entrySet()) {
            // Check if country name in previous owner country list, then remove it.
            if (l_Entry.getKey().equalsIgnoreCase(p_TargetCountry.getName())) {
                l_PreOwnerCountryList.remove(l_Entry.getKey());
                break;
            }
        }
        // Set previous owner his new country list
        l_PreviousOwner.setCountriesInControl(l_PreOwnerCountryList);
        // Now change the target country's owner
        p_TargetCountry.setOwner(p_NewOwner);
        // Assign the number of armies
        p_TargetCountry.setArmies(p_NumberOfArmies);
        // Add target country to new owner's country list
        p_NewOwner.assignCountry(p_TargetCountry);

        System.out.println("Player " + d_Player.getColour() + " has conquered Country " + p_TargetCountry.getName());

        // Randomly give new owner a new card
        p_NewOwner.receiveNewCard(Card.getRandomCard());
    }

    /**
     * check validate
     *
     * @return true if the order is valid; false otherwise
     */
    public boolean valid() {

        // Check whether attack and defend country is null
        if (d_AttackCountry == null || d_DefendCountry == null) {
            System.out.println("Invalid Advance Order: invalid country name.");
            return false;
        }

        // Check whether play of defend country exist
        Player l_targetPlayer = d_DefendCountry.getOwner();
        if (l_targetPlayer == null || !l_targetPlayer.getPlayerExist()) {
            System.out.println("Invalid Advance Order: player of target country does not exist");
            return false;
        }

        // Check whether player is owner of attack country
        if (!d_AttackCountry.getOwner().equals(d_Player)) {
            System.out.println("Invalid Advance Order: can not do the advance order moving " + d_NumberOfArmies + " armies from " +
                    d_AttackCountry.getName() + " to " + d_DefendCountry.getName() + " because " + d_Player.getColour() + " does not own " + d_AttackCountry + ".");
            return false;
        }

        // If target country is un-attackable, because of diplomacy
        // Only two countries with diplomacy cant attack each other
        if (d_Player.getUnattackablePlayers().contains(l_targetPlayer.getId())) {
            System.out.println("Invalid Advance Order: you cannot attack Player " + l_targetPlayer.getColour() + " due to a Diplomacy card has been used by the player.");
            return false;
        }


        // Check if attack country and defend country are neighbors
        if (!d_AttackCountry.getBorderCountries().containsKey(d_DefendCountry.getName())) {
            System.out.println("Invalid Advance Order: can not do the advance order moving " + d_NumberOfArmies + " armies from " +
                    d_AttackCountry.getName() + " to " + d_DefendCountry.getName() + " because they are not neighbors.");
            return false;
        }

        // Check if the number of armies is larger than zero
        if (d_NumberOfArmies <= 0) {
            System.out.println("Invalid Advance Order: the number of armies is not positive.");
            return false;
        }

        return true;
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
