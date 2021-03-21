package view;

import model.GameData;
import model.Observable;

/**
 * GameData view, implement in build3
 */
public class GameDataView implements Observer {


    /**
     * view tag
     */
    int d_ObsTag=2;

    /**
     * constructor
     * @param p_GameData
     */
    GameDataView(GameData p_GameData){p_GameData.attachObs(this);}


    /**
     * override function update
     * @param p_observable
     */
    @Override
    public void update(Observable p_observable) {

    }

    /**
     * tag getter
     *
     * @return obs tag string
     */
    @Override
    public int getTag() {
        return d_ObsTag;
    }
}
