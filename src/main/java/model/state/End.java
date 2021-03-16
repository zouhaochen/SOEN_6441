package model.state;

import controller.GameEngineController;

public class End extends GameplayPhase {
    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    End(GameEngineController p_gameData) {
        super(p_gameData);
    }
}
