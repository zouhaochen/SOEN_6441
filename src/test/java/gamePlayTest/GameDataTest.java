package gamePlayTest;

import gameplay.GameData;
import gameplay.GamePhase;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 *  game data test class
 */
public class GameDataTest {

    /**
     * test map file
     */
    File d_File = new File("germany.map");
    /**
     * Test object GameData.
     */
    GameData d_GameData = new GameData(d_File);

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

    /**
     * this is load map function test
     */
    @Test
    public void loadMapTest(){
        System.out.println("2.check load map function");
        try{
            d_GameData.loadMap();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
