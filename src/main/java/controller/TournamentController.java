package controller;

import command.CommandValidator;
import model.LogEntryBuffer;
import model.gameelements.Player;
import model.state.Edit;
import model.state.End;
import model.state.play.LoadMap;

import java.io.File;
import java.util.Scanner;

/**
 *To implement the Tournament game Mode.
 */
public class TournamentController extends MainPlayController {
    /**
     *  list of map files that players are used.
     */
    public File[] l_listOfMapFiles;
    /**
     * list of players strategies
     */
    private Player l_listofplayerstrategies;
    /**
     *  for how many game that players played
     */
    public int d_NumberOfGames;
    /**
     * set the max number of turns for each game.
     */
    public int d_MaxNumberOfTurns;

    private MainPlayController myGame = new MainPlayController();

    /**
     * game logger
     */
    private LogEntryBuffer d_LogEntryBuffer = new LogEntryBuffer();

    @Override
    public void Start(){
        d_LogEntryBuffer.start();
        CommandValidator.setGameData(d_GameData);
        do {
            Scanner l_Scanner = new Scanner(System.in);
            System.out.println("Welcome to tournament mode game! ");
            System.out.println("Do you want to edit map or play game? (Edit/Play/Exit)");
            System.out.println("( Edit for edit map / Play for play the game / Exit for exit the game )");

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
                    d_NumberOfGames ++;
                    System.out.println("Now, the numer of Game is: "+ d_NumberOfGames);
                    break;
                case "exit":
                    System.out.println("Exiting Warzone Game see you next time!");
                    return;
                default:
                    continue;
            }
            do {
                System.out.println(" =====================================================");
                System.out.println("| #   PHASE                       : command           |");
                System.out.println(" =====================================================");
                System.out.println("| 1.  Edit                        : editmap           |");
                System.out.println("| 2.  Edit                        : savemap           |");
                System.out.println("| 3.  set Up for Game             : showmap           |");
                System.out.println("| 4.  get list of map             : -M listofmapfile  |");
                System.out.println("| 5.  get listof player strategy  : -p listofstrategy |");
                System.out.println("| 4.  Play:MainPlay:start to play : start             |");
                System.out.println("| 5. Any                          : end               |");
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
//                        // 记录该地图名称
//                        System.out.println("please enter which of the map you are going to use: ");
//                        String l_mapName = l_Scanner.nextLine();
//
//                       for (int i = 0; i<=3; i++){
//                           if(mapPathCheck(p_file)) {
//                               l_listOfMapFiles[i];
//                           }
//                       }
//                        break;
                    case "addplayer":
                        gamePhase.setPlayers();
                        gamePhase.assignCountries();
                        break;

                    case "end":
                        gamePhase.endGame();
                        break;

                    case "start":
                        System.out.println("please enter for how many game in total: ");
                        d_NumberOfGames = l_Scanner.nextInt();
                        int l_GameCount = 1;

                        System.out.println("please enter the Max round of each game: ");
                        d_MaxNumberOfTurns = l_Scanner.nextInt();

                        do {
                            System.out.println("===== Game " + l_GameCount + " =====");
                            int l_RoundCount = 1;
                            while (true) {
                                System.out.println("===== Round " + l_RoundCount + " =====");
                                gamePhase.issueOrder();
                                gamePhase.executeOrder();
                                gamePhase.showMap();
                                l_RoundCount++;
                                // set up round limit, prevent from infinity battle round
                                if (l_RoundCount >= d_MaxNumberOfTurns) {
                                    System.out.println("===== Maximum Round Reach =====");
                                    System.out.println("===== DRAW ===== No Winner =====");
                                    gamePhase.endGame();
                                }
                                if (gamePhase instanceof End) {
                                    System.out.println("the rounds of this Game is: " + l_RoundCount);
                                    break;
                                }
                            }
                            l_GameCount ++;
                        }while (l_GameCount <= d_NumberOfGames);
                        break;

                    default:
                        System.out.println("this command does not exist");
                }
            } while (!(gamePhase instanceof End));
            System.out.println("\n");
        } while (!mycommand.equals("end"));
    }


    public Boolean mapPathCheck( File p_file){
        if(p_file.exists()){
            return true;
        }
        return false;
    }
}
