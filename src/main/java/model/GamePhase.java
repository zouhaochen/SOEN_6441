package model;

/**
 * Phase of the game
 */
public enum GamePhase {
    /**
     * to tag some player is lost
     */
    LOST(0),
    /**
     * waiting to turn phase
     */
    WAITING_TO_TURN(1),
    /**
     * reinforcement phase
     */
    REINFORCEMENT(2),
    /**
     * attack phase
     */
    ATTACK(3),
    /**
     * fortification phase
     */
    FORTIFICATION(4),
    /**
     * end of game phase
     */
    END_OF_GAME(5),
    /**
     * model.map edit phase
     */
    MAP_EDIT(6),
    /**
     * start up phase
     */
    STARTUP(7),
    /**
     * issue order phase
     */
    ISSUE_ORDER(8),
    /**
     * player win tag, when player win the game
     */
    WIN(9);


    /**
     * the integer that can represent the game phase for easier checking current game phase
     */
    private final int d_Phase;

    /**
     * private constructor
     *
     * @param p_Phase the phase
     */
    GamePhase(int p_Phase) {
        this.d_Phase = p_Phase;
    }

    /**
     * get the number re-present the game phase
     *
     * @return number re-present the game phase
     */
    public int getGamePhaseAsInt() {
        return d_Phase;
    }

}
