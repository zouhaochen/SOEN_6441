package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Card;
import model.gameelements.CardOrderCreator;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.AdvanceOrder;
import model.gameelements.order.DeployOrder;
import model.gameelements.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            if (l_CountryWithMostArmies == null || l_Country.getArmies() > l_CountryWithMostArmies.getArmies()) {
                l_CountryWithMostArmies = l_Country;
            }
        }

        return l_CountryWithMostArmies;
    }

    /**
     * Move from country.
     *
     * @return the country
     */
    @Override
    protected Country moveFrom() {
        Random l_Rand = new Random();
        List<Country> l_Countries = new ArrayList<>(getPlayer().getCountriesInControl().values());

        return l_Countries.get(l_Rand.nextInt(l_Countries.size()));
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
        if (getPlayer().getReinforcementArmies() > 0) {
            return new DeployOrder(getPlayer(), attackFrom(), getPlayer().getReinforcementArmies());
        } else if (!d_AttackedInCurrentTurn) {
            Country l_AttackFrom = attackFrom();
            d_AttackedInCurrentTurn = true;
            return new AdvanceOrder(getPlayer(), l_AttackFrom, getRandomNeighborOfCountry(l_AttackFrom), l_AttackFrom.getArmies());
        } else if (getPlayer().getCards().size() != 0) {
            // create Card order
            Card l_Card = getPlayer().getCards().get(0);
            Country l_MoveFrom = moveFrom();
            return CardOrderCreator.createCardOrder(l_Card, getPlayer(), getTargetPlayer(), l_MoveFrom, toDefend(), getRandomNeighbor(), l_MoveFrom.getArmies());
        } else {
            Country l_AttackFrom = attackFrom();
            Country l_MoveFrom = moveFrom();
            int l_Count = 1;
            while (l_Count < l_AttackFrom.getBorderCountries().size() && !l_AttackFrom.getBorderCountries().containsKey(l_MoveFrom.getName())) {
                l_MoveFrom = moveFrom();
                l_Count++;
            }

            if (l_Count >= l_AttackFrom.getBorderCountries().size()) {
                return null;
            }

            return new AdvanceOrder(getPlayer(), l_MoveFrom, l_AttackFrom, l_MoveFrom.getArmies());
        }
    }

    @Override
    public void reset() {
        d_AttackedInCurrentTurn = false;
    }
}
