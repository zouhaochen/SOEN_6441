package controller;

import command.CommandValidator;
import model.GameData;
import model.LogEntryBuffer;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.OrderFactory;
import model.gameelements.strategy.*;
import model.map.DominationMapReader;
import model.state.Edit;
import model.state.End;
import model.state.play.LoadMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

/**
 * To implement the Tournament game Mode.
 */
public class TournamentController extends MainPlayController {

    /**
     * Num of players strategies
     */
    private int d_NumOfPlayerStrategies;
    /**
     * for how many game that players played
     */
    private int d_NumberOfGames;
    /**
     * set the max number of turns for each game.
     */
    private int d_MaxNumberOfTurns;

    /**
     * Num of map to play in tournament
     */
    private int d_NumOfMap;

    private List<File> d_ListOfMapFiles = new ArrayList<>();
    private int d_MapCounter = 0;
    private File d_CurrentMap;

    /**
     * game logger
     */
    private LogEntryBuffer d_LogEntryBuffer = new LogEntryBuffer();

    @Override
    public void Start() {
        System.out.println("Welcome to tournament mode game! ");
        d_LogEntryBuffer.start();
        String l_Command;
        String[] l_CommandArr;
        Scanner l_Scanner = new Scanner(System.in);
        CommandValidator.setGameData(d_GameData);
        while (true) {


            System.out.println("Do you want to edit map or play game? (Edit/Play/Back)");
            System.out.println("( Edit for edit map / Play for play the game / Back for return to main menu )");


            mystart = l_Scanner.nextLine();
            switch (mystart.toLowerCase()) {
                case "edit":
                    // Set the state to Preload
                    setPhase(new Edit(this));
                    break;
                case "play":
                    // ask user to insert map to map list
                    stringMapPathInputProcess();

                    // tournament command validator
                    while (true) {
                        System.out.println("Please enter tournament command: tournament -M -P -G -D");
                        l_Command = l_Scanner.nextLine();
                        l_CommandArr = l_Command.split(" ");
                        // check header and size of command , command should complete when input
                        if (!l_CommandArr[0].equalsIgnoreCase("tournament") && l_CommandArr.length == 8) {
                            System.out.println("ERROR: wrong command format, should start with 'tournament'");
                            continue;
                        }
                        try {
                            for (int i = 1; i < l_CommandArr.length; i++) {
                                if (l_CommandArr[i].equalsIgnoreCase("-M")) {
                                    // pass in number of map
                                    d_NumOfMap = Integer.parseInt(l_CommandArr[i + 1]);
                                } else if (l_CommandArr[i].equalsIgnoreCase("-P")) {
                                    // pass in number of Player strategies
                                    d_NumOfPlayerStrategies = Integer.parseInt(l_CommandArr[i + 1]);
                                    if (d_NumOfPlayerStrategies >= 4 || d_NumOfPlayerStrategies < 2) {
                                        System.out.println("ERROR: player number should between 2-4");
                                        continue;
                                    }
                                } else if (l_CommandArr[i].equalsIgnoreCase("-G")) {
                                    // pass in number of Player strategies
                                    d_NumberOfGames = Integer.parseInt(l_CommandArr[i + 1]);
                                    if (d_NumberOfGames < 1 || d_NumberOfGames > 5) {
                                        System.out.println("ERROR: Game play should between 1-5");
                                        continue;
                                    }
                                } else if (l_CommandArr[i].equalsIgnoreCase("-D")) {
                                    // pass in number of Player strategies
                                    d_MaxNumberOfTurns = Integer.parseInt(l_CommandArr[i + 1]);
                                    if (d_MaxNumberOfTurns < 10 || d_MaxNumberOfTurns > 50) {
                                        System.out.println("ERROR: min and max round should between 10-50");
                                        continue;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR: tournament command not completed, try again\n");
                        }
                        break;
                    }
                    // main play loop
                    int l_GameCounter = 0;
                    while (l_GameCounter < d_NumberOfGames) {
                        loadMap(getNextMap());
                        setPlayers();
                        assignCountries();
                        int l_RoundCounter = 0;
                        while (l_RoundCounter < d_MaxNumberOfTurns) {
                            issueOrder();
                            if (executeOrder() || d_GameData.getPlayerList().size() == 1) {
                                break;
                            }

                            l_RoundCounter++;
                            if (l_RoundCounter > d_MaxNumberOfTurns) {
                                System.out.println("===== Maximum Round Reach =====");
                                System.out.println("===== DRAW ===== No Winner =====");
                                break;
                            }
                        }
                        l_GameCounter++;
                    }

                    break;
                case "back":
                    System.out.println("return to main menu");
                    break;
                default:
                    System.out.println("wrong input, choose between Edit, Play or Back");
                    continue;
            }
            break;
        }




    }


    public void setPlayers() {
        System.out.println("Num of Player: " + d_NumOfPlayerStrategies);
        Random l_Random = new Random();
        int l_count = 0;
        while (l_count < d_NumOfPlayerStrategies) {
            String l_AutoPlayerName = new String(" Player " + (l_count + 1));
            d_GameEngineController.addNewPlayer(l_AutoPlayerName);
            l_count++;
        }
        for (Player l_each : d_GameData.getPlayerList()) {

            int l_StrategyRandom = l_Random.nextInt(4) + 1;
            switch (l_StrategyRandom) {
                case 1:
                    l_each.setStrategy(new AggressivePattern(l_each, this.d_GameData));
                    break;
                case 2:
                    l_each.setStrategy(new BenevolentPattern(l_each, this.d_GameData));
                    break;
                case 3:
                    l_each.setStrategy(new RandomPattern(l_each, this.d_GameData));
                    break;
                case 4:
                    l_each.setStrategy(new CheaterPattern(l_each, this.d_GameData));
                    break;
                default:
                    System.out.println("WARNING: we dont have such strategy pattern, set to random strategy as default");
                    l_each.setStrategy(new RandomPattern(l_each, this.d_GameData));
                    break;
            }
        }
        getDLogEntryBuffer().updateFile();

    }

    public void assignCountries() {
        System.out.println("===== assign countries phase =====");
        // randomly assign countries for each player
        d_GameEngineController.autoAssignCountries();

        getDLogEntryBuffer().updateFile();

    }

    public void issueOrder() {
        System.out.println("===== issue order phase =====");
        d_GameEngineController.awardReinforcement();
        issueOrders();
    }

    public void loadMap(File p_NewMap) {

        System.out.println("===== load map phase =====");
        d_GameData = new GameData(p_NewMap);
        d_GameEngineController = new GameEngineController(d_GameData);
        OrderFactory.setGameData(d_GameData);
        try {
            d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getDLogEntryBuffer().updateFile();

    }

    public Boolean executeOrder() {
        System.out.println("===== execute order phase =====");
        executeAllOrders();

        // if there are any dangling countries, assign them to NEUTRAL player
        List<Country> l_DanglingCountries = getDanglingCountries();
        if (!l_DanglingCountries.isEmpty()) {
            assignDanglingCountriesToNeutralPlayer(l_DanglingCountries);
        }

        resetAttackableState();
        getDLogEntryBuffer().updateFile();

        if (d_GameEngineController.checkGameIsOver()) {
            // game ends
            return true;

        } else {
            // go back to issueOrder phase for current build
            return false;
        }
    }

    /**
     * get user map path list string input and store as real map file in tournament game map list
     */
    public void stringMapPathInputProcess() {
        System.out.println("===== please insert map to map list =====");
        // local key-in scanner
        Scanner l_Scanner = new Scanner(System.in);

        while (d_ListOfMapFiles.size() <= 5) {
            try {
                //preprocess path
                File l_NewMap = new File(d_GameEngineController.getSimpleFilePath());

                // use map file getter to get the map and add to the map list
                d_ListOfMapFiles.add(l_NewMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("===== Insert Map to list, 'n' to stop insert map, 'y' to keep insert =====");
            String keyin = l_Scanner.nextLine();
            if (keyin.equalsIgnoreCase("n")) {
                break;
            }
        }
        System.out.println("Successfully insert [" + d_ListOfMapFiles.size() + "] Map in game");
        showCurrentMapList();

    }

    /**
     * function show the tournament map list
     */
    public void showCurrentMapList() {
        System.out.println("Notice: Tournament Map List: ");
        for (File l_EachMapFile : d_ListOfMapFiles) {
            System.out.print(l_EachMapFile.getName() + " ");
        }
        System.out.println("");
    }


    /**
     * get next map to play in map list
     */
    public File getNextMap() {
        // check whether list is empty or counter out of bound
        if (!d_ListOfMapFiles.isEmpty() && d_MapCounter < d_ListOfMapFiles.size() && d_MapCounter <= d_NumOfMap) {
            // get obj index next one
            d_CurrentMap = d_ListOfMapFiles.get(d_MapCounter);
            d_MapCounter++;
        } else {
            System.out.println("Error: Map List Empty or out of bound");
            // if just out of bound iterate back to first map in list
            if (!d_ListOfMapFiles.isEmpty()) {
                d_CurrentMap = d_ListOfMapFiles.get(0);
                // reset counter id 0
                d_MapCounter = 0;
            }
        }
        return d_CurrentMap;
    }
}
