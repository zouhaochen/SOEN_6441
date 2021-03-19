package model.state;

import controller.GameEngineController;
import model.GameData;

import java.io.File;

/**
 *	State of the State pattern. Here implemented as a abstract class.
 *
 *	In this example, the states represent states in the board game Risk.
 *  There are many states, and even a hierarchy of states:
 *
 *		Phase
 *        Edit phase (abstract)
 *          Preload
 *          Postload
 *        Play (abstract)
 *          PlaySetup
 *          MainPlay
 *          Reinforcement <--
 *          Attack          |
 *          Fortify ---------
 *        End
 *
 *      In each state, nextState() is defined so that it goes down in
 *      the above list, except for Fortify, which goes back to
 *      Reinforcement state.
 */
public abstract class Phase {

    /**
     *  Contains a reference to the State of the GameEngine
     *  so that the state object can change the state of
     *  the GameEngine to transition between states.
     */
    MainLoop d_ml;


    Phase(MainLoop p_ml) {

        d_ml = p_ml;

    }

    // common commands
    abstract public void loadMap();
    abstract public void showMap();

    // Edit map commands
    abstract public void editMap();
    abstract public void saveMap();

    // Play commands
    // game setup commands
    abstract public void setPlayers();
    abstract public void assignCountries();

    // reinforcement commands
    abstract public void reinforce();

    // attack commands
    abstract public void IssueOrder();

    // fortify commands
    abstract public void execute();

    // end command
    abstract public void endGame();

    // go to next phase
    abstract public void next();

    // go to previous phase
    abstract public void previous();

    /**
     *  Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
    }
}
