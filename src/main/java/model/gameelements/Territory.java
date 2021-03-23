package model.gameelements;


import model.Observable;

/**
 * This is a class to represent the concept of an area on a model.map.
 * This serves as a parent class of Continent and Country.
 */
public class Territory {

    /**
     * Territory name
     */
    private String d_Name;

    /**
     * Instantiates a new Territory.
     *
     * @param p_Name the name of the territory
     */
    public Territory(String p_Name) {
        d_Name = p_Name;
    }

    /**
     * Gets name.
     *
     * @return the name of the territory
     */
    public String getName() {
        return d_Name;
    }

    /**
     * Sets name.
     *
     * @param p_Name the name of the territory
     */
    public void setName(String p_Name) {
        d_Name = p_Name;
    }
}
