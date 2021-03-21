import controller.MainPlayController;
import view.LogEntryBufferView;

import java.io.IOException;

/**
 * Start running the risk game
 */
public class GameDriver {
    /**
     * main game controller
     */
    MainPlayController d_MainPlayController;
    /**
     * logger view
     */
    LogEntryBufferView d_LogEntryBufferView;

    /**
     * constructor
     */
    public GameDriver(){
        // setup controller
        d_MainPlayController = new MainPlayController();
        // assign logger as model, pass it to logger view
        d_LogEntryBufferView = new LogEntryBufferView(d_MainPlayController.getDLogEntryBuffer());
    }

    /**
     * main method, Show each game phase from GameEngineController, and run the game according to the game rules
     *
     * @param args To get parameters from console
     * @throws IOException if file does not exist
     */
    public static void main(String args[]) throws Exception {

        // new game
        GameDriver l_GameDriver=new GameDriver();
        // run the controller
        l_GameDriver.d_MainPlayController.Start();
        //remove obs when game over
        l_GameDriver.d_MainPlayController.removeObs(l_GameDriver.d_LogEntryBufferView);


    }
}
