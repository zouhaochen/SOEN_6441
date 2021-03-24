package model.state.play;

import controller.MainPlayController;

/**
 * ConcreteState of the State pattern. In this example, defines behavior
 * for commands that are valid in this state, and for the others signifies
 * that the command is invalid.
 * <p>
 * This state represents a group of states, and defines the behavior
 * that is common to all the states in its group. All the states in its
 * group need to extend this class.
 */
public abstract class MainPlay extends Play {

    /**
     * Instantiates a new Main play.
     *
     * @param p_Ml the ml
     */
    public MainPlay(MainPlayController p_Ml) {
        super(p_Ml);

    }

}
