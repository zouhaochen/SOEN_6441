package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.map.MapGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class PlaySetup extends Play {

    PlaySetup(MainLoop p_ml) {
        super(p_ml);
    }

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
    public GameEngineController d_GameEngine = new GameEngineController(d_GameData);;




    public void loadMap() {

        d_GameData.setCurrentPhase(GamePhase.STARTUP);
        try {
            this.d_MapFile = new File(d_GameEngine.getMapFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        d_GameData = new GameData(d_MapFile);
        d_GameData.setCurrentPhase(GamePhase.STARTUP);
        d_GameEngine = new GameEngineController(d_GameData);
        try {
            d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showMap() {
        try {
            System.out.println("\nMain Graph show below:");
            MapGraph.printTable(d_MapFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPlayers() {
        Scanner l_scanner = new Scanner(System.in);

        boolean l_isTrue = true;
        while (l_isTrue) {
            System.out.println("do you want to add or remove player (Number of player limit is 2 to 5)? (y/n) ");
            String l_askUser = l_scanner.nextLine().trim();

            if (l_askUser.equalsIgnoreCase("y")) {
                if (d_GameData.getPlayerList().size() < 5) {
                    d_GameEngine.gamePlayerCommand();
                }
                // since the number of player range is 2 to 5. no more player can be add in.
                else if (d_GameData.getPlayerList().size() >= 5) {
                    System.out.println("number of player out of limit ");
                    continue;
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
                System.out.println("Invalid command, please try again (y/n): ");
            }
        }
    }

    public void assignCountries() {
        // randomly assign countries for each player
        d_GameEngine.assignCountries();
        int l_CurrentReinforcement = 5;
        int l_TempReinforcementArmy;

        // Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
        this.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
        System.out.println(d_GameData.getCurrentPhase());

        for (Player l_Player : this.d_GameEngine.d_GameData.getPlayerList()) {
            l_CurrentReinforcement += d_GameEngine.getReinforcementBonus(l_Player);
            l_Player.setReinforcementArmies(l_CurrentReinforcement);
            System.out.println(l_CurrentReinforcement + " Reinforcement Armies are assigned to " + " Player [" + l_Player.getColour() + "]  ");

            for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
                System.out.println("Controlling countries: " + l_CountryEntry.getKey());
            }
        }
    }

    public void reinforce() {
        printInvalidCommandMessage();
    }

    public void attack() {
        printInvalidCommandMessage();
    }

    public void fortify() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        printInvalidCommandMessage();
    }

    public void next() {
        d_ml.setPhase(new End(d_ml));
    }
}
