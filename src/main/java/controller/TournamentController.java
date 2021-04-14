package controller;

import command.CommandValidator;
import model.GameData;
import model.LogEntryBuffer;
import model.gameelements.Country;
import model.gameelements.order.OrderFactory;
import model.gameelements.strategy.*;

import model.map.ConquestMapReader;
import model.map.MapEdit;
import model.map.MapFileAdapter;
import model.state.Edit;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;

/**
 * To implement the Tournament game Mode.
 */
public class TournamentController extends MainPlayController {

    /**
     * Num of players strategies
     */
    private List<String> d_ListOfPlayerStrategies = new ArrayList<>();
    /**
     * for how many game that players played
     */
    private int d_NumberOfGames;
    /**
     * set the max number of turns for each game.
     */
    private int d_MaxNumberOfTurns;
    /**
     * The list of map files.
     */
    private List<File> d_ListOfMapFiles = new ArrayList<>();
    /**
     * The map counter.
     */
    private int d_MapCounter = 0;
    /**
     * The current map.
     */
    private File d_CurrentMap;
    /**
     * The map file adapter.
     */
    private MapFileAdapter d_MapFileAdapter = new MapFileAdapter(new ConquestMapReader());
    /**
     * The Maps to revert.
     */
    private List<String> d_MapsToRevert = new ArrayList<>();

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
                                    List<String> l_TempMapList = new ArrayList<>();
                                    while (!l_CommandArr[i + 1].equalsIgnoreCase("-P") && l_TempMapList.size() <= 5) {
                                        l_TempMapList.add(l_CommandArr[i + 1]);
                                        i++;
                                    }
                                    stringMapPathInputProcess(l_TempMapList);
                                } else if (l_CommandArr[i].equalsIgnoreCase("-P")) {

                                    List<String> l_TempPlayerStraList = new ArrayList<>();
                                    while (!l_CommandArr[i + 1].equalsIgnoreCase("-G") && l_TempPlayerStraList.size() <= 4) {
                                        l_TempPlayerStraList.add(l_CommandArr[i + 1]);
                                        i++;
                                    }
                                    d_ListOfPlayerStrategies = l_TempPlayerStraList;
                                    // check number of Player strategies
                                    if (d_ListOfPlayerStrategies.size() >= 4 || d_ListOfPlayerStrategies.size() < 2) {
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
                    int l_MapCount = 0;
                    while (l_MapCount < d_ListOfMapFiles.size()) {

                        // load map
                        System.out.println("===== Preload map =====");
                        loadMap(getNextMap());
                        int l_GameCounter = 1;
                        while (l_GameCounter <= d_NumberOfGames) {
                            System.out.println("===== Game " + l_GameCounter + " =====");
                            //reset map
                            loadMap(d_CurrentMap);
                            setPlayers();
                            assignCountries();
                            showMap();

                            //get the round loop for each games.
                            int l_RoundCounter = 1;
                            while (l_RoundCounter <= d_MaxNumberOfTurns) {
                                System.out.println("===== Round " + l_RoundCounter + " =====");
                                issueOrder();
                                boolean l_CheckGameOver = executeOrder();
                                showMap();
                                // if check game end or only one player left is true, current game end,break
                                if (l_CheckGameOver || d_GameData.getPlayerList().size() <= 1) {
                                    System.out.println(d_GameData.getPlayerList().get(0).getColour() + " is Winner");
                                    System.out.println("===== Game " + l_GameCounter + " is Over =====");
                                    break;
                                }

                                l_RoundCounter++;
                                // if round count greater than max turns, draw the game,break
                                if (l_RoundCounter > d_MaxNumberOfTurns) {
                                    System.out.println("===== Maximum Round Reach =====");
                                    System.out.println("===== DRAW ===== No Winner =====\n");
                                    break;
                                }
                            }
                            l_GameCounter++;
                        }
                        l_MapCount++;
                    }

                    revertMaps();
                    d_MapsToRevert.clear();
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

//tournament -m 02.map 03.map -p aggressive benevolent -g 2 -d 10

    /**
     * get the number of player in th game then set strategy for each player.
     */
    public void setPlayers() {
        System.out.println("Num of Player: " + d_ListOfPlayerStrategies.size());
        //first add player to player list
        for (String l_PlayerStra : d_ListOfPlayerStrategies) {
            String l_AutoPlayerName = l_PlayerStra + " Player ";
            d_GameEngineController.addNewPlayer(l_AutoPlayerName);

            // give each player his strategy
            switch (l_PlayerStra.toLowerCase()) {
                case "aggressive":
                    d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName).setStrategy(new AggressivePattern(d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName), this.d_GameData));
                    break;
                case "benevolent":
                    d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName).setStrategy(new BenevolentPattern(d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName), this.d_GameData));
                    break;
                case "random":
                    d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName).setStrategy(new RandomPattern(d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName), this.d_GameData));
                    break;
                case "cheater":
                    d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName).setStrategy(new CheaterPattern(d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName), this.d_GameData));
                    break;
                default:
                    System.out.println("WARNING: we dont have such strategy pattern, '" + l_PlayerStra + "' set to aggressive strategy as default");
                    d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName).setStrategy(new AggressivePattern(d_GameEngineController.d_GameData.getPlayerByName(l_AutoPlayerName), this.d_GameData));
                    break;
            }
        }


        getDLogEntryBuffer().updateFile();

    }

    /**
     * assign countries for each player
     */
    public void assignCountries() {
        System.out.println("===== assign countries phase =====");
        // randomly assign countries for each player
        d_GameEngineController.autoAssignCountries();

        getDLogEntryBuffer().updateFile();

    }

    /**
     * deploy the order at order issue phase.
     */
    public void issueOrder() {
        System.out.println("===== issue order phase =====");
        d_GameEngineController.awardReinforcement();
        issueOrders();
    }

    /**
     * load game map, if the map are not found then track the map file from the top of stack.
     *
     * @param p_NewMap load the map for current game
     */
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
        System.out.println("the current map is :" + d_CurrentMap.getName());
        getDLogEntryBuffer().updateFile();

    }

    /**
     * execute each player`s deplyed order at execute phase.
     *
     * @return the return value represent if the order can be execute
     */
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
     *
     * @param p_MapPathList the p map path list
     */
    public void stringMapPathInputProcess(List<String> p_MapPathList) {
        for (String l_EachMap : p_MapPathList) {
            try {
                // check if transformation of map format is needed
                if (!MapEdit.isDomination(l_EachMap)) {
                    // convert format
                    d_MapFileAdapter.conquestFileToDominationFile(l_EachMap);
                    if (!d_MapsToRevert.contains(l_EachMap)) {
                        d_MapsToRevert.add(l_EachMap);
                    }
                }
                //preprocess path
                File l_NewMap = new File(d_GameEngineController.getSimpleFilePath(l_EachMap));
                // use map file getter to get the map and add to the map list
                d_ListOfMapFiles.add(l_NewMap);
            } catch (IOException e) {
                System.out.println("Error: Your input [" + l_EachMap + "] map file not found ");
                e.printStackTrace();
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
     *
     * @return the next map
     */
    public File getNextMap() {
        // check whether list is empty or counter out of bound
        if (!d_ListOfMapFiles.isEmpty() && d_MapCounter < d_ListOfMapFiles.size() && d_MapCounter <= d_ListOfMapFiles.size()) {
            // get obj index next one
            d_CurrentMap = d_ListOfMapFiles.get(d_MapCounter);
            d_MapCounter++;
        } else {
            System.out.println("Error: Map List Empty or out of bound");
            // if list out of bound, iterate back to first map in list
            if (!d_ListOfMapFiles.isEmpty()) {
                d_CurrentMap = d_ListOfMapFiles.get(0);
                // reset counter id 0
                d_MapCounter = 0;
            }
        }

        return d_CurrentMap;
    }

    /**
     * Revert maps back to conquest format if needed.
     */
    public void revertMaps() {
        for (String l_MapFileName :
                d_MapsToRevert) {
            try {
                d_MapFileAdapter.dominationFileToConquestFile(l_MapFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
