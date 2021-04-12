package model;

import view.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Subject Class, which named Observable
 */
public class Observable implements Serializable {
    private List<Observer> d_ObserverList = new ArrayList<>();

    /**
     * get tag list of registered obs
     *
     * @return list of string with obs tag
     */
    public List<String> getDObserverList() {
        List<String> l_StrList = new ArrayList<>();
        for (Observer l_Obs : d_ObserverList) {
            l_StrList.add(Integer.toString(l_Obs.getTag()));
        }
        return l_StrList;
    }

    /**
     * add new observer
     *
     * @param p_Observer observer
     */
    public void attachObs(Observer p_Observer) {
        d_ObserverList.add(p_Observer);
    }

    /**
     * remove un-used observer
     *
     * @param p_Observer observer
     */
    public void removeObs(Observer p_Observer) {
        d_ObserverList.remove(p_Observer);
    }

    /**
     * notify all obs
     *
     * @param p_Obs this parameter is From Observable class, be aware.
     */
    public void notifyObs(Observable p_Obs) {
        for (Observer l_Obs : d_ObserverList) {
            l_Obs.update(p_Obs);
        }
    }

    /**
     * notify player view only
     *
     * @param p_Obs observable object
     */
    public void notifyPlayerObs(Observable p_Obs) {
        for (Observer l_Obs : d_ObserverList) {
            if (l_Obs.getTag() == 1) {
                l_Obs.update(p_Obs);
            }
        }
    }

    /**
     * notify GameData view only
     *
     * @param p_Obs observable object
     */
    public void notifyGameDataObs(Observable p_Obs) {
        for (Observer l_Obs : d_ObserverList) {
            if (l_Obs.getTag() == 2) {
                l_Obs.update(p_Obs);
            }
        }
    }

    /**
     * notify LogEntryBuffer view only
     *
     * @param p_Obs observable object
     */
    public void notifyLogEntryBufferObs(Observable p_Obs) {
        for (Observer l_Obs : d_ObserverList) {
            if (l_Obs.getTag() == 3) {
                l_Obs.update(p_Obs);
            }
        }
    }
}