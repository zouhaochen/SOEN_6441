package model.state;

import controller.MainPlayController;
import model.map.MapEdit;
import model.state.play.Startup;

public class PostLoad extends Edit {

	public PostLoad(MainPlayController p_ml) {
		super(p_ml);
	}
	
	public void showMap() {
		printInvalidCommandMessage();
	}

	public void loadMap() { printInvalidCommandMessage(); }

	public void editMap() {
		d_ml.setPhase(new PostLoad(d_ml));
		try {
			MapEdit.mapEditLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveMap() {
		System.out.println("map has been saved");
		d_ml.setPhase(new Startup(d_ml));
	}

	public void next() {

		System.out.println("must save map");
		d_ml.setPhase(new Startup(d_ml));
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

	public void previous() {
		System.out.println("back to previous phase");
		d_ml.setPhase(new Preload(d_ml));
	}
}
