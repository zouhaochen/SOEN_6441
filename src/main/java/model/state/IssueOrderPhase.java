package model.state;

import controller.GameEngineController;

public class IssueOrderPhase extends GameplayPhase {
    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    IssueOrderPhase(GameEngineController p_gameData) {
        super(p_gameData);
    }

}
