package gamePlayTest;

import gameplay.GameData;
import gameplay.GameEngine;
import org.junit.Test;

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
        System.out.println("1.check removed Player");
        d_GameEngine.RemovePlayer(d_GameEngine.d_GameData.getD_PlayerList().get(0));
    }
}
