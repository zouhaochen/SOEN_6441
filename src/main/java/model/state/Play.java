package model.state;


import controller.GameEngineController;
import model.GameData;

import java.io.File;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior
 *  for commands that are valid in this state, and for the others signifies
 *  that the command is invalid.
 *
 *  This state represents a group of states, and defines the behavior
 *  that is common to all the states in its group. All the states in its
 *  group need to extend this class.
 *
 */
public abstract class Play extends Phase {

    Play(MainLoop p_ml ){
        super(p_ml);
    }
//    /**
//     * model.map file that use to load represent game model.map.
//     */
//    public File d_MapFile = new File(".//domination//test_02.map");
//    /**
//     * get Game data as an object, used to be the input parameter for GameEngineController class
//     */
//    public GameData d_GameData = new GameData(d_MapFile);
//    /**
//     * get game engine as an object that used to call the function from GameEngineController class
//     */
//    public GameEngineController d_GameEngine = new GameEngineController(d_GameData);

    public void showMap() {
        System.out.println("map is being displayed");
    }

    public void editMap() {
        printInvalidCommandMessage();
    }

    public void saveMap() {
        printInvalidCommandMessage();
    }
    public void previous() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        d_ml.setPhase(new End(d_ml));
    }
}