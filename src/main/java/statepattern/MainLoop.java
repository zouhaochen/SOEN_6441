package statepattern;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.map.MapEdit;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *  Context of the State pattern.
 *  It contains a State object.
 */
public class MainLoop {
    /**
     * State object of the MainLoop
     */
    private Phase gamePhase;

    String mystart;
    int mycommand;

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
        Scanner l_Scanner = new Scanner(System.in);

        do {
            System.out.println("Welcome to warzone! ");
            System.out.println("Do you want to edit model.map or play game (Edit/Play/Exit)");
            System.out.println("( Edit for edit model.map / Play for play the game / Exit for exit the game )");
            mystart = l_Scanner.nextLine();
            switch (mystart) {
                case "Edit":
                    // Set the state to Preload
                    //setPhase(new Preload(this));
                    break;
                case "Play":
                    // Set the state to PlaySetup
                    //setPhase(new PlaySetup(this));
                    break;
                case "Exit":
                    System.out.println("Bye!");
                    return;
            }
            do {
                System.out.println(" =================================================");
                System.out.println("| #   PHASE                   : command           |");
                System.out.println(" =================================================");
                System.out.println("| 1.  Any except MainPlay     : load map          |");
                System.out.println("| 2.  Any                     : show map          |");
                System.out.println("| 3.  Edit:PostLoad           : edit country      |");
                System.out.println("| 4.  Edit:Postload           : save map and play |");
                System.out.println("| 5.  Play:PlaySetup          : set players       |");
                System.out.println("| 6.  Play:PlaySetup          : assign countries  |");
                System.out.println("| 7.  Play:MainPlay:Reinforce : reinforce         |");
                System.out.println("| 8.  Play:MainPlay:Attack    : attack            |");
                System.out.println("| 9.  Play:MainPlay:Fortify   : fortify           |");
                System.out.println("| 10. Play                    : end game          |");
                System.out.println("| 0.  Any                     : next phase        |");
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
                        gamePhase.editCountry();
                        break;
                    case 4:
                        gamePhase.saveMap();
                        break;
                    case 5:
                        gamePhase.setPlayers();
                        break;
                    case 6:
                        gamePhase.assignCountries();
                        break;
                    case 7:
                        gamePhase.reinforce();
                        break;
                    case 8:
                        gamePhase.attack();
                        break;
                    case 9:
                        gamePhase.fortify();
                        break;
                    case 10:
                        gamePhase.endGame();
                        break;
                    case 0:
                        gamePhase.next();
                        break;
                    default:
                        System.out.println("this command does not exist");
                }
            } while (mycommand != 3);
        } while (mycommand != 3);

        l_Scanner.close();
    }
}
