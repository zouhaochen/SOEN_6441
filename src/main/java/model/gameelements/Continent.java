package model.gameelements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * the continent object that represent in game
 */
public class Continent extends Territory implements Serializable {

    /**
     * the Continent value
     */
    private int d_ContinentValue;
    /**
     * the countries that belong to this continent
     */
    private Map<String, Country> d_Countries;

    /**
     * Continent Object Constructor
     *
     * @param p_Name           continent name you need to give
     * @param p_ContinentValue continent value you need to set
     */
    public Continent(String p_Name, int p_ContinentValue) {
        super(p_Name);
        d_ContinentValue = p_ContinentValue;
        d_Countries = new HashMap<>();
    }

    /**
     * continent value getter
     *
     * @return current continent value
     */
    public int getContinentValue() {
        return d_ContinentValue;
    }

    /**
     * continent value setter
     *
     * @param p_ContinentValue new continent value
     */
    public void setContinentValue(int p_ContinentValue) {
        this.d_ContinentValue = p_ContinentValue;
    }

    /**
     * Countries belong to continent getter
     *
     * @return Hash Map that Countries belong to continent
     */
    public Map<String, Country> getCountries() {
        return d_Countries;
    }

    /**
     * Countries belong to continent setter
     *
     * @param p_Countries HashMap for Countries that belongs to this continent
     */
    public void setCountries(Map<String, Country> p_Countries) {
        this.d_Countries = p_Countries;
    }
}
