package model.gameelements.state.play;

import controller.MainPlayController;
import model.state.Edit;
import model.state.play.AddPlayer;
import org.junit.Test;


/**
 * unit test for Play State Test
 */
public class PlayStateTest {

    /**
     * The Play package to be tested.
     */
    public MainPlayController d_MainPlayController = new MainPlayController();

    /**
     * unit test for edit state
     */
    @Test
    public void testEditState() {
        d_MainPlayController.setPhase(new Edit(d_MainPlayController));
        d_MainPlayController.gamePhase.deploy();
    }

    /**
     * unit test for play state
     */
    @Test
    public void testPlayState() {
        d_MainPlayController.setPhase(new AddPlayer(d_MainPlayController));
        d_MainPlayController.gamePhase.loadMap();
    }
}
