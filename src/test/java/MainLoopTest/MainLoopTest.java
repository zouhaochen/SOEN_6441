package MainLoopTest;

import gameplay.GameData;
import gameplay.GameEngine;
import gameplay.GamePhase;
import gameplay.MainLoop;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * MainLoop test unit
 */
public class MainLoopTest {
    /**
     * this method used to test MainLoop Class
     */
    public static GameData d_GameData;
    private static GameEngine d_GameEngine;




    @Test
    public void testMainLogic() throws Exception {
        String file = "test_02.map";
        MainLoop mainLoop = new MainLoop(file);
        mainLoop.MainLogic();
    }


}
