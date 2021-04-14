package controller;

import command.CommandValidator;
import model.LogEntryBuffer;
import model.state.Edit;
import model.state.End;
import model.state.play.LoadMap;

import java.io.Serializable;
import java.util.Scanner;

/**
 * to implemnt single game mode.
 */
public class SingleGameController extends MainPlayController implements Serializable {

    /**
     * game logger
     */
    transient private LogEntryBuffer d_LogEntryBuffer = new LogEntryBuffer();

    @Override
    public void Start(){
        d_LogEntryBuffer.start();
        CommandValidator.setGameData(d_GameData);
        do {
            Scanner l_Scanner = new Scanner(System.in);
            System.out.println("Welcome to single game mode! ");
            System.out.println("Do you want to edit map or play game? (Edit/Play/Back)");
            System.out.println("( Edit for edit map / Play for play the game / Back for return to main menu )");

            mystart = l_Scanner.nextLine();
            mycommand = "";

            switch (mystart.toLowerCase()) {
                case "edit":
                    // Set the state to Preload
                    setPhase(new Edit(this));
                    break;
                case "play":
                    // Set the state to PlaySetup
                    setPhase(new LoadMap(this));
                    break;
                case "back":
                    System.out.println("return to main menu");
                    return;
                default:
                    continue;
            }
            do {
                System.out.println(" =====================================================");
                System.out.println("| #   PHASE                      : command           |");
                System.out.println(" =====================================================");
                System.out.println("| 1.  Edit                       : editmap           |");
                System.out.println("| 2.  Edit                       : savemap           |");
                System.out.println("| 3.  Play except for LoadMap    : showmap           |");
                System.out.println("| 4.  Play:Startup:LoadMap       : loadmap           |");
                System.out.println("| 4.  Play:Startup:LoadMap       : loadgame          |");
                System.out.println("| 5.  Play:Startup:AddPlayer     : addPlayer         |");
                System.out.println("| 6.  Play:Startup:AssignCountry : assigncountries   |");
                System.out.println("| 7.  Play:MainPlay:start to play: start             |");
                System.out.println("| 8.  Play:MainPlay:IssueOrder   : issue (user)      |");
                System.out.println("| 9.  Play:MainPlay:ExecuteOrder : execute  (user)   |");
                System.out.println("| 10. Play:MainPlay:SavaGame     : savegame          |");
                System.out.println("| 11. Any                        : end               |");
                System.out.println(" =====================================================");
                System.out.println("enter a " + gamePhase.getClass().getSimpleName() + " phase command: ");
                mycommand = l_Scanner.nextLine();
                System.out.println(" =====================================================");
                //
                // Calls the method corresponding to the action that the user has selected.reflection
                // Depending on what it the current state object, these method calls will
                // have a different implementation.
                //
                switch (mycommand) {
                    case "editmap":
                        gamePhase.editMap();
                        break;
                    case "savemap":
                        gamePhase.saveMap();
                        break;
                    case "showmap":
                        gamePhase.showMap();
                        break;
                    case "loadmap":
                        gamePhase.loadMap();
                        break;
                    case "loadgame":
                        gamePhase.loadGame();
                        showMap();
                        break;
                    case "addplayer":
                        gamePhase.setPlayers();
                        break;
                    case "assigncountries":
                        gamePhase.assignCountries();
                        break;
                    case "issue":
                        gamePhase.issueOrder();
                        break;
                    case "execute":
                        gamePhase.executeOrder();
                        break;
                    case "savegame":
                        gamePhase.saveGame(d_GameData);
                        break;
                    case "end":
                        gamePhase.endGame();
                        break;
                    case "start":
                        int l_RoundCount=1;
                        while (true){
                            System.out.println("===== Round "+l_RoundCount+" =====");
                            gamePhase.issueOrder();
                            gamePhase.executeOrder();
                            gamePhase.showMap();
                            l_RoundCount++;
                            // set up round limit, prevent from infinity battle round
                            if (l_RoundCount>=31){
                                System.out.println("===== Maximum Round Reach =====");
                                System.out.println("===== DRAW ===== No Winner =====");
                                gamePhase.endGame();
                            }
                            if (gamePhase instanceof End){
                                break;
                            }
                        }
                        break;
                    default:
                        System.out.println("this command does not exist");
                }
            } while (!(gamePhase instanceof End));
            d_LogEntryBuffer.updateFile();
            System.out.println("\n");
        } while (!mycommand.equals("end"));
    }
}
