package gameelements;

import java.util.*;

public class Player {

	private static int d_count = 0;

	private int d_id;
	private String d_colour;
	private Map<String, Country> d_countries_in_control;
	private Deque<Order> d_orders_in_current_turn;
	private int d_reinforcement_armies;

	public Player(String p_colour) {
		d_id = ++d_count;
		d_colour = p_colour;
		d_countries_in_control = new HashMap<>();
		d_orders_in_current_turn = new ArrayDeque<>();
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
		return d_countries_in_control;
	}

	public void setCountriesInControl(Map<String, Country> p_country_list) {
		d_countries_in_control = p_country_list;
	}

	public void assignCountry(Country p_country) {
		d_countries_in_control.put(p_country.getName(), p_country);
	}

	public Deque<Order> getOrdersInCurrentTurn() {
		return d_orders_in_current_turn;
	}

	public void setOrdersInCurrentTurn(Deque<Order> p_orders) {
		d_orders_in_current_turn = p_orders;
	}

	public void addOrderToList(Order p_order) {
		d_orders_in_current_turn.add(p_order);
	}

	public int getReinforcementArmies() {
		return d_reinforcement_armies;
	}

	public void setReinforcementArmies(int p_reinforcement_arimies) {
		d_reinforcement_armies = p_reinforcement_arimies;
	}

	public void issueOrder() {
		addOrderToList(new Order());
	}

	public Order nextOrder() {
		return d_orders_in_current_turn.poll();
	}

	public boolean deployReinforcementArmies(int p_army_number) {
		if (p_army_number > d_reinforcement_armies) {
			return false;
		}

		d_reinforcement_armies -= p_army_number;
		return true;
	}
}
