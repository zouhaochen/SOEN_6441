package model.state;

import controller.GameEngineController;
import controller.MainPlayController;
import model.GameData;
import model.map.MapGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Preload extends Edit {


	public Preload(MainPlayController p_ml) {
		super(p_ml);
	}

	public void loadMap() {
		try {
			d_ml.d_MapFile = new File(d_ml.d_GameEngine.getMapFilePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		d_ml.d_GameData = new GameData(d_ml.d_MapFile);
		d_ml.d_GameEngine = new GameEngineController(d_ml.d_GameData);
		try {
			d_ml.d_GameData.loadMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void editMap() {
		printInvalidCommandMessage();
	}

	public void saveMap() {
		printInvalidCommandMessage();
	}

	public void next() {
		System.out.println("must load map if play a game");
		d_ml.setPhase(new PostLoad(d_ml));
	}

	public void previous() {
		System.out.println("already in the first phase");
	}
}
