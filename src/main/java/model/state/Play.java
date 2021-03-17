package model.state;



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

    Play(MainLoop p_ml) {
        super(p_ml);
    }

    public void showMap() {
        System.out.println("map is being displayed");
    }

    public void editMap() {
        printInvalidCommandMessage();
    }

    public void saveMap() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        d_ml.setPhase(new End(d_ml));
    }
}