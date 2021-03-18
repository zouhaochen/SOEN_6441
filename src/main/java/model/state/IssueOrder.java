package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.gameelements.Player;

import java.io.File;

public class IssueOrder extends MainPlay {
    IssueOrder(MainLoop p_ml) {
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
        printInvalidCommandMessage();
    }

    @Override
    public void deployOrder() {

        int l_TempReinforcementArmy;

        for (Player l_Player : this.d_GameEngine.d_GameData.getPlayerList()) {
            this.d_GameEngine.d_GameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
            System.out.println(this.d_GameEngine.d_GameData.getCurrentPhase());

            l_TempReinforcementArmy = l_Player.getReinforcementArmies();
            while (l_TempReinforcementArmy > 0) {
                System.out.println("==== Now Player [" + l_Player.getColour() + "]'s turn to issue order ====");
                System.out.println(" Player [" + l_Player.getColour() + "] have " + l_TempReinforcementArmy
                        + " Reinforcement Armies.");
                l_Player.issueOrder();

                //Reduce the corresponding amount of reinforcements after deploying.
                l_TempReinforcementArmy -= l_Player.getLastOrderFromQueue().getOrderInfo().getNumberOfArmy();
            }
        }
    }

    @Override
    public void fortify() {

    }

    @Override
    public void next() {
        d_ml.setPhase(new Execution(d_ml));
    }
}
