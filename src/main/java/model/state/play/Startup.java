package model.state.play;

import controller.MainPlayController;

import java.io.Serializable;

/**
 * The type Startup.
 */
public class Startup extends Play implements Serializable {

    /**
     * Instantiates a new Startup.
     *
     * @param p_Ml the ml
     */
    public Startup(MainPlayController p_Ml) {
        super(p_Ml);
    }

}
