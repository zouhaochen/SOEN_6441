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

/** The class is to run main activity for the game
 * @author hanjiaming
 * @version  1.0.0
 */

public class MainLoop {
    public  static GameData d_GameData;
    private static GameEngine d_GameEngine;
    private static MapEdit d_MapEdit;
    public  GamePhase d_GamePhase;
    public  Player d_Player;
    public  CommandValidator d_commandValidator;
    public  String d_colour;
    public  File d_MapFile;
    private static Map<String, Country> d_countryList;
    private static String OPTFILE = "";

    /**
     * Constructor
     * @param p_FilePath
     */
    public MainLoop(String p_FilePath){
        this.d_MapFile=new File(p_FilePath);
        this.d_Player = new Player(d_colour, d_commandValidator);
        this.d_GameData = new GameData(d_MapFile);
        this.d_GameEngine = new GameEngine(d_GameData);
        this.d_MapEdit =new MapEdit();

    }

    /**
     * Call functions from MapEdit class to edit or creat a map for the game, and the new create map is showing up at domination file.
     * @throws IOException
     */
    public void mainLoopMapEdit() throws IOException {
        Scanner sc = new Scanner(System.in);


        this.d_GamePhase=GamePhase.MAP_EDIT;
        System.out.println("do you want to create and edit a new map? (y/n)");
        String p_CheckNewGame = sc.nextLine();


        if (p_CheckNewGame.equalsIgnoreCase("y")) {
            System.out.println("You are in the map editor.");
            String l_Command = "";
            for (; ; ) {
                System.out.println("Please type in your command:");
                l_Command = sc.nextLine();
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
                String p_CheckOption = sc.next();
                if (p_CheckOption.equalsIgnoreCase("n")) {
                    break;
                }
                if (p_CheckOption.equalsIgnoreCase("y")) {
                    continue;
                }
            }

        } else if (p_CheckNewGame.equalsIgnoreCase("n")) {
            System.out.println(" End of the Game ");
        }
    }
         //   System.out.println(" do you want to use this map? ");

    /**
     * call function from GameEngine class, and according to the game rule to execute the game.
     */
    public void mainLoopGameEngine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter for how many player are going to play: ");
        int p_playerNum = sc.nextInt();
        d_GameEngine.d_GameData.setTotalPlayer(p_playerNum);

        for (int i = 0; i < p_playerNum; i++) {
            System.out.println("Please enter the name for each of player: ");
            String p_playerName = sc.next();
            d_GameEngine.addNewPlayer(p_playerName);
        }
        //获取地图信息, 包括所有的country和country的领主信息, 根据规则和player拥有的领土信息分配军队数量




        //回合前检查玩家是否占领了大陆上足够多的国家来在每回合额外分配兵力
        int currentReinForcement = 5;
       // currentReinForcement += bonusArmies;

        /**
         * start up the game, according to the game rules to start game engine, and determine if any players are eliminated at the end of each round.
         */
        //this.d_GamePhase=GamePhase.REINFORCEMENT;
        d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
        System.out.println(d_GameData.getCurrentPhase());
        int l_countTotalPlayer = d_GameEngine.d_GameData.getTotalPlayer();

        while (this.d_GamePhase.getGamePhaseAsInt() != 5) {

            /**
             * Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
             */
            for (Player l_Player : d_GameData.getPlayerList()) {
                l_Player.setReinforcementArmies(currentReinForcement);

            /**
             * Issue order phase,Loop through all players, until all players finish issuing the instructions, and save the order in player`s order list.
             */
                //this.d_GamePhase=GamePhase.ISSUE_ORDER;
                d_GameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
                System.out.println(d_GameData.getCurrentPhase());
                while (l_Player.getReinforcementArmies() > 0) {
                    l_Player.issueOrder();
                }
            }

            /**
             *execute orders phase,  execute player`s order, assigning a number of armies to move towards the target country.
             */
            for (Player l_Player : d_GameData.getPlayerList()) {
                int p_orderList_length = l_Player.getOrdersInCurrentTurn().size();

                for (int i = 0; i < p_orderList_length; i++) {
                    l_Player.nextOrder().execute();
                }
                d_GameEngine.showMap(d_Player);
            }

            if( l_countTotalPlayer<2) {
              //  this.d_GamePhase=GamePhase.END_OF_GAME;
                d_GameData.setCurrentPhase(GamePhase.END_OF_GAME);
                System.out.println(d_GameData.getCurrentPhase());

            }
            else
                continue;
        }
        sc.close();
    }

    /**
     * main method， Show each game phase for player, and run the game according to the rules of the game
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        MainLoop l_MainLoop;

        System.out.println("Welcome to warzone! ");
        System.out.println("Do you want to edit map or play game (1/2)");
        System.out.println("(1 for edit map / 2 for play map)");
        int l_Command = sc.nextInt();
        switch(l_Command ){
            // map edit case
            case 1:
                System.out.println("do you want to edit current map or create a new map (1/2): ");
                System.out.println("(1 for edit current map / 2 for create a new map)");
                int l_Command1 = sc.nextInt();
                     if(l_Command1 == 1){
                         System.out.println("please enter your file path:");
                         String l_TempFilePath3=sc.next();
                         l_MainLoop = new MainLoop(l_TempFilePath3);
                         l_MainLoop.mainLoopMapEdit();
                         break;
                     }

                     else if(l_Command1 == 2){
                         System.out.println("please enter your file path:");
                            String l_TempFilePath4=sc.next();
                            l_MainLoop = new MainLoop(l_TempFilePath4);
                            l_MainLoop.mainLoopMapEdit();
                            break;
                     }
                System.out.println("invalid input! ");
             break;

             // game play case
            case 2:
                System.out.println("please enter your file path:");
                String l_TempFilePath2=sc.next();
                // mainloop for game play
                l_MainLoop = new MainLoop(l_TempFilePath2);
                l_MainLoop.mainLoopGameEngine();
        }

        sc.close();
        System.out.println("------The End of Game------ ");
        }

    }
