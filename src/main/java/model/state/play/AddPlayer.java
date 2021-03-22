package model.state.play;

import controller.MainPlayController;

public class AddPlayer extends Startup{
    public AddPlayer(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void setPlayers() {

        next();
    }

    @Override
    public void next() {
        d_ml.setPhase(new AssignCountry(d_ml));
    }
}
