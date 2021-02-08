package gameelements;

import java.util.*;

public class Player {

	private static int d_count = 0;

	private int d_id;
	private String d_colour;
	private Map<String, Country> d_countriesInControl;
	private Deque<Order> d_ordersInCurrentTurn;
	private int d_reinforcementArmies;

	public Player(String p_colour) {
		d_id = ++d_count;
		d_colour = p_colour;
		d_countriesInControl = new HashMap<>();
		d_ordersInCurrentTurn = new ArrayDeque<>();
	}

	public static int getCount() {
		return d_count;
	}

	public int getId() {
		return d_id;
	}

	public String getColour() {
		return d_colour;
	}

	public void setColour(String p_colour) {
		d_colour = p_colour;
	}

	public Map<String, Country> getCountriesInControl() {
		return d_countriesInControl;
	}

	public void setCountriesInControl(Map<String, Country> p_countryList) {
		d_countriesInControl = p_countryList;
	}

	public void assignCountry(Country p_country) {
		d_countriesInControl.put(p_country.getName(), p_country);
	}

	public Deque<Order> getOrdersInCurrentTurn() {
		return d_ordersInCurrentTurn;
	}

	public void setOrdersInCurrentTurn(Deque<Order> p_orders) {
		d_ordersInCurrentTurn = p_orders;
	}

	public void addOrderToList(Order p_order) {
		d_ordersInCurrentTurn.add(p_order);
	}

	public int getReinforcementArmies() {
		return d_reinforcementArmies;
	}

	public void setReinforcementArmies(int p_reinforcementArmies) {
		d_reinforcementArmies = p_reinforcementArmies;
	}

	public void issueOrder() {
		addOrderToList(new Order());
	}

	public Order nextOrder() {
		return d_ordersInCurrentTurn.poll();
	}

	public boolean deployReinforcementArmies(int p_armyNumber) {
		if (p_armyNumber > d_reinforcementArmies) {
			return false;
		}

		d_reinforcementArmies -= p_armyNumber;
		return true;
	}
}
