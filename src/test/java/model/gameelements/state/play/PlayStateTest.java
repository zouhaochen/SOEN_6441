package model.gameelements.state.play;

import controller.MainPlayController;
import model.state.*;
import model.state.play.AddPlayer;
import org.junit.Test;



public class PlayStateTest {

    /**
     * The Play package to be tested.
     */
    public MainPlayController d_MainPlayController = new MainPlayController();

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
