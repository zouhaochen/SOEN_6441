package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.AdvanceOrder;
import model.gameelements.order.DeployOrder;
import model.gameelements.order.Order;

/**
 * The type Benevolent pattern.
 */
public class BenevolentPattern extends PlayerStrategy {

    /**
     * The count of move order issued.
     */
    private int d_AdvanceOrderCount;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public BenevolentPattern(Player p_Player, GameData p_GameData) {
        super(p_Player, p_GameData);
    }

    /**
     * To defend country.
     *
     * @return the country
     */
    @Override
    protected Country toDefend() {
        if (getPlayer().getCountriesInControl().size() == 0) {
            return null;
        }

        Country l_CountryWithLeastArmies = null;
        for (Country l_Country : getPlayer().getCountriesInControl().values()) {
            if (l_CountryWithLeastArmies == null
                    || (l_Country.getArmies() - l_Country.getCommittedArmies())
                    < (l_CountryWithLeastArmies.getArmies() - l_CountryWithLeastArmies.getCommittedArmies())) {
                l_CountryWithLeastArmies = l_Country;
            }
        }

        return l_CountryWithLeastArmies;
    }

    /**
     * Create order order.
     *
     * @return the order
     */
    @Override
    public Order createOrder() {
        int l_AvailableReinforcement = getPlayer().getReinforcementArmies() - getPlayer().getCommittedReinforcement();

        if (l_AvailableReinforcement > 0) {
            // option 1: reinforce the weakest country
            return new DeployOrder(getPlayer(), toDefend(), l_AvailableReinforcement);
        } else if (getPlayer().getCards().size() != 0) {
            // option 2: use card if the player has any
            return createRandomCardOrder();
        } else {
            // option 3: move armies to reinforce weaker countries
            // limit the number of Advance orders that Benevolent play issues
            if (d_AdvanceOrderCount >= getPlayer().getCountriesInControl().size() * 2) {
                return null;
            }

            Country l_MoveFrom = moveFrom();

            if (l_MoveFrom == null) {
                return null;
            }

            Country l_MoveTo = getRandomNeighborOfCountry(l_MoveFrom);
            if (l_MoveTo == null) {
                return null;
            }

            int l_CompareResult = compareArmies(l_MoveFrom, l_MoveTo);
            if (l_CompareResult == 1)  {
                d_AdvanceOrderCount++;
                return new AdvanceOrder(getPlayer(), l_MoveFrom, l_MoveTo, calculateArmiesToMove(l_MoveFrom, l_MoveTo));
            } else {
                return null;
            }
        }
    }

    /**
     * Reset strategy states.
     */
    @Override
    public void reset() {
        d_AdvanceOrderCount = 0;
    }

    /**
     * Compare armies int.
     *
     * @param p_CountryA the p country a
     * @param p_CountryB the p country b
     * @return -1 if the first parameter has less armies; 1 if the first parameter has more armies; 0 if they have the
     * the same armies
     */
    private int compareArmies(Country p_CountryA, Country p_CountryB) {
        int l_AvailableArmiesFromA = p_CountryA.getArmies() - p_CountryA.getCommittedArmies();
        int l_AvailableArmiesFromB = p_CountryB.getArmies() - p_CountryB.getCommittedArmies();

        if (l_AvailableArmiesFromA < l_AvailableArmiesFromB) {
            return -1;
        } else if (l_AvailableArmiesFromA > l_AvailableArmiesFromB) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Calculates armies to be moved in an Advance order.
     *
     * @param p_MoveFrom the move-from country
     * @param p_MoveTo   the move-to country
     * @return the number of armies
     */
    private int calculateArmiesToMove(Country p_MoveFrom, Country p_MoveTo) {
        int l_ArmiesOfMoveFrom = p_MoveFrom.getArmies() - p_MoveFrom.getCommittedArmies();
        int l_ArmiesOfMoveTo = p_MoveTo.getArmies() - p_MoveTo.getCommittedArmies();

        return (l_ArmiesOfMoveFrom - l_ArmiesOfMoveTo) / 2;
    }
}
