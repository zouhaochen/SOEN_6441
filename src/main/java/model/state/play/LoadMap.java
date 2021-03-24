package model.state.play;

import controller.GameEngineController;
import controller.MainPlayController;
import model.GameData;
import model.gameelements.order.OrderFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The type Load map.
 */
public class LoadMap extends Startup {

    /**
     * Instantiates a new Load map.
     *
     * @param p_Ml the ml
     */
    public LoadMap(MainPlayController p_Ml) {
        super(p_Ml);
    }

    /**
     * Loads a map.
     */
    public void loadMap() {

        try {
            d_ml.d_MapFile = new File(d_ml.d_GameEngineController.getMapFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        d_ml.d_GameData = new GameData(d_ml.d_MapFile);
        d_ml.d_GameData.setCurrentPhase(this);
        d_ml.d_GameEngineController = new GameEngineController(d_ml.d_GameData);
        OrderFactory.setGameData(d_ml.d_GameData);
        try {
            d_ml.d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        d_ml.getDLogEntryBuffer().updateFile();

        next();
    }

    /**
     * Showing map is invalid.
     */
    @Override
    public void showMap() {
        printInvalidCommandMessage();
    }

    /**
     * Goes to the next phase.
     */
    @Override
    public void next() {
        d_ml.setPhase(new AddPlayer(d_ml));
    }
}
