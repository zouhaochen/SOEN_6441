package model.state;

import controller.MainPlayController;

/**
 * State of the State pattern. Here implemented as a abstract class.
 * <p>
 * In this example, the states represent states in the board game Risk.
 * There are many states, and even a hierarchy of states:
 * <p>
 * Phase
 * Edit phase (abstract)
 * Preload
 * Postload
 * Play (abstract)
 * PlaySetup
 * MainPlay
 * Reinforcement <--
 * Attack          |
 * Fortify ---------
 * End
 * <p>
 * In each state, nextState() is defined so that it goes down in
 * the above list, except for Fortify, which goes back to
 * Reinforcement state.
 */
public class Phase {

    /**
     * Contains a reference to the State of the GameEngine
     * so that the state object can change the state of
     * the GameEngine to transition between states.
     */
    protected MainPlayController d_ml;


    /**
     * Instantiates a new Phase.
     *
     * @param p_ml the p ml
     */
    public Phase(MainPlayController p_ml) {

        d_ml = p_ml;

    }

    // common commands

    /**
     * Load map.
     */
    public void loadMap() {
        printInvalidCommandMessage();
    }

    /**
     * Show map.
     */
    public void showMap() {
        printInvalidCommandMessage();
    }

    // Edit map commands

    /**
     * Edit map.
     */
    public void editMap() {
        printInvalidCommandMessage();
    }

    /**
     * Save map.
     */
    public void saveMap() {
        printInvalidCommandMessage();
    }

    // game setup commands
    // Play commands

    /**
     * Sets players.
     */
    public void setPlayers() {
        printInvalidCommandMessage();
    }

    /**
     * Assign countries.
     */
    public void assignCountries() {
        printInvalidCommandMessage();
    }

    /**
     * Issue order.
     */
    public void issueOrder() {
        printInvalidCommandMessage();
    }

    /**
     * Execute order.
     */
    public void executeOrder() {
        printInvalidCommandMessage();
    }

    /**
     * Deploy.
     */
    public void deploy() {
        printInvalidCommandMessage();
    }

    /**
     * End game.
     */
    public void endGame() {
        d_ml.setPhase(new End(d_ml));
        System.out.println("Exit the game!");
    }

    /**
     * Next.
     */
    public void next() {
        printInvalidCommandMessage();
    }

    /**
     * Previous.
     */
    public void previous() {
        printInvalidCommandMessage();
    }

    /**
     * Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in phase " + this.getClass().getSimpleName());
    }
}
