package model.gameelements.state;

import controller.MainPlayController;

import model.state.*;
import model.state.play.AddPlayer;
import org.junit.After;
import org.junit.Test;



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
        d_MainPlayController.gamePhase.setPlayers();
        d_MainPlayController.gamePhase.assignCountries();
        d_MainPlayController.gamePhase.loadMap();
    }

    @Test
    public void testPlayState() {
        d_MainPlayController.setPhase(new AddPlayer(d_MainPlayController));
        d_MainPlayController.gamePhase.loadMap();
    }
}
