package gameelements;

public class Continent extends Territory{


	private int d_continentValue;

	public Continent(String p_name, int p_continentValue) {
		super(p_name);
		d_continentValue = p_continentValue;
	}

	public int getContinentValue() {
		return d_continentValue;
	}

	public void setContinentValue(int d_continentValue) {
		this.d_continentValue = d_continentValue;
	}




}
