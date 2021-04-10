import controller.MainPlayController;
import controller.SingleGameController;
import view.LogEntryBufferView;

import java.util.Scanner;

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
    public GameDriver(MainPlayController p_MainPlayController) {
        // setup controller
        d_MainPlayController = p_MainPlayController;
        // assign logger as model, pass it to logger view
        d_LogEntryBufferView = new LogEntryBufferView(d_MainPlayController.getDLogEntryBuffer());
    }

    /**
     * main method, Show each game phase from GameEngineController, and run the game according to the game rules
     *
     * @param args To get parameters from console
     */
    public static void main(String[] args) {

        Scanner l_Scanner = new Scanner(System.in);

        System.out.println("Welcome to WAR-ZONE game! ");

        GameDriver l_GameDriver;

        while (true) {
            System.out.println("===== game mode selection(single,tournament)=====");
            String l_Mode=l_Scanner.nextLine();
            switch (l_Mode){
                case "single":
                    // new single game
                    System.out.println(" *****single game mode*****");
                    l_GameDriver = new GameDriver(new SingleGameController());
                    l_GameDriver.d_MainPlayController.Start();
                    break;
                case "tournament":
                    // new tournament game
                    System.out.println("*****tournament game mode*****");
                    System.out.println("not implement yet");
                    continue;
                default:
                    System.out.println("Error: mode not found");
                    continue;
            }
            break;
        }


        // run the controller
        l_GameDriver.d_MainPlayController.Start();
        //remove obs when game over
        l_GameDriver.d_MainPlayController.removeObs(l_GameDriver.d_LogEntryBufferView);

    }
}
