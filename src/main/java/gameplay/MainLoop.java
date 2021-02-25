package gameplay;

import gameelements.Country;
import gameelements.Player;
import map.MapCheck;
import map.MapEdit;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * The Main game loop program to execute ecah of the game phase.
 *
 * @author hanjiaming
 * @version 1.0.0
 */

public class MainLoop {
    private static String DIRNAME = "domination";
    public static GameData d_GameData;
    private static GameEngine d_GameEngine;
    public File d_MapFile;
    private static Map<String, Country> d_countryList;
    private static String OPTFILE = "";

    /**
     * Constructor
     * the main game loop that to control game phases.
     *
     * @param p_FilePath the map file
     */
    public MainLoop(String p_FilePath) {
        this.d_MapFile = new File(p_FilePath);
        this.d_GameData = new GameData(d_MapFile);
        this.d_GameEngine = new GameEngine(d_GameData);

    }

    /**
     * Call functions from MapEdit class to edit or creat a map for the game, and the new create map is showing up at domination file.
     *
     * @throws IOException if files are not found
     */
    public void mainLoopMapEdit() throws Exception {
        Scanner l_Scanner = new Scanner(System.in);
        System.out.println("You are in the map editor.");
        String l_Command = "";

        for (;;) {
            System.out.println("Please type in your command:");
            l_Command = l_Scanner.nextLine();
            // call methods according to user's commands
            // call the editmap method
            if (l_Command.startsWith("editmap ")) {
                MapEdit.editMap(l_Command);
                // call the editcontinent method
            } else if (l_Command.startsWith("editcontinent ")) {
                MapEdit.editContinent(l_Command);
                // call the editcountry method
            } else if (l_Command.startsWith("editcountry ")) {
                MapEdit.editCountry(l_Command);
                // call the editneighbor method
            } else if (l_Command.startsWith("editneighbor ")) {
                MapEdit.editNeighbor(l_Command);
                // call the showmap method
            } else if ("showmap".equals(l_Command)) {
                File l_File = MapEdit.getFile(OPTFILE);
                if(l_File.exists()) {
                    MapEdit.showMap(l_File);
                }

                System.out.println("\\n Map graph as follow: \n");

                map.MapGraph.printTable(OPTFILE);
                // call the savemap method
            } else if (l_Command.startsWith("savemap ")) {
                MapEdit.saveMap(l_Command);
                // call the validatemap method
            } else if (l_Command.startsWith("validatemap")) {
                File l_File = MapEdit.getFile(OPTFILE);
                if(l_File.exists()) {
                    // check map validation
                    map.MapCheck.check(l_File);
                }
            } else {
                System.out.println("invalid command");
            }
        }
    }

    /**
     * call function from GameEngine class, and according to the game rule to execute the game.
     */
    public void mainGamePhaseLoop() {
        Scanner l_scanner = new Scanner(System.in);

        boolean l_isTrue = true;
        while (l_isTrue) {
            System.out.println("do you want to add or remove player (Number of player limit is 2 to 5)? (y/n) ");
            String l_askUser = l_scanner.next();

            if (l_askUser.equalsIgnoreCase("y")) {
                if (d_GameData.getPlayerList().size() < 5) {
                    d_GameEngine.gamePlayerCommand();
                }
                // since the number of player range is 2 to 5. no more player can be add in.
                else if (d_GameData.getPlayerList().size() > 5) {
                    System.out.println("number of player out of limit ");
                    l_isTrue = false;
                }
            } else if (l_askUser.equalsIgnoreCase("n")) {
                // since the number of player range is 2 to 5. no more player can be remove in.
                if (d_GameData.getPlayerList().size() < 2) {
                    System.out.println("number of player is not enough, please add more ");
                } else {
                    System.out.println("All player have already set ");
                    l_isTrue = false;
                }
            } else {
                System.out.println("Invalid command, please type (y/n): ");
            }
        }

        // randomly assign countries for each player
        d_GameEngine.assignCountries();

        //start up the game, according to the game rules to start game engine, and determine if any players are eliminated at the end of each round.
        int l_currentReinForcement = 5;
        while (d_GameData.getCurrentPhase() != GamePhase.END_OF_GAME) {
            int l_TempReforcementArmy;

            // Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
            this.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
            System.out.println(d_GameData.getCurrentPhase());

            for (Player l_Player : this.d_GameEngine.d_GameData.getPlayerList()) {
                l_Player.setReinforcementArmies(l_currentReinForcement);
                System.out.println(l_currentReinForcement + " Reinforcement Armies are assigned to " + " Player [" + l_Player.getColour() + "]  ");

                for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
                    System.out.println("Controling countries: " + l_CountryEntry.getKey());
                }
            }

            System.out.println("---------REINFORCEMENT PHASE COMPLETE-----------\n");


            // Issue order phase,Loop through all players, until all players finish issuing the instructions, and save the order in player`s order list.
            for (Player l_Player : this.d_GameEngine.d_GameData.getPlayerList()) {
                this.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
                System.out.println(this.d_GameEngine.d_GameData.getCurrentPhase());


                l_TempReforcementArmy = l_Player.getReinforcementArmies();
                while (l_TempReforcementArmy > 0) {
                    System.out.println("==== Now Player [" + l_Player.getColour() + "]'s turn to issue order ====");
                    System.out.println(" Player [" + l_Player.getColour() + "] have " + l_TempReforcementArmy
                            + " Reinforcement Armies.");
                    l_Player.issueOrder();
                    l_TempReforcementArmy -= l_Player.getLastOrderFromQueue().getOrderInfo().getNumberOfArmy();
                }
            }
            //execute orders phase,  execute player`s order, assigning a number of armies to move towards the target country.
            this.d_GameEngine.phaseProcess();

            //game phase turning to END OF GAME.
            d_GameEngine.d_GameData.setCurrentPhase(GamePhase.END_OF_GAME);
            System.out.println(d_GameEngine.d_GameData.getCurrentPhase());
        }
        l_scanner.close();
    }

    /**
     * According to user input to check in which model that user are going to get in.
     *
     * @throws IOException if files are not found
     */
    public void MainLogic() throws Exception {
        Scanner l_Scanner = new Scanner(System.in);
        MainLoop l_MainLoop;

        int l_CheckState = 1;
        while (l_CheckState != 0) {
            System.out.println("Welcome to warzone! ");
            System.out.println("Do you want to edit map or play game (Edit/Play/Exit)");
            System.out.println("( Edit for edit map / Play for play the game / Exit for exit the game )");
            String l_GameOptionCommand = l_Scanner.nextLine();
            // input edit to get into map editing model.
            if (l_GameOptionCommand.equalsIgnoreCase("Edit")) {
                map.MapEdit.mapEditLoop();
            }

            // input edit to get into map edit model playing game model.
            else if (l_GameOptionCommand.equalsIgnoreCase("Play")) {
//                System.out.println("please enter your file path to load game map: ");
//                String l_TempLoadmapFilePath = l_Scanner.next();

                // mainloop for game play
                d_GameData.setCurrentPhase(GamePhase.STARTUP);
                l_MainLoop = new MainLoop(d_GameEngine.getMapFilePath());
                d_GameData.loadMap();
                l_MainLoop.mainGamePhaseLoop();
                System.out.println("------The End of Game------ ");
                break;
            }
            // input exit to close the game.
            else if (l_GameOptionCommand.equalsIgnoreCase("Exit")) {
                l_CheckState = 0;
                System.out.println("Exiting Warzone Game see you next time!");
            }
            // Error handling for user input
            else
                System.out.println("Invalid input, try again !");
        }
        l_Scanner.close();
    }

    /**
     * main method， Show each game phase from GameEngine, and run the game according to the game rules
     *
     * @param args To get parameters from console
     * @throws IOException if file does not exist
     */
    public static void main(String[] args) throws Exception {
        String file = "test_02.map";
        MainLoop mainLoop = new MainLoop(file);
        mainLoop.MainLogic();
    }

}