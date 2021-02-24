package gameelements;

import java.util.HashMap;
import java.util.Map;

/**
 * The class to represent a country element in the game.
 */
public class Country extends Territory{

	private int d_armies;
	private int d_CountryId;
	private Player d_Owner;
	private Map<String, Country> d_BorderCountries;

	public Country(String p_name) {
		super(p_name);
		d_BorderCountries = new HashMap<>();
	}

	public int getCountryId(){return d_CountryId;}

	public void setCountryId(int p_Id){this.d_CountryId=p_Id;}

	public int getArmies() {
		return d_armies;
	}

	public void setArmies(int p_armies) {
		d_armies = p_armies;
	}

	public void deployArmies(int p_armies) {
		d_armies += p_armies;
	}

	public Player getOwner() {
		return d_Owner;
	}

	public void setOwner(Player p_Owner) {
		this.d_Owner = p_Owner;
	}

	public Map<String, Country> getBorderCountries() {
		return d_BorderCountries;
	}

	public void setBorderCountries(Map<String, Country> p_BorderCountries) {
		this.d_BorderCountries = p_BorderCountries;
	}
}
