package gameplay;

/**
 * Phase of the game
 */
 public enum GamePhase {
    LOST(0),
    WAITING_TO_TURN(1),
    REINFORCEMENT(2),
    ATTACK(3),
    FORTIFICATION(4),
    END_OF_GAME(5),
    MAP_EDIT(6),
    STARTUP(7),
    ISSUE_ORDER(8),
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
