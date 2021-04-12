package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.Order;

import java.util.ArrayList;
import java.util.List;

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
        List<Country> l_CountriesToConquer = new ArrayList<>();
        for (Country l_CountryInControl : getPlayer().getCountriesInControl().values()) {
            for (Country l_Neighbor : l_CountryInControl.getBorderCountries().values()) {
                if (l_Neighbor.getOwner().getId() != getPlayer().getId()) {
                    l_Neighbor.getOwner().getCountriesInControl().remove(l_Neighbor.getName().toLowerCase());
                    l_Neighbor.setArmies(l_Neighbor.getArmies() * 2);
                    l_CountriesToConquer.add(l_Neighbor);
                }
            }
        }

        if (!l_CountriesToConquer.isEmpty()) {
            for (Country l_Country : l_CountriesToConquer) {
                getPlayer().assignCountry(l_Country);
                l_Country.setOwner(getPlayer());
            }
        }
    }
}
