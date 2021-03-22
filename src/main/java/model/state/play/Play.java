package model.state.play;

import controller.MainPlayController;
import model.map.MapGraph;
import model.state.End;
import model.state.Phase;

/**
 * ConcreteState of the State pattern. In this example, defines behavior
 * for commands that are valid in this state, and for the others signifies
 * that the command is invalid.
 * <p>
 * This state represents a group of states, and defines the behavior
 * that is common to all the states in its group. All the states in its
 * group need to extend this class.
 */
public abstract class Play extends Phase {

    Play(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void showMap() {
        try {
            System.out.println("\nMain Graph show below:");
            MapGraph.printTable(d_ml.d_MapFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
