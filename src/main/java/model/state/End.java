package model.state;

import controller.GameEngineController;

public class End extends GamePlay {
    /**
     * initialize the GameData object
     *
     * @param p_gameData The current context of game engine object
     */
    End(GameEngineController p_gameData, MainLoop p_mlObj) {
        super(p_gameData,p_mlObj);
    }
}
