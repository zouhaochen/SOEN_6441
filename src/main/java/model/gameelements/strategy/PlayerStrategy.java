package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * The random.
     */
    private Random d_Random;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public PlayerStrategy(Player p_Player, GameData p_GameData) {
        d_Player = p_Player;
        d_GameData = p_GameData;
        d_Random = new Random();
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
     * Gets random.
     *
     * @return the random
     */
    public Random getRandom() {
        return d_Random;
    }

    /**
     * Create order order.
     *
     * @return the order
     */
    public abstract Order createOrder();

    /**
     * Reset strategy states.
     */
    public abstract void reset();

    /**
     * To attack an random country.
     *
     * @return the country
     */
    protected Country toAttack() {
        List<Country> l_Countries = getGameData().getCountryList();
        Country l_CountryToAttack;
        do {
            l_CountryToAttack = l_Countries.get(d_Random.nextInt(l_Countries.size()));
        } while (l_CountryToAttack.getOwner().getColour().equalsIgnoreCase(getPlayer().getColour()));

        return l_CountryToAttack;
    }

    /**
     * Attack from country.
     *
     * @return the country
     */
    protected Country attackFrom() {
        return null;
    }

    /**
     * Move from country.
     *
     * @return the country
     */
    protected Country moveFrom() {
        return null;
    }

    /**
     * To defend country.
     *
     * @return the country
     */
    protected Country toDefend() {
        return null;
    }

    /**
     * Gets random neighbor of country.
     *
     * @param p_Departure the p departure
     * @return the random neighbor of country
     */
    protected Country getRandomNeighborOfCountry(Country p_Departure) {
        List<Country> l_Neighbors = new ArrayList<>(p_Departure.getBorderCountries().values());
        return l_Neighbors.get(d_Random.nextInt(l_Neighbors.size()));
    }

    /**
     * Gets random neighbor.
     *
     * @return the random neighbor
     */
    protected Country getRandomNeighbor() {
        List<Country> l_CountriesInControl = new ArrayList<>(d_Player.getCountriesInControl().values());
        List<Country> l_RandomNeighbors = new ArrayList<>(l_CountriesInControl.get(d_Random.nextInt(l_CountriesInControl.size())).getBorderCountries().values());
        return l_RandomNeighbors.get(d_Random.nextInt(l_RandomNeighbors.size()));
    }

    /**
     * Gets target player.
     *
     * @return the target player
     */
    protected Player getTargetPlayer() {
        List<Player> l_Players = d_GameData.getPlayerList();
        Player l_Target;

        do {
            l_Target = l_Players.get(d_Random.nextInt(l_Players.size()));
        } while (l_Target.getId() == d_Player.getId());

        return l_Target;
    }

}
