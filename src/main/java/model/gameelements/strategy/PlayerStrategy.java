package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Player;
import model.gameelements.order.Order;

/**
 * The type Player strategy.
 */
public abstract class PlayerStrategy {

    /**
     * The game data.
     */
    private GameData d_GameData;
    /**
     * The player.
     */
    private Player d_Player;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public PlayerStrategy(Player p_Player, GameData p_GameData) {
        d_Player = p_Player;
        d_GameData = p_GameData;
    }

    /**
     * Gets game data.
     *
     * @return the game data
     */
    public GameData getGameData() {
        return d_GameData;
    }

    /**
     * Sets game data.
     *
     * @param p_GameData the game data
     */
    public void setGameData(GameData p_GameData) {
        this.d_GameData = p_GameData;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return d_Player;
    }

    /**
     * Sets player.
     *
     * @param p_Player the player
     */
    public void setPlayer(Player p_Player) {
        this.d_Player = p_Player;
    }

    /**
     * Create order order.
     *
     * @return the order
     */
    public abstract Order createOrder();
}
