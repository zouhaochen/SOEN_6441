package model.gameelements.state;

import controller.GameEngineController;
import controller.MainPlayController;
import model.GameData;
import model.map.MapListing;
import model.state.*;
import model.state.play.AddPlayer;
import model.state.play.Play;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class StateTest {


    /**
     * The MainPlayController class to be tested.
     */
    public MainPlayController d_MainPlayController = new MainPlayController();

    /**
     * print ok when test is passed
     */
    @After
    public void checked(){
        System.out.println("ok");
    }

    @Test
    public void testEditState() {
        d_MainPlayController.setPhase(new Edit(d_MainPlayController));
        d_MainPlayController.gamePhase.deploy();
    }

    @Test
    public void testPlayState() {
        d_MainPlayController.setPhase(new AddPlayer(d_MainPlayController));
        d_MainPlayController.gamePhase.loadMap();
    }
}
