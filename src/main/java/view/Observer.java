package view;

import model.Observable;

/**
 * Observer interface
 */
public interface Observer {

    /**
     * interface update function
     *
     * @param p_Observable observable object
     */
    void update(Observable p_Observable);

    /**
     * tag getter
     *
     * @return int that stand for specific view
     */
    int getTag();
}