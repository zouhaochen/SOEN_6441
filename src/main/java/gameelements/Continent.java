package gameelements;

import java.util.HashMap;
import java.util.Map;

public class Continent extends Territory{

	private int d_ContinentValue;
	private Map<String, Country> d_Countries;

	public Continent(String p_name, int p_ContinentValue) {
		super(p_name);
		d_ContinentValue = p_ContinentValue;
		d_Countries = new HashMap<>();
	}

	public int getContinentValue() {
		return d_ContinentValue;
	}

	public void setContinentValue(int p_ContinentValue) {
		this.d_ContinentValue = p_ContinentValue;
	}

	public Map<String, Country> getCountries() {
		return d_Countries;
	}

	public void setCountries(Map<String, Country> p_Countries) {
		this.d_Countries = p_Countries;
	}
}
