package model.state;

import controller.GameEngineController;
import model.GameData;
import model.gameelements.Player;

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
    private model.state.Phase gamePhase;
    public String p_Command;
    public String p_Color;
    public String[] p_command;
    public File p_filename;
    public Player p_player;
    public String p_countryID;
    public int p_numberOfArmies;
    public String p_countryIDFrom;
    public String p_countryIDTo;
    public String p_playerID;

    String mystart;
    int mycommand;

    /**
     * get Game data as an object, used to be the input parameter for GameEngineController class
     */
    public GameData d_GameData;
    /**
     * get game engine as an object that used to call the function from GameEngineController class
     */
    public GameEngineController d_GameEngine;
    /**
     * model.map file that use to load represent game model.map.
     */
    public File d_MapFile;

    /**
     * Constructor
     * the main game loop that to control game phases.
     *
     * @param p_FilePath the model.map file path that use to load game model.map.
     */
    public MainLoop(String p_FilePath) {
        this.d_MapFile = new File(p_FilePath);
        this.d_GameData = new GameData(d_MapFile);
        this.d_GameEngine = new GameEngineController(d_GameData);
    }

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
    public void MainLogic() throws Exception {
        Scanner l_Scanner = new Scanner(System.in);

        do {
            System.out.println("Welcome to warzone! ");
            System.out.println("Do you want to edit model.map or play game (Edit/Play/Exit)");
            System.out.println("( Edit for edit model.map / Play for play the game / Exit for exit the game )");
            mystart = l_Scanner.nextLine();
            switch (mystart) {
                case "Edit":
                    // Set the state to Preload
                    //setPhase(new mapEditLoop());
                    break;

                case "Play":
                    // Set the state to PlaySetup
                    //setPhase(new playStartUpPhase());
                    System.out.println("------The End of Game------ ");
                    break;

                case "Exit":
                    System.out.println("Exiting Warzone Game see you next time!");
                    return;
            }

            do {
                    System.out.println(" ================================================================");
                    System.out.println("| #   PHASE                   : command                         |");
                    System.out.println(" ================================================================");
                    System.out.println("| 1.  Any except play         : edit map                        |");
                    System.out.println("| 2.  Edit Preload            : editContinent                   |");
                    System.out.println("| 3.  Edit Preload            : edit country                    |");
                    System.out.println("| 4.  Edit:Preload            : editNeighbor                    |");
                    System.out.println("| 5.  Edit:Preload            : showmap                         |");
                    System.out.println("| 6.  Edit:Preload            : savemap                         |");
                    System.out.println("| 7.  Edit:Preload            : validate                        |");
                    System.out.println("| 8.  playStartUpPhase        : loadMap -filename               |");
                    System.out.println("| 9.  playStartUpPhase        : gameplayer -add                 |");
                    System.out.println("| 10. playStartUpPhase        : gameplayer  -remove             |");
                    System.out.println("| 11. playStartUpPhase        : showMap                         |");
                    System.out.println("| 12. Reinforcement           : assignCountries                 |");
                    System.out.println("| 13. IssueOrder              : deploy -countryID -numArmies    |");
                    System.out.println("| 14. IssueOrder              : advance                         |");
                    System.out.println("| 15. IssueOrder              : block                           |");
                    System.out.println("| 16. IssueOrder              : bomb                            |");
                    System.out.println("| 17. IssueOrder              : airlift                         |");
                    System.out.println("| 18. IssueOrder              : dilpomacy                       |");
                    System.out.println("| 19. play startup phase      : end game                        |");
                    System.out.println("| 20. Any                     : next phase                      |");
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
                        gamePhase.editMap(p_Command);
                        break;
                    case 2:
                        gamePhase.editContinent(p_command);
                        break;
                    case 3:
                        gamePhase.editCountry(p_command);
                        break;
                    case 4:
                        gamePhase.editNeighbor(p_command);
                        break;
                    case 5:
                        gamePhase.showMap(p_filename);
                        break;
                    case 6:
                        gamePhase.saveMap(p_Command);
                        break;
                    case 7:
                        gamePhase.validate(p_filename);
                        break;
                    case 8:
                        gamePhase.loadMap();
                        break;
                    case 9:
                        gamePhase.addNewPlayer(p_Color);
                        break;
                    case 10:
                        gamePhase.removePlayer(p_player);
                        break;
                    case 11:
                        gamePhase.showMap(p_player);
                        break;
                    case 12:
                        gamePhase.assignCountries();
                        break;
                    case 13:
                        gamePhase.deployOrder(p_player, p_countryID, p_numberOfArmies);
                        break;
                    case 14:
                        gamePhase.advanceOrder(p_player, p_countryIDFrom, p_countryIDTo, p_numberOfArmies);
                        break;
                    case 15:
                        gamePhase.blockadeOrder(p_player, p_countryID);
                        break;
                    case 16:
                        gamePhase.bombOrder(p_player, p_countryID);
                        break;
                    case 17:
                        gamePhase.airliftOrder(p_player, p_countryIDFrom, p_countryIDTo, p_numberOfArmies);
                        break;
                    case 18:
                        gamePhase.dilpomacyOrder(p_player ,p_playerID);
                        break;
                    case 19:
                        gamePhase.endGame();
                        break;
                    case 20:
                        gamePhase.next();
                        break;

                    default:
                        System.out.println("this command does not exist");
                }
            } while (!(gamePhase instanceof End));
        } while (!mystart.equalsIgnoreCase("Exit"));

        l_Scanner.close();
    }
}
