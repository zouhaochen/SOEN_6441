package model.state;

import model.map.MapEdit;

public class PostLoad extends Edit {

	PostLoad(MainLoop p_ml) {
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
		d_ml.setPhase(new PlaySetup(d_ml));
	}

	public void next() {
		System.out.println("must save map");
	}

	public void previous() {
		System.out.println("back to previous phase");
		d_ml.setPhase(new Preload(d_ml));
	}
}
