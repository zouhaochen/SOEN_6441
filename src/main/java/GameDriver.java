import controller.GameEngineController;
import controller.MainLoopController;
import model.state.MainLoop;

import java.io.IOException;

/**
 * Start running the risk game
 */
public class GameDriver {
    /**
     * main method, Show each game phase from GameEngineController, and run the game according to the game rules
     *
     * @param args To get parameters from console
     * @throws IOException if file does not exist
     */
    public static void main(String args[]) throws Exception {
        String file = "domination/test_02.map";
        MainLoop l_mainLoop = new MainLoop();
        l_mainLoop.Start();
//      MainLoopController l_mainLoop = new MainLoopController(file);
//      l_mainLoop.MainLogic();
    }
}
