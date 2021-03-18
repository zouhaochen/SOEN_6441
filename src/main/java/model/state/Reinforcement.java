package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;

import java.io.File;
import java.util.Map;

public class Reinforcement extends MainPlay{
    Reinforcement(MainLoop p_ml) {
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
    public GameEngineController d_GameEngine = new GameEngineController(d_GameData);


    @Override
    public void reinforce() {

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
        System.out.println("---------REINFORCEMENT PHASE COMPLETE-----------\n");
    }

    @Override
    public void deployOrder() {
        printInvalidCommandMessage();
    }


    @Override
    public void fortify() {
        printInvalidCommandMessage();
    }

    @Override
    public void next() {
        d_ml.setPhase(new IssueOrder(d_ml));
    }

}
