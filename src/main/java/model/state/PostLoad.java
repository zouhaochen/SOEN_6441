package model.state;

public class PostLoad extends Edit {

	PostLoad(MainLoop p_ml) {
		super(p_ml);
	}
	
	public void showMap() {
		System.out.println("edited map is displayed");
	}

	public void loadMap() {
		System.out.println("map has been loaded");
	}

	public void editCountry() {
		System.out.println("country has been edited");
	}

	public void saveMap() {
		System.out.println("map has been saved");
		d_ml.setPhase(new PlaySetup(d_ml));
	}

	public void next() {
		System.out.println("must save map");
	}
}
