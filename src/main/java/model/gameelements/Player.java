package model.gameelements;

import command.CommandValidator;
import model.gameelements.order.Order;
import model.gameelements.order.OrderFactory;

import java.util.*;

/**
 * player object that reprsent in game.
 */
public class Player {

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
     * command validator.
     */
    private CommandValidator d_CommandValidator;
    /**
     * check player obj whether existed
     */
    private boolean d_playerExist = true;

    /**
     * user's card list
     */
    private List<Card> d_Cards;


    /**
     * the player can be attacked
     * it is int array, [x,y] x stand for current player who want diplomacy, y is target player
     */
    private int[] d_DiplomacyFlag;

    /**
     * Player class constructor
     *
     * @param p_Colour           get player name as string type.
     * @param p_CommandValidator add new commandValidator object
     */
    public Player(String p_Colour, CommandValidator p_CommandValidator) {
        d_Id = ++D_COUNT;
        d_Colour = p_Colour;
        d_CountriesInControl = new HashMap<>();
        d_OrdersInCurrentTurn = new ArrayDeque<>();
        d_CommandValidator = p_CommandValidator;
        d_DiplomacyFlag = new int[2];
        d_DiplomacyFlag[0]=d_Id;
    }

    /**
     * Attack flag setter
     *
     * @param p_TargetDiplomaPlayer to set flag
     */
    public void setPlayerDiplomacy(Player p_TargetDiplomaPlayer) {
        this.d_DiplomacyFlag[1]=p_TargetDiplomaPlayer.getId();
    }

    /**
     * reset the Diaplomacy
     */
    public void resetPlayerDiplomacy(){
        int[] l_Arr=new int[2];
        l_Arr[0]=this.d_Id;
        this.d_DiplomacyFlag =l_Arr;
    }

    /**
     * Attackable Flag getter
     *
     * @return boolean flag
     */
    public int[] getPlayerDiplomacy() {
        return d_DiplomacyFlag;
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
     * get player name.
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
     * following the command to issue order and add the order to order list.
     */
    public void issueOrder() {
        // read the command from a player
        String l_Command;
        do {
            System.out.println("\nPlease enter the command: \n");
            Scanner l_Scanner = new Scanner(System.in);
            l_Command = l_Scanner.nextLine();
        } while (!d_CommandValidator.validate(l_Command));

        // create an order
        String[] l_CommandArr = l_Command.split(" ");
        Order l_Order = OrderFactory.CreateOrder(l_CommandArr, this);
        if (l_Order != null) {
            addOrderToList(l_Order);
        }
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
     * @return return true if the number of the remaining armies is not less than the number to deploy,
     * and otherwise return false.
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

}
