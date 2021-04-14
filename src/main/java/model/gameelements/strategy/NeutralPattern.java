package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Player;
import model.gameelements.order.Order;

/**
 *  implement player strategy as Neutral type.
 */
public class NeutralPattern extends PlayerStrategy{
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public NeutralPattern(Player p_Player, GameData p_GameData) {
        super(p_Player, p_GameData);
    }

    /**
     * Create order order.
     *
     * @return null
     */
    @Override
    public Order createOrder() {
        return null;
    }

    /**
     * Reset strategy states.
     */
    @Override
    public void reset() {

    }
}
