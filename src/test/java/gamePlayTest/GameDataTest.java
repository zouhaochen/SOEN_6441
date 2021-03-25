package gamePlayTest;

import model.GameData;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


/**
 *  game data test class
 */
public class GameDataTest {

    /**
     * test model.map file
     */
    File d_File = new File("domination/germany.map");
    /**
     * Test object GameData.
     */
    GameData d_GameData = new GameData(d_File);
    /**
     * print ok when test is passed
     */
    @After
    public void checked(){
        System.out.println("ok");
    }

    /**
     * this is load model.map function test
     */
    @Test
    public void loadMapTest(){
        System.out.println("check load model.map function");
        try{
            d_GameData.loadMap();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
