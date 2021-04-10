package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.Order;

/**
 * The type Cheater pattern.
 */
public class CheaterPattern extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public CheaterPattern(Player p_Player, GameData p_GameData) {
        super(p_Player, p_GameData);
    }

    /**
     * Does not create actual orders.
     * Insteads, conquers all neighboring countries and doubles the armies.
     *
     * @return the order
     */
    @Override
    public Order createOrder() {
        conquerAllNeighborCountriesAndDoubleArmies();
        return null;
    }

    /**
     * Resets strategy states.
     */
    @Override
    public void reset() {

    }

    /**
     * Conquers all neighbor countries and doubles armies.
     */
    private void conquerAllNeighborCountriesAndDoubleArmies() {
        for (Country l_CountryInControl : getPlayer().getCountriesInControl().values()) {
            for (Country l_Neighbor : l_CountryInControl.getBorderCountries().values()) {
                if (l_Neighbor.getOwner().getId() != getPlayer().getId()) {
                    getPlayer().assignCountry(l_Neighbor);
                    l_Neighbor.setOwner(getPlayer());
                    l_Neighbor.setArmies(l_Neighbor.getArmies() * 2);
                }
            }
        }
    }
}
