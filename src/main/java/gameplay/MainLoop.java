package gameplay;

import command.CommandValidator;
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
    private static String d_DirName = "domination";
    public static GameData d_GameData;
    private static GameEngine d_GameEngine;
//    private static MapEdit d_MapEdit;
    public Player d_Player;
    public CommandValidator d_commandValidator;
    public String d_colour;
    public File d_MapFile;
    private static Map<String, Country> d_countryList;
    private static String OPTFILE = "";

    /**
     * Constructor
     * the main game loop that to control game phases.
     * @param p_FilePath the map file
     */
    public MainLoop(String p_FilePath) {
        this.d_MapFile = new File(p_FilePath);
        this.d_Player = new Player(d_colour, d_commandValidator);
        this.d_GameData = new GameData(d_MapFile);
        this.d_GameEngine = new GameEngine(d_GameData);
       // this.d_MapEdit = new MapEdit();
    }

    /**
     * Call functions from MapEdit class to edit or creat a map for the game, and the new create map is showing up at domination file.
     *
     * @throws IOException  if files are not found
     */
    public void mainLoopMapEdit() throws IOException {
        Scanner l_scanner = new Scanner(System.in);
        System.out.println("You are in the map editor.");
        String l_Command = "";
        for (; ; ) {
            System.out.println("Please type in your command:");
            l_Command = l_scanner.nextLine();
            if (l_Command.startsWith("editmap ")) {
                MapEdit.editMap(l_Command);
            } else if (l_Command.startsWith("editcontinent ")) {
                MapEdit.editContinent(l_Command);
            } else if (l_Command.startsWith("editcountry ")) {
                MapEdit.editCountry(l_Command);
            } else if (l_Command.startsWith("editneighbor ")) {
                MapEdit.editNeighbor(l_Command);
            } else if ("showmap".equals(l_Command)) {
                File l_File = MapEdit.getFile(OPTFILE);
                if (l_File.exists()) {
                    MapEdit.showMap(l_File);
                }
            } else if (l_Command.startsWith("savemap ")) {
                MapEdit.saveMap(l_Command);
            } else if (l_Command.startsWith("validatemap")) {
                File l_File = MapEdit.getFile(OPTFILE);
                if (l_File.exists()) {
                    MapCheck.check(l_File);
                }
            }
            else if(l_Command.startsWith("exit")){
                break;
            }
            else {
                System.out.println("invalid command");
            }


//            System.out.println("any other option? (y/n)");
//            String p_CheckOption = l_scanner.next();
//            if (p_CheckOption.equalsIgnoreCase("n")) {
//                break;
//            }
//            if (p_CheckOption.equalsIgnoreCase("y")) {
//                continue;
//            }
//            l_scanner.close();
        }
    }

    /**
     * call function from GameEngine class, and according to the game rule to execute the game.
     */
    public void mainGamePhaseLoop() {
        Scanner l_scanner= new Scanner(System.in);

        boolean l_isTrue = true;
        while(l_isTrue ) {
            System.out.println("do you want to add or remove player? (y/n) ");
            String l_askUser = l_scanner.next();
             if(l_askUser.equalsIgnoreCase("y")){
                d_GameEngine.gamePlayerCommand();
            }
             else if(l_askUser.equalsIgnoreCase("n")){
                 l_isTrue =false;
             }
             else{
                 System.out.println("Invalid command, please type (y/n): ");
             }
        }
        // randomly assign countries
        d_GameEngine.assignCountries();

        /**
         * start up the game, according to the game rules to start game engine, and determine if any players are eliminated at the end of each round.
         */
//        int l_countTotalPlayer = this.d_GameEngine.d_GameData.getPlayerList().size();
        int l_currentReinForcement = 5;
        while (d_GameData.getCurrentPhase() != GamePhase.END_OF_GAME) {
            int l_TempReforcementArmy;
            /**
             * Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
             */
                this.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
                System.out.println(d_GameData.getCurrentPhase());

                for (Player l_Player :  this.d_GameEngine.d_GameData.getPlayerList()) {
                    l_Player.setReinforcementArmies(l_currentReinForcement);
                    System.out.println(l_currentReinForcement  +" Reinforcement Armies are assigned to "+ " Player ["+l_Player.getColour()+"]  ");

                    for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()){
                        System.out.println("Controling countries: "+ l_CountryEntry.getKey());
                    }
                }

                System.out.println("---------REINFORCEMENT PHASE COMPLETE-----------\n");


                /**
                 * Issue order phase,Loop through all players, until all players finish issuing the instructions, and save the order in player`s order list.
                 */
            for (Player l_Player :  this.d_GameEngine.d_GameData.getPlayerList()) {
                this.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
                System.out.println( this.d_GameEngine.d_GameData.getCurrentPhase());

                l_TempReforcementArmy=l_Player.getReinforcementArmies();
                while (l_TempReforcementArmy > 0) {
                    System.out.println("==== Now Player ["+l_Player.getColour()+"]'s turn to issue order ====");
                    System.out.println(" Player ["+l_Player.getColour()+"] have "+l_TempReforcementArmy
                            +" Reinforcement Armies.");
                    l_Player.issueOrder();
                    l_TempReforcementArmy -= l_Player.getLastOrderFromQueue().getOrderInfo().getNumberOfArmy();
                }
            }

            /**
             *execute orders phase,  execute player`s order, assigning a number of armies to move towards the target country.
             */
            this.d_GameEngine.phaseProcess();


            d_GameEngine.d_GameData.setCurrentPhase(GamePhase.END_OF_GAME);
            System.out.println( d_GameEngine.d_GameData.getCurrentPhase());
        }
        l_scanner.close();
    }

    /**
     *
     * @throws IOException  if files are not found
     */
    public void MainLogic() throws IOException {
        Scanner l_scanner = new Scanner(System.in);
        MainLoop l_MainLoop;

        int l_checkState = 1;
        while(l_checkState != 0) {
            System.out.println("Welcome to warzone! ");
            System.out.println("Do you want to edit map or play game (Edit/Play/Exit)");
            System.out.println("( Edit for edit map / Play for play the game / Exit for exit the game )");
            String l_GameOptionCommand = l_scanner.nextLine();
            // input edit to get into map editing model.
            if(l_GameOptionCommand.equalsIgnoreCase("Edit")) {
                mainLoopMapEdit();
            }

            // input edit to get into map edit model playing game model.
            else if(l_GameOptionCommand.equalsIgnoreCase("Play")) {
                System.out.println("please enter your file path to load game map: ");
                String l_TempLoadmapFilePath = l_scanner.next();
                // mainloop for game play
                l_MainLoop = new MainLoop(l_TempLoadmapFilePath);
                d_GameData.loadMap();
                d_GameData.setCurrentPhase(GamePhase.STARTUP);
                l_MainLoop.mainGamePhaseLoop();
                System.out.println("------The End of Game------ ");
                break;
            }
            // input exit to close the game.
            else if(l_GameOptionCommand.equalsIgnoreCase("Exit")){
                l_checkState = 0;
                System.out.println("Exiting Warzone Game see you next time!");
            }
            // Error handolling for user input
            else
                System.out.println("Invalid input, try again !");
            }
            l_scanner.close();
    }

    /**
     * main method， Show each game phase from GameEngine, and run the game according to the game rules
     *
     * @param args To get parameters from console
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String file = "test_02.map";
        MainLoop mainLoop = new MainLoop(file );
        mainLoop.MainLogic();
    }

}
