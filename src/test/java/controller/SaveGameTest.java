package controller;

import model.GameData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SaveGameTest {

    /**
     * The tournament controller.
     */
    private SingleGameController d_Single;
    /**
     * The map file names.
     */
    private List<String> d_MapFileNames;

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
    public void checked() {
        System.out.println("ok");
    }

    /**
     * this is load model.map function test
     */
    @Test
    public void loadMapTest() {
        System.out.println("check save game function");
        try {
            d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
