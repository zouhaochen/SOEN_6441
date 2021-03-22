
package model.state;

import controller.MainPlayController;

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
public abstract class Edit extends Phase {

	Edit(MainPlayController p_ml) {
		super(p_ml);
	}

	public void setPlayers() {
		printInvalidCommandMessage(); 
	}

	public void assignCountries() {
		printInvalidCommandMessage(); 
	}

	public void deploy() {
		printInvalidCommandMessage(); 
	}
	public void loadMap() {
		printInvalidCommandMessage();
	}

	public void advance() {
		printInvalidCommandMessage(); 
	}

	public void cards() {
		printInvalidCommandMessage(); 
	}

	public void endGame() {
		d_ml.setPhase(new End(d_ml));
	}
	
	public void showMap() {
		printInvalidCommandMessage(); 
	}
}
