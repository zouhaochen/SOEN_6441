package model.state;


import controller.MainPlayController;

/**
 * To terminal the game
 */
public class End extends Phase {

    /** constructor of End class
     * @param p_Ml  take MainPlayController as an object.
     */
    public End(MainPlayController p_Ml) {
        super(p_Ml);
    }

}