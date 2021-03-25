package controller;

import command.CommandType;
import command.CommandValidator;
import model.GameData;
import model.gameelements.Card;
import model.gameelements.Continent;
import model.gameelements.Country;
import model.gameelements.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * this is game main controller
 */
public class GameEngineController {


    /**
     * the GameData you design from the MainLoop
     */
    public GameData d_GameData;

    /**
     * GameEngineController Constructor
     *
     * @param p_GameData you should pass reference that Game Data you used
     */
    public GameEngineController(GameData p_GameData) {

        // new gameData here
        d_GameData = p_GameData;
        // command validator initialize here
        CommandValidator.setGameData(p_GameData);
    }


    /**
     * add new player to the game
     *
     * @param p_colour you custom player color
     */
    public void addNewPlayer(String p_colour) {
        // check if the player name(colour) already exists
        Player l_Player = d_GameData.getPlayerByName(p_colour);
        boolean l_Result = false;
        Player l_NewPlayer = null;

        // no such player with the same name(colour) exists
        if (l_Player == null) {
            //new temp player list
            ArrayList<Player> l_NewPlayerList = d_GameData.getPlayerList();
            l_NewPlayer = new Player(p_colour);
            // add to temp player list
            l_Result = l_NewPlayerList.add(l_NewPlayer);
            // set to game data player list
            d_GameData.setPlayerList(l_NewPlayerList);
        }

        if (l_Result) {
            System.out.println("NOTICE: New Player " + l_NewPlayer.getId() + " [" + l_NewPlayer.getColour() + "] has been added to the game.");
        } else {
            System.out.println("Warning: Fail to create Player " + p_colour + ".");
        }
    }

    /**
     * remove player from the game
     *
     * @param p_Player the player you want to remove
     */
    public void removePlayer(Player p_Player) {
        System.out.println("NOTICE: The Player " + p_Player.getId() + " [" + p_Player.getColour() + "] has been removed from the game.");
        //new temp player list
        ArrayList<Player> l_PlayerList = d_GameData.getPlayerList();
        // remove it
        l_PlayerList.remove(p_Player);
        // set to game data player list
        d_GameData.setPlayerList(l_PlayerList);
    }


    /**
     * this method is current player phase process, player's order will be processed here.
     */
    public void phaseProcess() {


        for (Player l_Player : d_GameData.getPlayerList()) {
            // player executes order till order list is empty
            while (!l_Player.getOrdersInCurrentTurn().isEmpty()) {
                l_Player.nextOrder().execute();
                this.showMap(l_Player);
            }

        }
    }


    /**
     * this method used to player show model.map in game
     *
     * @param p_Player input the playerID you want to show its model.map
     */
    public void showMap(Player p_Player) {
        Player l_Player = d_GameData.getTargetPlayer(p_Player);
        System.out.println("Player [" + l_Player.getColour() + "] your current army force show below: ");
        // iterate each country in player's model.map
        for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
            // get name and army separately
            String l_CountryName = l_CountryEntry.getKey();
            Country l_Country = l_CountryEntry.getValue();
            //print military force first
            int ArmyNum = l_Country.getArmies();
            System.out.println(l_CountryName + " has " + ArmyNum + " Armies.");

            // get neighbour country into string arrayList
            System.out.println(l_CountryName + " connected to :");
            for (Map.Entry<String, Country> l_BorderCountryEntry : d_GameData.getCountryByName(l_CountryName).getBorderCountries().entrySet()) {
                System.out.print(l_BorderCountryEntry.getValue().getName() + "\t");
            }
            System.out.print("Player cards: ");
            for (Card l_Card : p_Player.getCards()) {
                System.out.print("[" + l_Card.name() + "]");
            }


            System.out.println();
        }

    }


    /**
     * Game Engine get correct model.map file path function, return model.map path
     *
     * @return .model.map file relative file path
     * @throws IOException if file not found
     */
    public String getMapFilePath() throws IOException {
        String l_Command;
        String[] l_CommandArr;

        // validate the command from console
        do {
            System.out.println("\nPlease enter command to load the map for the game: ");
            Scanner l_scanner = new Scanner(System.in);
            l_Command = l_scanner.nextLine();
            l_CommandArr = l_Command.split(" ");
        } while (!CommandValidator.validate(l_Command));

        String l_ProjectPath = new File("").getCanonicalPath();
        return l_ProjectPath + "/domination/" + l_CommandArr[1];
    }

    /**
     * Allows the user to create/remove the players for the game by typing command.
     */
    public void gamePlayerCommand() {
        String l_Command;
        String[] l_CommandArr;

        // validate the command from console
        do {
            System.out.println("\nPlease enter \"gameplayer -add playername -remove playername\" command to create players for the game: ");
            Scanner l_scanner = new Scanner(System.in);
            l_Command = l_scanner.nextLine();
            l_CommandArr = l_Command.split(" ");
            if (!l_CommandArr[0].equalsIgnoreCase(CommandType.ADD_PLAYER.getLabel())) {
                System.out.println("\nInvalid command.");
            }
        } while (!l_CommandArr[0].equalsIgnoreCase(CommandType.ADD_PLAYER.getLabel()) || !CommandValidator.validate(l_Command));

        // iterate through the command arguments to perform the operations
        for (int l_Index = 1; l_Index < l_CommandArr.length - 1; ) {
            String l_Type = l_CommandArr[l_Index];
            String l_PlayerName = l_CommandArr[l_Index + 1];
            if (l_Type.equalsIgnoreCase("-add")) {
                addNewPlayer(l_PlayerName);
            } else if (l_Type.equalsIgnoreCase("-remove")) {
                Player l_PlayerToRemove = d_GameData.getPlayerByName(l_PlayerName);
                if (l_PlayerToRemove != null) {
                    removePlayer(l_PlayerToRemove);
                } else {
                    System.out.println("Warning: Fail to remove Player " + l_PlayerName + ". No such player exists.");
                }
            }

            l_Index += 2;
        }
    }

    /**
     * Randomly assign all the countries to the players.
     */
    public void assignCountries() {
        String l_Command;

        // validate the command from console
        do {
            System.out.println("\nPlease enter command to randomly assign countries to all players: ");
            Scanner l_scanner = new Scanner(System.in);
            l_Command = l_scanner.nextLine();
        } while (!CommandValidator.validate(l_Command));

        // randomly assign countries
        int l_NumberOfPlayer = d_GameData.getPlayerList().size();
        int l_NumberOfCountry = d_GameData.getCountryList().size();
        int l_Quotient = l_NumberOfCountry / l_NumberOfPlayer;
        Stack<Country> l_CountryStack = new Stack<>();
        l_CountryStack.addAll(d_GameData.getCountryList());
        Collections.shuffle(l_CountryStack);

        // first step: try to assign same amounts of countries to each player
        for (Player l_Player :
                d_GameData.getPlayerList()) {
            for (int l_Count = 0; l_Count < l_Quotient; ++l_Count) {
                l_CountryStack.peek().setOwner(l_Player);
                l_Player.assignCountry(l_CountryStack.pop());
            }
        }

        // second step: if there are any countries left
        Random l_Random = new Random();
        while (!l_CountryStack.empty()) {
            int l_RandomPlayerIndex = l_Random.nextInt(d_GameData.getPlayerList().size());
            Player l_RandomPlayer = d_GameData.getPlayerList().get(l_RandomPlayerIndex);
            l_CountryStack.peek().setOwner(l_RandomPlayer);
            l_RandomPlayer.assignCountry(l_CountryStack.pop());
        }
    }

    /**
     * Award reinforcement to each player.
     */
    public void awardReinforcement() {
        for (Player l_Player : d_GameData.getPlayerList()) {
            l_Player.setReinforcementArmies(calculateReinforcement(l_Player));
        }
    }

    /**
     * Calculate reinforcement value.
     *
     * @param p_Player the player
     * @return reinforcement value
     */
    public int calculateReinforcement(Player p_Player) {
        int l_MinimumReinforcementNumber = 3;
        int l_BasicValue = p_Player.getCountriesInControl().size() / 3;
        int l_Reinforcement = l_BasicValue + getReinforcementBonus(p_Player);
        return Math.max(l_MinimumReinforcementNumber, l_Reinforcement);
    }

    /**
     * Calculate the reinforcement armies to be award.
     *
     * @param p_Player the player object to be checked
     * @return the number of reinforcement
     */
    private int getReinforcementBonus(Player p_Player) {
        int l_ReinforcementBonus = 0;
        for (Continent l_Continent : d_GameData.getContinentList()) {

            // go through all the countries in the current continent to check if the owner is the same as
            // the passed-in player object
            Map<String, Country> l_Countries = l_Continent.getCountries();
            boolean l_ContinentConquered = true;
            Player l_Owner;
            for (Country l_Country : l_Countries.values()) {
                l_Owner = l_Country.getOwner();
                if (l_Owner.getId() != p_Player.getId()) {
                    l_ContinentConquered = false;
                    break;
                }
            }

            if (l_ContinentConquered) {
                l_ReinforcementBonus += l_Continent.getContinentValue();
            }
        }
        return l_ReinforcementBonus;
    }


    /**
     * check current game whether is over, and also remove the lost player
     *
     * @return true if the game is over, false otherwise
     */
    public boolean checkGameIsOver() {
        List<Player> l_ToRemove = new ArrayList<>();
        // remove all the players who have lost
        for (Player l_Player : d_GameData.getPlayerList()) {
            // if player already lost or not exist, since has no country in play
            if (!l_Player.getPlayerExist()) {
                l_ToRemove.add(l_Player);
            }
        }

        if (l_ToRemove.size() > 0) {
            d_GameData.getPlayerList().removeAll(l_ToRemove);
        }

        if (d_GameData.getPlayerList().size() == 1) {
            System.out.println("=====Game is Over=====");
            System.out.println("The winner is: Player " + d_GameData.getPlayerList().get(0).getColour());
            return true;
        }
        return false;
    }


}
