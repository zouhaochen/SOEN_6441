package gameplay;

/**
 * This class is a wrapper class containing the elements and the current states of the game
 */
public class GameData {

	private GamePhase d_currentPhase;

	public GamePhase getCurrentPhase() {
		return d_currentPhase;
	}

	public void setCurrentPhase(GamePhase p_currentPhase) {
		d_currentPhase = p_currentPhase;
	}
}
