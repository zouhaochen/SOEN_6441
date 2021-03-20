package controller;



import controller.GameEngineController;
import model.GameData;
import model.map.MapEdit;
import model.state.End;
import model.state.Phase;
import model.state.PlaySetup;
import model.state.Preload;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *  Context of the State pattern.
 *  It contains a State object.
 */
public class MainPlayController {
    /**
     * State object of the MainLoop
     */
    private Phase gamePhase;

    String mystart;
    int mycommand;
    /**
     * model.map file that use to load represent game model.map.
     */
    public File d_MapFile = new File(".//domination//test_02.map");
    /**
     * get Game data as an object, used to be the input parameter for GameEngineController class
     */
    public GameData d_GameData = new GameData(d_MapFile);
    /**
     * get game engine as an object that used to call the function from GameEngineController class
     */
    public GameEngineController d_GameEngine = new GameEngineController(d_GameData);
    /**
     * Method that allows the GameEngine object to change its state.
     * @param p_phase new state to be set for the GameEngine object.
     */
    public void setPhase(Phase p_phase) {
        gamePhase = p_phase;
        System.out.println("new phase: " + p_phase.getClass().getSimpleName());
    }

    /**
     * According to user input to check in which model that user are going to get in.
     *
     * @throws IOException if files are not found
     */
    public void Start() throws Exception {

        do {
            Scanner l_Scanner = new Scanner(System.in);
            System.out.println("Welcome to warzone! ");
            System.out.println("Do you want to edit map or play game? (Edit/Play/Exit)");
            System.out.println("( Edit for edit map / Play for play the game / Exit for exit the game )");

            mystart = l_Scanner.nextLine();

            switch (mystart.toLowerCase()) {
                case "edit":
                    // Set the state to Preload
                    setPhase(new Preload(this));
                    //gamePhase.loadMap();
                    do {
                        System.out.println(" =================================================");
                        System.out.println("| #   PHASE                   : command           |");
                        System.out.println(" =================================================");
                        System.out.println("| 1.  Edit:PreLoad            : load map          |");
                        System.out.println("| 2.  Edit:PreLoad            : show map          |");
                        System.out.println("| 3.  Edit:PostLoad           : edit map          |");
                        System.out.println("| 4.  Edit:PostLoad           : save map          |");
                        System.out.println("| 10. Any                     : end               |");
                        System.out.println("| 11. Any                     : next phase        |");
                        System.out.println("| 12. Any                     : previous phase    |");
                        System.out.println(" =================================================");
                        System.out.println("enter a " + gamePhase.getClass().getSimpleName() + " phase command: ");
                        mycommand = l_Scanner.nextInt();
                        System.out.println(" =================================================");
                        //
                        // Calls the method corresponding to the action that the user has selected.
                        // Depending on what it the current state object, these method calls will
                        // have a different implementation.
                        //
                        switch (mycommand) {
                            case 1:
                                gamePhase.loadMap();
                                break;
                            case 2:
                                gamePhase.showMap();
                                break;
                            case 3:
                                gamePhase.editMap();
                                break;
                            case 4:
                                gamePhase.saveMap();
                                break;
                            case 10:
                                gamePhase.endGame();
                                break;
                            case 11:
                                gamePhase.next();
                                break;
                            case 12:
                                gamePhase.previous();
                            default:
                                System.out.println("this command does not exist");
                        }
                    } while (!(gamePhase instanceof End));
                    break;
                case "play":
                    // Set the state to PlaySetup
                    setPhase(new PlaySetup(this));
                    do {
                        System.out.println(" =================================================");
                        System.out.println("| #   PHASE                   : command           |");
                        System.out.println(" =================================================");
                        System.out.println("| 1.  Play:PlaySetup:         : load map          |");
                        System.out.println("| 2.  Play:PlaySetup:         : show map          |");
                        System.out.println("| 7.  Play:MainPlay:deploy    : reinforce+deploy  |");
                        System.out.println("| 8.  Play:MainPlay:advance   : advance           |");
                        System.out.println("| 9.  Play:MainPlay:cards     : cards             |");
                        System.out.println("| 10. Play                    : end game          |");
                        System.out.println("| 11. Play:PlaySetup:         : next phase        |");
                        System.out.println("| 12. Any                     : previous phase    |");
                        System.out.println(" =================================================");
                        System.out.println("enter a " + gamePhase.getClass().getSimpleName() + " phase command: ");
                        mycommand = l_Scanner.nextInt();
                        System.out.println(" =================================================");
                        //
                        // Calls the method corresponding to the action that the user has selected.
                        // Depending on what it the current state object, these method calls will
                        // have a different implementation.
                        //
                        switch (mycommand) {
                            case 1:
                                gamePhase.loadMap();
                                break;
                            case 2:
                                gamePhase.showMap();
                                break;
                            case 7:
                                gamePhase.deploy();
                                break;
                            case 8:
                                gamePhase.advance();
                                break;
                            case 9:
                                gamePhase.cards();
                                break;
                            case 10:
                                gamePhase.endGame();
                                break;
                            case 11:
                                gamePhase.next();
                                break;
                            case 12:
                                gamePhase.previous();
                            case 13:

                                break;
                            default:
                                System.out.println("this command does not exist");
                        }
                    } while (!(gamePhase instanceof End));
                    break;
                case "exit":
                    System.out.println("Exiting Warzone Game see you next time!");
                    return;
            }



        } while (mycommand != 3);
        //l_Scanner.close();
    }

}
