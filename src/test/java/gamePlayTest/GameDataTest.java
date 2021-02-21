package gamePlayTest;

import gameplay.GameData;
import gameplay.GamePhase;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameDataTest {
    /**
     * Test object GameData.
     */
    GameData d_GameData = new GameData();

    /**
     * this is GameData class, GamePhase dMember Test
     */
    @Test
    public void GamePhaseTest() {
        System.out.println("1.check phase change");
        d_GameData.setCurrentPhase(GamePhase.WAITING_TO_TURN);
        System.out.println(d_GameData.getCurrentPhase());
        d_GameData.setCurrentPhase(GamePhase.ATTACK);
        System.out.println(d_GameData.getCurrentPhase());
        assertEquals(GamePhase.ATTACK,d_GameData.getCurrentPhase());
    }
}
