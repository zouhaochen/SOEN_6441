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
 * The class is to run main activity for the game
 *
 * @author hanjiaming
 * @version 1.0.0
 */

public class MainLoop {
    public static GameData d_GameData;
    private static GameEngine d_GameEngine;
    private static MapEdit d_MapEdit;
    public GamePhase d_GamePhase;
    public Player d_Player;
    public CommandValidator d_commandValidator;
    public String d_colour;
    public File d_MapFile;
    private static Map<String, Country> d_countryList;
    private static String OPTFILE = "";

    /**
     * Constructor
     *
     * @param p_FilePath
     */
    public MainLoop(String p_FilePath) {
        this.d_MapFile = new File(p_FilePath);
        this.d_Player = new Player(d_colour, d_commandValidator);
        this.d_GameData = new GameData(d_MapFile);
        this.d_GameEngine = new GameEngine(d_GameData);
        this.d_MapEdit = new MapEdit();

    }

    /**
     * Call functions from MapEdit class to edit or creat a map for the game, and the new create map is showing up at domination file.
     *
     * @throws IOException
     */
    public void mainLoopMapEdit() throws IOException {
        Scanner l_scanner = new Scanner(System.in);
        System.out.println("You are in the map editor.");
        String l_Command = "";
        for (; ; ) {
            System.out.println("Please type in your command:");
            l_Command = l_scanner.nextLine();
            if (l_Command.startsWith("editmap ")) {
                d_MapEdit.editMap(l_Command);
            } else if (l_Command.startsWith("editcontinent ")) {
                d_MapEdit.editContinent(l_Command);
            } else if (l_Command.startsWith("editcountry ")) {
                d_MapEdit.editCountry(l_Command);
            } else if (l_Command.startsWith("editneighbor ")) {
                d_MapEdit.editNeighbor(l_Command);
            } else if ("showmap".equals(l_Command)) {
                File l_File = d_MapEdit.getFile(OPTFILE);
                if (l_File.exists()) {
                    d_MapEdit.showMap(l_File);
                }
            } else if (l_Command.startsWith("savemap ")) {
                d_MapEdit.saveMap(l_Command);
            } else if (l_Command.startsWith("validatemap")) {
                File l_File = d_MapEdit.getFile(OPTFILE);
                if (l_File.exists()) {
                    MapCheck.check(l_File);
                }
            } else {
                System.out.println("invalid command");
            }


            System.out.println("any other option? (y/n)");
            String p_CheckOption = l_scanner.next();
            if (p_CheckOption.equalsIgnoreCase("n")) {
                break;
            }
            if (p_CheckOption.equalsIgnoreCase("y")) {
                continue;
            }
            l_scanner.close();
        }


    }
    //   System.out.println(" do you want to use this map? ");

    /**
     * call function from GameEngine class, and according to the game rule to execute the game.
     */
    public void mainLoopGameEngine() {
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


        //回合前检查玩家是否占领了大陆上足够多的国家来在每回合额外分配兵力
        int l_currentReinForcement = 5;
        // currentReinForcement += bonusArmies;

        /**
         * start up the game, according to the game rules to start game engine, and determine if any players are eliminated at the end of each round.
         */
//        int l_countTotalPlayer = this.d_GameEngine.d_GameData.getPlayerList().size();

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
            System.out.println(d_GameEngine.d_GameData.getPlayerList().get(0).getOrdersInCurrentTurn());
            this.d_GameEngine.phaseProcess();

                d_GameEngine.d_GameData.setCurrentPhase(GamePhase.END_OF_GAME);
                System.out.println( d_GameEngine.d_GameData.getCurrentPhase());
        }
        l_scanner.close();
    }


    public static void MainLogic() throws IOException {
        Scanner l_scanner = new Scanner(System.in);
        MainLoop l_MainLoop;

        System.out.println("Welcome to warzone! ");
        System.out.println("Do you want to edit map or play game (1/2)");
        System.out.println("(1 for edit map / 2 for play the game)");
        int l_Command = l_scanner.nextInt();
        switch (l_Command) {
            // map edit case
            case 1:
                System.out.println("do you want to edit current map or create a new map (1/2): ");
                System.out.println("(1 for edit current map / 2 for create a new map)");
                int l_Command1 = l_scanner.nextInt();
                if (l_Command1 == 1) {
                    System.out.println("please enter the file path of the map you want to edit :");
                    String l_TempFilePath3 = l_scanner.next();
                    l_MainLoop = new MainLoop(l_TempFilePath3);
                    l_MainLoop.mainLoopMapEdit();
                    System.out.println("------The End of Map Editing------ ");
                    break;

                } else if (l_Command1 == 2) {
                    System.out.println("please enter a file path for new map:");
                    String l_TempFilePath4 = l_scanner.next();
                    l_MainLoop = new MainLoop(l_TempFilePath4);
                    l_MainLoop.mainLoopMapEdit();
                    System.out.println("------The End of Map Editing------ ");
                    break;
                }
                System.out.println("invalid input! ");
                break;

            // game play case
            case 2:
                System.out.println("please enter your file path to load game map: ");
                String l_TempFilePath2 = l_scanner.next();
                // mainloop for game play
                l_MainLoop = new MainLoop(l_TempFilePath2);
                d_GameData.loadMap();
                d_GameData.setCurrentPhase(GamePhase.STARTUP);
                l_MainLoop.mainLoopGameEngine();
                System.out.println("------The End of Game------ ");
        }

        l_scanner.close();

    }

    /**
     * main method， Show each game phase for player, and run the game according to the rules of the game
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MainLogic();
    }

}
