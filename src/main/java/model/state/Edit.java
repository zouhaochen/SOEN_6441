package model.state;

import controller.MainPlayController;
import model.map.MapEdit;
import model.map.MapGraph;
import model.state.play.LoadMap;

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
public class Edit extends Phase {

	public Edit(MainPlayController p_ml) {
		super(p_ml);
	}

	public void showMap() {
		try {
			System.out.println("\nMain Graph show below:");
			MapGraph.printTable(d_ml.d_MapFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void editMap() {

		try {
			MapEdit.mapEditLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveMap() {
		System.out.println("map has been saved");
		d_ml.setPhase(new LoadMap(d_ml));
	}

	public void next() {

		System.out.println("must save map");
		d_ml.setPhase(new LoadMap(d_ml));
		System.out.println(" =================================================");
		System.out.println("| #   PHASE                   : command           |");
		System.out.println(" =================================================");
		System.out.println("| 1.  Play:PlaySetup:         : load map          |");
		System.out.println("| 2.  Play:PlaySetup:         : show map          |");
		System.out.println("| 7.  Play:MainPlay:deploy    : reinforce+deploy  |");
		System.out.println("| 8.  Play:MainPlay:advance   : advance           |");
		System.out.println("| 9.  Play:MainPlay:cards     : cards             |");
		System.out.println("| 10. Play                    : end game          |");
		System.out.println("| 11. Play:PlaySetup:         : next phase        |");
		System.out.println("| 12. Any                     : previous phase    |");
		System.out.println(" =================================================");
		System.out.println("enter a " + d_ml.getClass().getSimpleName() + " phase command: ");
	}

}