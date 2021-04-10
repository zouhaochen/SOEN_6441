package model.gameelements;

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
     * The D committed armies.
     */
    private int d_CommittedArmies;

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
     * @param p_Armies new Armies number
     */
    public void setArmies(int p_Armies) {
        d_Armies = p_Armies;
    }

    /**
     * deploy armies on this country
     *
     * @param p_Armies armies you want to deploy on current country
     */
    public void deployArmies(int p_Armies) {
        d_Armies += p_Armies;
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
     * returns model.map of current country border.
     *
     * @return a model.map containing the countries that belong to the current continent
     */
    public Map<String, Country> getBorderCountries() {
        return d_BorderCountries;
    }

    /**
     * add Country border to the other country
     *
     * @param p_BorderCountries current country new connectivity model.map
     */
    public void setBorderCountries(Map<String, Country> p_BorderCountries) {
        this.d_BorderCountries = p_BorderCountries;
    }

    /**
     * Gets committed armies.
     *
     * @return the committed armies
     */
    public int getCommittedArmies() {
        return d_CommittedArmies;
    }

    /**
     * Sets committed armies.
     *
     * @param p_CommittedArmies the committed armies
     */
    public void setCommittedArmies(int p_CommittedArmies) {
        this.d_CommittedArmies = p_CommittedArmies;
    }

    /**
     * add the 2 countries boarder connectivity
     *
     * @param p_Country the neighbor country
     * @return true if successfully added, otherwise return false
     */
    public boolean addBoarderConnection(Country p_Country) {
        if (p_Country != null) {
            d_BorderCountries.put(p_Country.getName(), p_Country);
            return true;
        } else
            return false;
    }
}
