package model.state;

import controller.GameEngineController;
import gameplay.GamePhase;
import model.GameData;
import model.map.MapGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Preload extends Edit {

	/**
	 * model.map file that use to load represent game model.map.
	 */
	public File d_MapFile = new File(".//domination//test_02.map");
	/**
	 * get Game data as an object, used to be the input parameter for GameEngineController class
	 */
	public GameData d_GameData = new GameData(d_MapFile);
	/**
	 * get game engine as an object that used to call the function from GameEngineController class
	 */
	public GameEngineController d_GameEngine = new GameEngineController(d_GameData);

	Preload(MainLoop p_ml) {
		super(p_ml);
	}

	public void loadMap() {
		d_GameData.setCurrentPhase(GamePhase.STARTUP);
		try {
			this.d_MapFile = new File(d_GameEngine.getMapFilePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		d_GameData = new GameData(d_MapFile);
		d_GameData.setCurrentPhase(GamePhase.STARTUP);
		d_GameEngine = new GameEngineController(d_GameData);
		try {
			d_GameData.loadMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void showMap() {
		try {
			System.out.println("\nMain Graph show below:");
			MapGraph.printTable(d_MapFile.getName());
		} catch (Exception e) {
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
