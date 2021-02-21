package gamePlayTest;

import gameelements.Country;
import gameelements.Player;
import gameplay.GameData;
import gameplay.GameEngine;
import gameplay.GamePhase;
import org.junit.Test;

import java.util.Map;

/**
 * Game engine test unit
 */

public class GameEngineTest {
    GameData d_GameData = new GameData();
    GameEngine d_GameEngine = new GameEngine(d_GameData);


    /**
     * this method used to test GameEngine Class
     */
    @Test
    public void testGameEngine() {
        System.out.println("Game Engine Test Start:");

    }

    /**
     * thie method used to test add and remove player to the game
     */
    @Test
    public void testAddRemovePlayer() {
        System.out.println("1.check add Player");
        d_GameEngine.addNewPlayer("red");
        System.out.println("2.check removed Player");
        d_GameEngine.removePlayer(d_GameEngine.d_GameData.getD_PlayerList().get(0));

    }

    /**
     * test game phase change.
     */
    @Test
    public void testPhaseChange() {
        System.out.println("3.check phase change");
        d_GameEngine.d_GameData.setCurrentPhase(GamePhase.WAITING_TO_TURN);
        System.out.println(d_GameData.getCurrentPhase());
        d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ATTACK);
        System.out.println(d_GameData.getCurrentPhase());
    }

    /**
     * test show map function
     */
    @Test
    public void testShowMap() {
        System.out.println("4. check show map");
        d_GameEngine.addNewPlayer("black");
        Player l_Player=d_GameEngine.d_GameData.getD_PlayerList().get(0);

        Country l_Country1=new Country("China");
        l_Country1.setArmies(10);
        Country l_Country2=new Country("India");
        l_Country2.setArmies(5);
        Map<String,Country> l_TempCountryMap=l_Player.getCountriesInControl();
        l_TempCountryMap.put(l_Country1.getName(),l_Country1);
        l_TempCountryMap.put(l_Country2.getName(),l_Country2);
        d_GameEngine.showMap(l_Player);
    }
}
