package gameelements;

/**
 * The class to represent a country element in the game.
 */
public class Country extends Territory{

	private int d_armies;

	public Country(String p_name) {
		super(p_name);
	}

	public int getArmies() {
		return d_armies;
	}

	public void setArmies(int p_armies) {
		d_armies = p_armies;
	}

	public void deployArmies(int p_armies) {
		d_armies += p_armies;
	}
}
