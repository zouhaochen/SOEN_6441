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

public class RandomPattern extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public RandomPattern(Player p_Player, GameData p_GameData) {
        super(p_Player, p_GameData);
    }

    @Override
    protected Country toDefend() {
        if (getPlayer().getCountriesInControl().size() == 0) {
            return null;
        }
        List<Country> l_Countries = new ArrayList<>(getPlayer().getCountriesInControl().values());
        return l_Countries.get(getRandom().nextInt(l_Countries.size()));
    }

    @Override
    protected Country attackFrom() {
        return toDefend();
    }

    /**
     * Create order order.
     *
     * @return the order
     */
    @Override
    public Order createOrder() {
        Player l_Player = getPlayer();
        boolean hasCard = l_Player.getCards().size() > 0;
        int l_RandomOrderSelection = hasCard ? getRandom().nextInt(6) : getRandom().nextInt(5);
        Country l_AttackFrom = attackFrom();
        int l_AvailableArmies = Math.max(0, l_AttackFrom.getArmies() - l_AttackFrom.getCommittedArmies());
        int l_Armies = getRandom().nextInt(l_AvailableArmies + 1);
        switch (l_RandomOrderSelection) {
            case 0:
                int l_AvailableReinforcement = l_Player.getReinforcementArmies() - l_Player.getCommittedReinforcement();
                if (l_AvailableReinforcement <= 0) {
                    return null;
                }
                return new DeployOrder(l_Player, l_AttackFrom, getRandom().nextInt(l_AvailableReinforcement) + 1);
            case 1:
                if (l_Armies == 0) {
                    return null;
                }
                return new AdvanceOrder(l_Player, l_AttackFrom, getRandomNeighborOfCountry(l_AttackFrom), l_Armies);
            case 2:
                if (l_Armies == 0) {
                    return null;
                }
                return new AdvanceOrder(l_Player, l_AttackFrom, toDefend(), l_Armies);
            case 5:
                Card l_Card = l_Player.getCards().remove(0);
                return CardOrderCreator.createCardOrder(l_Card, l_Player, getTargetPlayer(), l_AttackFrom, toDefend(), getRandomNeighbor(), l_Armies);
            default:
                return null;
        }
    }

    /**
     * Reset strategy states.
     */
    @Override
    public void reset() {

    }
}
