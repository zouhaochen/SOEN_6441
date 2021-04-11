package model.gameelements;

import model.Observable;
import model.gameelements.order.Order;
import model.gameelements.strategy.PlayerStrategy;

import java.util.*;

/**
 * player object that represents a player in game.
 */
public class

Player extends Observable {

    /**
     * id counter (increment)
     */
    private static int D_COUNT = 0;

    /**
     * player id
     */
    private int d_Id;
    /**
     * player name /color
     */
    private String d_Colour;
    /**
     * hash model.map used to represent countries owned by player
     */
    private Map<String, Country> d_CountriesInControl;
    /**
     * current player order list
     */
    private Deque<Order> d_OrdersInCurrentTurn;
    /**
     * number of reinforcement armies
     */
    private int d_ReinforcementArmies;
    /**
     * check player obj whether existed
     */
    private boolean d_playerExist;
    /**
     * user's card list
     */
    private List<Card> d_Cards;
    /**
     * the ids of the unattackable players
     */
    private List<Integer> d_UnattackablePlayers;
    /**
     * The player strategy.
     */
    private PlayerStrategy d_Strategy;
    /**
     * The cards committed for orders.
     */
    private List<Card> d_CommittedCards;
    /**
     * The number of armies committed for Deploy orders.
     */
    private int d_CommittedReinforcement;

    /**
     * Player class constructor
     *
     * @param p_Colour get player name as string type.
     */
    public Player(String p_Colour) {
        d_Id = ++D_COUNT;
        d_Colour = p_Colour;
        d_CountriesInControl = new HashMap<>();
        d_OrdersInCurrentTurn = new ArrayDeque<>();
        d_UnattackablePlayers = new ArrayList<>();
        d_Cards = new ArrayList<>();
        d_playerExist = true;
        d_CommittedCards = new ArrayList<>();
    }

    /**
     * current strategy name getter
     * @return strategy name string
     */
    public String getPlayerStrategyName(){
        return this.d_Strategy.getClass().getSimpleName();
    }

    /**
     * Reset the unattackable player list
     */
    public void resetPlayerDiplomacy() {
        this.d_UnattackablePlayers.clear();
    }

    /**
     * Attackable Flag getter
     *
     * @return boolean flag
     */
    public List<Integer> getUnattackablePlayers() {
        return d_UnattackablePlayers;
    }

    /**
     * Set unattackable players list
     *
     * @param p_PlayerIds the list of IDs
     */
    public void setUnattackablePlayers(List<Integer> p_PlayerIds) {
        this.d_UnattackablePlayers = p_PlayerIds;
    }

    /**
     * get number of player
     *
     * @return return the number of player as int
     */
    public static int getCount() {
        return D_COUNT;
    }

    /**
     * get the value of player ID
     *
     * @return return the player ID as int.
     */
    public int getId() {
        return d_Id;
    }

    /**
     * set player name.
     *
     * @return return the value of player name.
     */
    public String getColour() {
        return d_Colour;
    }

    /**
     * set player name.
     *
     * @param p_Colour set the player name as data withstring type
     */
    public void setColour(String p_Colour) {
        d_Colour = p_Colour;
    }

    /**
     * return  which player get control authority of the country
     *
     * @return return the data mebmer value of countriesinControl.
     */
    public Map<String, Country> getCountriesInControl() {
        return d_CountriesInControl;
    }

    /**
     * to set countries for each player in control
     *
     * @param p_CountryList add countrylist, and model.map the countries name to country object
     */
    public void setCountriesInControl(Map<String, Country> p_CountryList) {
        d_CountriesInControl = p_CountryList;
    }

    /**
     * to assign country for each player
     *
     * @param p_Country add a new country object into function
     */
    public void assignCountry(Country p_Country) {
        d_CountriesInControl.put(p_Country.getName().toLowerCase(), p_Country);
    }

    /**
     * to get current turning orders for each player
     *
     * @return return the value of order in current turn
     */
    public Deque<Order> getOrdersInCurrentTurn() {
        return d_OrdersInCurrentTurn;
    }

    /**
     * set current turning orders for each player
     *
     * @param p_Orders add new orderlist object.
     */
    public void setOrdersInCurrentTurn(Deque<Order> p_Orders) {
        d_OrdersInCurrentTurn = p_Orders;
    }

    /**
     * add order to order list
     *
     * @param p_order add new order object to order list
     */
    public void addOrderToList(Order p_order) {
        d_OrdersInCurrentTurn.add(p_order);
    }

    /**
     * return reinforcement armies for each player
     *
     * @return return the value of d_reinforcementArmies, how many reinforcement Armies in this round for a player.
     */
    public int getReinforcementArmies() {
        return d_ReinforcementArmies;
    }

    /**
     * set how many Reinforcement Armies that each player have.
     *
     * @param p_ReinforcementArmies add number of Reinforcement Armies as int, to set value in the function.
     */
    public void setReinforcementArmies(int p_ReinforcementArmies) {
        d_ReinforcementArmies = p_ReinforcementArmies;
    }

    /**
     * Sets strategy.
     *
     * @param p_Strategy the strategy
     */
    public void setStrategy(PlayerStrategy p_Strategy) {
        this.d_Strategy = p_Strategy;
        System.out.println("Player ["+d_Colour+"] your strategy pattern now is "+getPlayerStrategyName().toUpperCase());
    }

    /**
     * Gets the committed cards.
     *
     * @return the list of committed cards
     */
    public List<Card> getCommittedCards() {
        return d_CommittedCards;
    }

    /**
     * Gets committed reinforcement armies.
     *
     * @return the number of committed reinforcement armies
     */
    public int getCommittedReinforcement() {
        return d_CommittedReinforcement;
    }

    /**
     * Sets deployed armies buffer.
     *
     * @param p_Armies the p deployed armies buffer
     */
    public void setCommittedReinforcement(int p_Armies) {
        this.d_CommittedReinforcement = p_Armies;
    }

    /**
     * Add card to buffer.
     *
     * @param p_Card the p card
     */
    public void addCardToCommittedList(Card p_Card) {
        d_CommittedCards.add(p_Card);
    }

    /**
     * following the command to issue order and add the order to order list.
     *
     * @return true if an order is created
     */
    public boolean issueOrder() {
        Order l_Order = this.d_Strategy.createOrder();
        if (l_Order != null) {
            addOrderToList(l_Order);
            System.out.println(d_Colour+" has issue order ["+l_Order.getType()+"]");
            return true;
        }

        return false;
    }

    /**
     * return the last order in orderlist.
     *
     * @return return the value of peek element in queue.
     */
    public Order getLastOrderFromQueue() {
        return d_OrdersInCurrentTurn.peekLast();
    }

    /**
     * To execute the first order as order list.
     *
     * @return poll the value of first element in queue.
     */
    public Order nextOrder() {
        return d_OrdersInCurrentTurn.poll();
    }

    /**
     * Subtract the armies from the reinforcement pool.
     *
     * @param p_ArmyNumber add number of armies as int.
     * @return return true if the number of the remaining armies is not less than the number to deploy, and otherwise return false.
     */
    public boolean deployReinforcementArmies(int p_ArmyNumber) {
        if (p_ArmyNumber > d_ReinforcementArmies) {
            d_ReinforcementArmies = 0;
            return false;
        }
        d_ReinforcementArmies -= p_ArmyNumber;
        return true;
    }

    /**
     * Get whether a play still exist in the game
     *
     * @return true if the player still has at least one territory.
     */
    public boolean getPlayerExist() {
        // if player has no country
        if (this.d_CountriesInControl.isEmpty()) {
            d_playerExist = false;
        }

        return d_playerExist;
    }

    /**
     * Cards the player has available to play
     *
     * @return list of cards
     */
    public List<Card> getCards() {
        return d_Cards;
    }

    /**
     * player receive a New Card
     *
     * @param p_NewCard send a new card for a player
     */
    public void receiveNewCard(Card p_NewCard) {
        this.d_Cards.add(p_NewCard);
        System.out.println("Notice: Player " + this.d_Colour + " receive new card [" + p_NewCard.name() + "]");
    }

    /**
     * Card being used so remove from card list
     *
     * @param p_UsedCard the used card
     */
    public void removeTargetCard(Card p_UsedCard) {
        d_Cards.remove(p_UsedCard);
        System.out.println("Notice: Player " + this.d_Colour + " consumes card [" + p_UsedCard.name() + "]");
    }

    /**
     * Resets strategy state.
     */
    public void resetStrategyState() {
        d_Strategy.reset();
    }

}
