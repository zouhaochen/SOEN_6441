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
            d_Ml.d_MapFile = new File(d_Ml.d_GameEngineController.getMapFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        d_Ml.d_GameData = new GameData(d_Ml.d_MapFile);
        d_Ml.d_GameData.setCurrentPhase(this);
        d_Ml.d_GameEngineController = new GameEngineController(d_Ml.d_GameData);
        OrderFactory.setGameData(d_Ml.d_GameData);
        try {
            d_Ml.d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        d_Ml.getDLogEntryBuffer().updateFile();

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
        d_Ml.setPhase(new AddPlayer(d_Ml));
    }
}
