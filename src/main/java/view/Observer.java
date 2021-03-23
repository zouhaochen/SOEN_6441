package view;

import model.Observable;

import java.util.List;

/**
 * Observer interface
 */
public interface Observer
{

    /**
     * interface update function
     * @param p_observable
     */
    void update(Observable p_observable);

    /**
     * tag getter
     * @return int that stand for specific view
     */
    int getTag();
}