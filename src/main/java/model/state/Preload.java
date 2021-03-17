package model.state;

import model.map.MapEdit;

public class Preload extends Edit {

	Preload(MainLoop p_ml) {
		super(p_ml);
	}

	public void loadMap() {
		System.out.println("map has been loaded");
		d_ml.setPhase(new PostLoad(d_ml));
		try {
			MapEdit.mapEditLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editCountry() {
		printInvalidCommandMessage(); 
	}

	public void saveMap() {
		printInvalidCommandMessage(); 
	}

	public void next() {
		System.out.println("must load map");
		d_ml.setPhase(new PostLoad(d_ml));
	}
}
