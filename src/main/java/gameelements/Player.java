package gameelements;

import command.CommandValidator;
import gameelements.order.Order;
import gameelements.order.OrderFactory;

import java.util.*;

/**
 * player object that reprsent in game.
 */
public class Player {

    /**
     * id counter (increment)
     */
    private static int d_count = 0;

    /**
     * player id
     */
    private int d_id;
    /**
     * player name /color
     */
    private String d_colour;
    /**
     * hash map used to represent countries owned by player
     */
    private Map<String, Country> d_countriesInControl;
    /**
     * current player order list
     */
    private Deque<Order> d_ordersInCurrentTurn;
    /**
     * number of reinforcement armies
     */
    private int d_reinforcementArmies;
    /**
     * command validator.
     */
    private CommandValidator d_commandValidator;

    /**
     * Player class constructor
     *
     * @param p_colour           get player name as string type.
     * @param p_commandValidator add new commandValidator object
     */
    public Player(String p_colour, CommandValidator p_commandValidator) {
        d_id = ++d_count;
        d_colour = p_colour;
        d_countriesInControl = new HashMap<>();
        d_ordersInCurrentTurn = new ArrayDeque<>();
        d_commandValidator = p_commandValidator;
    }

    /**
     * get number of player
     *
     * @return return the number of player as int
     */
    public static int getCount() {
        return d_count;
    }

    /**
     * get the value of player ID
     *
     * @return return the player ID as int.
     */
    public int getId() {
        return d_id;
    }

    /**
     * set player name.
     *
     * @return return the value of player name.
     */
    public String getColour() {
        return d_colour;
    }

    /**
     * set player name.
     *
     * @param p_colour set the player name as data withstring type
     */
    public void setColour(String p_colour) {
        d_colour = p_colour;
    }

    /**
     * return  which player get control authority of the country
     *
     * @return return the data mebmer value of countriesinControl.
     */
    public Map<String, Country> getCountriesInControl() {
        return d_countriesInControl;
    }

    /**
     * to set countries for each player in control
     *
     * @param p_countryList add countrylist, and map the countries name to country object
     */
    public void setCountriesInControl(Map<String, Country> p_countryList) {
        d_countriesInControl = p_countryList;
    }

    /**
     * to assign country for each player
     *
     * @param p_country add a new country object into function
     */
    public void assignCountry(Country p_country) {
        d_countriesInControl.put(p_country.getName().toLowerCase(), p_country);
    }

    /**
     * to get current turning orders for each player
     *
     * @return return the value of order in current turn
     */
    public Deque<Order> getOrdersInCurrentTurn() {
        return d_ordersInCurrentTurn;
    }

    /**
     * set current turning orders for each player
     *
     * @param p_orders add new orderlist object.
     */
    public void setOrdersInCurrentTurn(Deque<Order> p_orders) {
        d_ordersInCurrentTurn = p_orders;
    }

    /**
     * add order to order list
     *
     * @param p_order add new order object to order list
     */
    public void addOrderToList(Order p_order) {
        d_ordersInCurrentTurn.add(p_order);
    }

    /**
     * return reinforcement armies for each player
     *
     * @return return the value of d_reinforcementArmies, how many reinforcement Armies in this round for a player.
     */
    public int getReinforcementArmies() {
        return d_reinforcementArmies;
    }

    /**
     * set how many Reinforcement Armies that each player have.
     *
     * @param p_reinforcementArmies add number of Reinforcement Armies as int, to set value in the function.
     */
    public void setReinforcementArmies(int p_reinforcementArmies) {
        d_reinforcementArmies = p_reinforcementArmies;
    }

    /**
     * following the command to issue order and add the order to order list.
     */
    public void issueOrder() {
        // read the command from a player
        String l_command;
        do {
            System.out.println("\nPlease enter the command: \n");
            Scanner l_scanner = new Scanner(System.in);
            l_command = l_scanner.nextLine();
        } while (!d_commandValidator.validate(l_command));

        // create an order
        String[] l_command_arr = l_command.split(" ");
        Order l_order = OrderFactory.CreateOrder(l_command_arr, this);
        if (l_order != null) {
            addOrderToList(l_order);
        }
    }

    /**
     * return the last order in orderlist.
     *
     * @return return the value of peek element in queue.
     */
    public Order getLastOrderFromQueue() {
        return d_ordersInCurrentTurn.peekLast();
    }

    /**
     * To execute the first order as order list.
     *
     * @return poll the value of first element in queue.
     */
    public Order nextOrder() {
        return d_ordersInCurrentTurn.poll();
    }

    /**
     * Subtract the armies from the reinforcement pool.
     *
     * @param p_armyNumber add number of armies as int.
     * @return return true if the number of the remaining armies is not less than the number to deploy,
     * and otherwise return false.
     */
    public boolean deployReinforcementArmies(int p_armyNumber) {
        if (p_armyNumber > d_reinforcementArmies) {
            d_reinforcementArmies = 0;
            return false;
        }
        d_reinforcementArmies -= p_armyNumber;
        return true;
    }
}
