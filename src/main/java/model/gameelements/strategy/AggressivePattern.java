package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Card;
import model.gameelements.CardOrderCreator;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.AdvanceOrder;
import model.gameelements.order.DeployOrder;
import model.gameelements.order.Order;

/**
 * The type Aggressive pattern.
 */
public class AggressivePattern extends PlayerStrategy {

    /**
     * Whether the player has issued Advance order in current turn.
     */
    private boolean d_AttackedInCurrentTurn;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public AggressivePattern(Player p_Player, GameData p_GameData) {
        super(p_Player, p_GameData);
    }

    /**
     * Attack from country.
     *
     * @return the country
     */
    @Override
    protected Country attackFrom() {
        if (getPlayer().getCountriesInControl().size() == 0) {
            return null;
        }

        Country l_CountryWithMostArmies = null;
        for (Country l_Country : getPlayer().getCountriesInControl().values()) {
            if (l_CountryWithMostArmies == null
                    || (l_Country.getArmies() - l_Country.getCommittedArmies())
                    > (l_CountryWithMostArmies.getArmies() - l_CountryWithMostArmies.getCommittedArmies())) {
                l_CountryWithMostArmies = l_Country;
            }
        }

        return l_CountryWithMostArmies;
    }

    /**
     * To defend country.
     *
     * @return the country
     */
    @Override
    protected Country toDefend() {
        return moveFrom();
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
            // option 1: reinforce the strongest country
            return new DeployOrder(getPlayer(), attackFrom(), l_AvailableReinforcement);
        } else if (!d_AttackedInCurrentTurn) {
            // option 2: attack with the strongest country
            Country l_AttackFrom = attackFrom();
            d_AttackedInCurrentTurn = true;
            return new AdvanceOrder(getPlayer(), l_AttackFrom, getRandomNeighborOfCountry(l_AttackFrom), l_AttackFrom.getArmies() - l_AttackFrom.getCommittedArmies());
        } else if (getPlayer().getCards().size() != 0) {
            // option 3: use card if the player has any
            Card l_Card = getPlayer().getCards().remove(0);
            Country l_MoveFrom = moveFrom();
            int l_ArmiesToMove = l_MoveFrom.getArmies() - l_MoveFrom.getCommittedArmies();
            return CardOrderCreator.createCardOrder(l_Card, getPlayer(), getRandomOpponentPlayer(), l_MoveFrom, attackFrom(), getRandomNeighbor(), l_ArmiesToMove);
        } else {
            // option 4: move armies to maximize aggregation of forces in one country
            Country l_MoveTo = attackFrom();
            Country l_MoveFrom = moveFrom();
            int l_Count = 1;
            int l_SearchLimit = getPlayer().getCountriesInControl().size() * 2; // limits the times of the search for a valid country

            while (l_Count < l_SearchLimit && isInvalidMoveFromCountry(l_MoveFrom, l_MoveTo)) {
                l_MoveFrom = moveFrom();
                l_Count++;
            }

            if (isInvalidMoveFromCountry(l_MoveFrom, l_MoveTo)) {
                return null;
            }

            return new AdvanceOrder(getPlayer(), l_MoveFrom, l_MoveTo, l_MoveFrom.getArmies() - l_MoveFrom.getCommittedArmies());
        }
    }


    @Override
    public void reset() {
        d_AttackedInCurrentTurn = false;
    }

    /**
     * Check whether the move-from country is invalid.
     *
     * @param p_MoveFrom the move-from country
     * @param p_MoveTo   the move-to country
     * @return true if the move-from country is invalid, false otherwise
     */
    private boolean isInvalidMoveFromCountry(Country p_MoveFrom, Country p_MoveTo) {
        return (p_MoveFrom.getArmies() <= p_MoveFrom.getCommittedArmies()) || !p_MoveFrom.getBorderCountries().containsKey(p_MoveTo.getName());
    }
}
