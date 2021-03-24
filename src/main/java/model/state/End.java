package model.state;


import controller.MainPlayController;

public class End extends Phase {

    /**
     *  to terminal the game.
     * @param p_ml  the object of mainloop
     */
    public End(MainPlayController p_ml) {
        super(p_ml);
    }

}