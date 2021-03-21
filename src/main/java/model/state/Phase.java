package model.state;

import controller.MainPlayController;
import model.Observable;

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
    protected MainPlayController d_ml;


    public Phase(MainPlayController p_ml) {

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


    // attack commands
    abstract public void deploy();

    // advance commands
    abstract public void advance();

    // cards commands
    abstract public void cards();

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
