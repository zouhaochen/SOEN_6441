package gamePlayTest;

import gameplay.GameData;
import gameplay.GameEngine;
import gameplay.GamePhase;
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
        d_GameEngine.removePlayer(d_GameEngine.d_GameData.getD_PlayerList().get(0));
    }

    /**
     * test game phase change.
     */
    @Test
    public void testPhaseChange(){
        System.out.println("3.check phase change");
        d_GameEngine.d_GameData.setCurrentPhase(GamePhase.WAITING_TO_TURN);
        System.out.println(d_GameData.getCurrentPhase());
        d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ATTACK);
        System.out.println(d_GameData.getCurrentPhase());
    }
}
