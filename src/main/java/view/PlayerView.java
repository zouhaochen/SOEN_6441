package view;

import model.Observable;
import model.gameelements.Player;

/**
 * Player view , implements in build 3
 */
public class PlayerView implements Observer {

    /**
     * view tag
     */
    int d_ObsTag = 1;

    /**
     * constructor
     *
     * @param p_Player player object
     */
    public PlayerView(Player p_Player) {
        p_Player.attachObs(this);
    }


    /**
     * override function update
     *
     * @param p_Observable observable object
     */
    @Override
    public void update(Observable p_Observable) {

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
