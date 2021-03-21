package model.state.play;

import controller.GameEngineController;
import controller.MainPlayController;
import model.GamePhase;
import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import model.map.MapGraph;
import model.state.End;
import model.state.PostLoad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Startup extends Play {

    public Startup(MainPlayController p_ml) {
        super(p_ml);
    }



    public void loadMap() {

        d_ml.d_GameData.setCurrentPhase(GamePhase.STARTUP);
        try {
            d_ml.d_MapFile = new File(d_ml.d_GameEngine.getMapFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        d_ml.d_GameData = new GameData(d_ml.d_MapFile);
        d_ml.d_GameData.setCurrentPhase(GamePhase.STARTUP);
        d_ml.d_GameEngine = new GameEngineController(d_ml.d_GameData);
        try {
            d_ml.d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        d_ml.getDLogEntryBuffer().updateFile();
        setPlayers();


    }

    public void showMap() {
        try {
            System.out.println("\nMain Graph show below:");
            MapGraph.printTable(d_ml.d_MapFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editMap() {
        d_ml.d_GameData.setCurrentPhase(GamePhase.STARTUP);
        try {
            d_ml.d_MapFile = new File(d_ml.d_GameEngine.getMapFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        d_ml.d_GameData = new GameData(d_ml.d_MapFile);
        d_ml.d_GameData.setCurrentPhase(GamePhase.STARTUP);
        d_ml.d_GameEngine = new GameEngineController(d_ml.d_GameData);
        try {
            d_ml.d_GameData.loadMap();
        } catch (FileNotFoundException e) {
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
                if (d_ml.d_GameData.getPlayerList().size() < 5) {
                    d_ml.d_GameEngine.gamePlayerCommand();
                }
                // since the number of player range is 2 to 5. no more player can be add in.
                else if (d_ml.d_GameData.getPlayerList().size() >= 5) {
                    System.out.println("number of player out of limit ");
                    continue;
                }
            } else if (l_askUser.equalsIgnoreCase("n")) {
                // since the number of player range is 2 to 5. no more player can be remove in.
                if (d_ml.d_GameData.getPlayerList().size() < 2) {
                    System.out.println("number of player is not enough, please add more ");
                } else {
                    System.out.println("All player have already set ");
                    l_isTrue = false;
                }
            } else {
                System.out.println("Invalid command, please try again (y/n): ");
            }
        }
        d_ml.getDLogEntryBuffer().updateFile();
        assignCountries();


    }

    public void assignCountries() {
        // randomly assign countries for each player
        d_ml.d_GameEngine.assignCountries();
        int l_CurrentReinforcement = 5;
        int l_TempReinforcementArmy;

        // Assign Reinforcement phase, Call the method in gameplay to allocate the number of ReinforcementArmies in each round to each player
        d_ml.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.REINFORCEMENT);
        System.out.println(d_ml.d_GameData.getCurrentPhase());

        for (Player l_Player : d_ml.d_GameEngine.d_GameData.getPlayerList()) {
            l_CurrentReinforcement += d_ml.d_GameEngine.getReinforcementBonus(l_Player);
            l_Player.setReinforcementArmies(l_CurrentReinforcement);
            System.out.println(l_CurrentReinforcement + " Reinforcement Armies are assigned to " + " Player [" + l_Player.getColour() + "]  ");

            for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
                System.out.println("Controlling countries: " + l_CountryEntry.getKey());
            }
        }
        d_ml.getDLogEntryBuffer().updateFile();
        d_ml.setPhase(new MainPlay(d_ml));
    }

    public void deploy() {
        printInvalidCommandMessage();
    }

    public void advance() {
        printInvalidCommandMessage();
    }

    public void cards() {
        printInvalidCommandMessage();
    }

    public void endGame() { d_ml.setPhase(new End(d_ml));}

    public void next() {
        d_ml.setPhase(new MainPlay(d_ml));
    }

    public void previous() {
        d_ml.setPhase(new PostLoad(d_ml));
    }
}
