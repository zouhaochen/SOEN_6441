package controller;

import command.CommandValidator;
import model.GameData;
import model.LogEntryBuffer;
import model.Observable;
import model.gameelements.Continent;
import model.gameelements.Country;
import model.gameelements.Player;
import model.state.Edit;
import model.state.End;
import model.state.Phase;
import model.state.play.LoadMap;

import java.io.File;
import java.util.Scanner;

/**
 * Context of the State pattern.
 * It contains a State object.
 */
public class MainPlayController extends Observable {
    /**
     * State object of the MainLoop
     */
    public Phase gamePhase;

    /**
     * model.map file that use to load represent game model.map.
     */
    public File d_MapFile = new File(".//domination//test_02.map");
    /**
     * get Game data as an object, used to be the input parameter for GameEngineController class
     */
    public GameData d_GameData = new GameData(d_MapFile);
    /**
     * get game engine as an object that used to call the function from GameEngineController class
     */
    public GameEngineController d_GameEngineController = new GameEngineController(d_GameData);

    /**
     * game logger
     */
    private LogEntryBuffer d_LogEntryBuffer = new LogEntryBuffer();


    /**
     * Method that allows the GameEngine object to change its state.
     *
     * @param p_phase new state to be set for the GameEngine object.
     */
    public void setPhase(Phase p_phase) {
        gamePhase = p_phase;
        d_GameData.setCurrentPhase(p_phase);
        System.out.println("\nCurrent game phase: " + p_phase.getClass().getSimpleName());
    }

    /**
     * LogEntryBuffer obj getter
     *
     * @return LogEntryBuffer obj
     */
    public LogEntryBuffer getDLogEntryBuffer() {
        return d_LogEntryBuffer;
    }


    /**
     * According to user input to check in which model that user are going to get in.
     */
    public void Start() {
        d_LogEntryBuffer.start();
        CommandValidator.setGameData(d_GameData);
        String l_MyStart;
        int l_MyCommand;
        do {
            Scanner l_Scanner = new Scanner(System.in);
            System.out.println("Welcome to warzone! ");
            System.out.println("Do you want to edit map or play game? (Edit/Play/Exit)");
            System.out.println("( Edit for edit map / Play for play the game / Exit for exit the game )");

            l_MyStart = l_Scanner.nextLine();

            switch (l_MyStart.toLowerCase()) {
                case "edit":
                    // Set the state to Preload
                    setPhase(new Edit(this));
                    break;
                case "play":
                    // Set the state to PlaySetup
                    setPhase(new LoadMap(this));

                    break;
                case "exit":
                    System.out.println("Exiting Warzone Game see you next time!");
                    return;
            }
            do {
                System.out.println(" =====================================================");
                System.out.println("| #   PHASE                      : command           |");
                System.out.println(" =====================================================");
                System.out.println("| 1.  Edit                       : edit map          |");
                System.out.println("| 2.  Edit                       : save map          |");
                System.out.println("| 3.  Play except for LoadMap    : show map          |");
                System.out.println("| 4.  Play:Startup:LoadMap       : load map          |");
                System.out.println("| 5.  Play:Startup:AddPlayer     : add Players       |");
                System.out.println("| 6.  Play:Startup:AssignCountry : assign countries  |");
                System.out.println("| 7.  Play:MainPlay:IssueOrder   : issue orders      |");
                System.out.println("| 8.  Play:MainPlay:ExecuteOrder : execute orders    |");
                System.out.println("| 9.  Any                        : end               |");
                System.out.println(" =====================================================");
                System.out.println("Please enter a " + gamePhase.getClass().getSimpleName() + " phase number: ");
                if (l_Scanner.hasNextInt()) {
                    l_MyCommand = l_Scanner.nextInt();
                } else {
                    l_Scanner.nextLine();
                    l_MyCommand = 100;
                }
                System.out.println(" =====================================================");
                //
                // Calls the method corresponding to the action that the user has selected.
                // Depending on what it the current state object, these method calls will
                // have a different implementation.
                //
                switch (l_MyCommand) {
                    case 1:
                        gamePhase.editMap();
                        break;
                    case 2:
                        gamePhase.saveMap();
                        break;
                    case 3:
                        gamePhase.showMap();
                        break;
                    case 4:
                        gamePhase.loadMap();
                        break;
                    case 5:
                        gamePhase.setPlayers();
                        break;
                    case 6:
                        gamePhase.assignCountries();
                        break;
                    case 7:
                        gamePhase.issueOrder();
                        break;
                    case 8:
                        gamePhase.executeOrder();
                        break;
                    case 9:
                        gamePhase.endGame();
                        break;
                    default:
                        System.out.println("this command does not exist");
                }
            } while (!(gamePhase instanceof End));


        } while (l_MyCommand != 3);

    }

    public void issueOrders() {
        boolean l_Continue;
        do {
            l_Continue = false;
            for (Player l_Player : d_GameData.getPlayerList()) {
                boolean l_NewOrder = l_Player.issueOrder();
                l_Continue = l_Continue || l_NewOrder;
            }
        } while (l_Continue);
    }

    public void executeAllOrders() {
        boolean l_MoreOrder;
        do {
            l_MoreOrder = false;

            for (Player l_Player : d_GameData.getPlayerList()) {
                // one order of the player executes in each turn
                if (!l_Player.getOrdersInCurrentTurn().isEmpty()) {
                    l_Player.nextOrder().execute();
                }

                l_MoreOrder = !l_Player.getOrdersInCurrentTurn().isEmpty() || l_MoreOrder;
            }

        } while (l_MoreOrder);
    }

    public void showMap() {
        System.out.println("\n====================== Map ======================");
        // Information of continents
        System.out.println("Continents:");
        for (Continent l_Continent : d_GameData.getContinentList()) {
            System.out.println(l_Continent.getName());
        }

        // Information of countries
        System.out.println("\nCountries:");
        for (Country l_Country : d_GameData.getCountryList()) {
            String l_CountryName = l_Country.getName();
            int l_Army = l_Country.getArmies();
            String l_OwnerString;
            if (l_Country.getOwner() != null) {
                l_OwnerString = "owned by Player " + l_Country.getOwner().getColour();
            } else {
                l_OwnerString = "no owner";
            }
            System.out.println(l_CountryName + ": " + l_Army + " armies, " + l_OwnerString);
            StringBuilder l_StringBuilder = new StringBuilder();
            for (Country l_Border : l_Country.getBorderCountries().values()) {
                l_StringBuilder.append(l_Border.getName()).append(", ");
            }
            String l_BorderList = l_StringBuilder.delete(l_StringBuilder.length() - 2, l_StringBuilder.length()).toString();
            System.out.println("\tconnected to: " + l_BorderList);
        }

        System.out.println("\n=================================================");
    }

}
