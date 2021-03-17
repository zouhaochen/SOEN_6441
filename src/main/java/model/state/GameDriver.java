package model.state;

import java.io.IOException;

public class GameDriver {
    /**
     * main method, Show each game phase from GameEngineController, and run the game according to the game rules
     *
     * @param args To get parameters from console
     * @throws IOException if file does not exist
     */
    public static void main(String[] args) throws Exception {
        //filename initial
        String file = "domination/test_02.map";
        MainLoop mainLoop = new MainLoop(file);
        mainLoop.MainLogic();
    }
}
