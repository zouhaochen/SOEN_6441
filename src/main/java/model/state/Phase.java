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
public class Phase {

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
    public void loadMap() {
        printInvalidCommandMessage();
    }
    public void showMap(){
        printInvalidCommandMessage();
    }

    // Edit map commands
    public void editMap() {
        printInvalidCommandMessage();
    }
    public void saveMap() {
        printInvalidCommandMessage();
    }

    // Play commands
    // game setup commands
    public void setPlayers() {
        printInvalidCommandMessage();
    }
    public void assignCountries() {
        printInvalidCommandMessage();
    }


    // issue orders
    public void issueOrder() {
        printInvalidCommandMessage();
    }

    // order execute
    public void execute() {
        printInvalidCommandMessage();
    }

    // attack commands
    public void deploy() {
        printInvalidCommandMessage();
    }

    // advance commands
    public void advance() {
        printInvalidCommandMessage();
    }

    // cards commands
    public void cards() {
        printInvalidCommandMessage();
    }

    // end command
    public void endGame() {
        d_ml.setPhase(new End(d_ml));
        System.out.println("Exit the game!");
    }

    // go to next phase
    public void next() {
        printInvalidCommandMessage();
    }

    // go to previous phase
    public void previous() {
        printInvalidCommandMessage();
    }

    /**
     *  Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in phase " + this.getClass().getSimpleName() );
    }
}
