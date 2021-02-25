package gameelements;

import java.util.HashMap;
import java.util.Map;

/**
 * The class to represent a country element in the game.
 */
public class Country extends Territory {

    /**
     * the army number now in this country
     */
    private int d_Armies;
    /**
     * the country id
     */
    private int d_CountryId;
    /**
     * the owner for current country
     */
    private Player d_Owner;
    /**
     * Hash Map for all connectivity for current country
     */
    private Map<String, Country> d_BorderCountries;

    /**
     * Country Object Constructor
     *
     * @param p_Name new Country name for constructor
     */
    public Country(String p_Name) {
        super(p_Name);
        d_BorderCountries = new HashMap<>();
    }

    /**
     * current country id getter
     *
     * @return current country id
     */
    public int getCountryId() {
        return d_CountryId;
    }

    /**
     * current country id setter
     *
     * @param p_Id new country id
     */
    public void setCountryId(int p_Id) {
        this.d_CountryId = p_Id;
    }

    /**
     * current country army number getter
     *
     * @return current country army number
     */
    public int getArmies() {
        return d_Armies;
    }

    /**
     * current country Army number setter
     *
     * @param p_armies new Armies number
     */
    public void setArmies(int p_armies) {
        d_Armies = p_armies;
    }

    /**
     * deploy armies on this country
     *
     * @param p_armies armies you want to deploy on current country
     */
    public void deployArmies(int p_armies) {
        d_Armies += p_armies;
    }

    /**
     * country owner getter
     *
     * @return current country owner
     */
    public Player getOwner() {
        return d_Owner;
    }

    /**
     * current country owner setter
     *
     * @param p_Owner current country owner
     */
    public void setOwner(Player p_Owner) {
        this.d_Owner = p_Owner;
    }

    /**
     * returns map of current country border.
     *
     * @return a map containing the countries that belong to the current continent
     */
    public Map<String, Country> getBorderCountries() {
        return d_BorderCountries;
    }

    /**
     * add Country border to the other country
     *
     * @param p_BorderCountries current country new connectivity map
     */
    public void setBorderCountries(Map<String, Country> p_BorderCountries) {
        this.d_BorderCountries = p_BorderCountries;
    }
}
