package model.state.play;

import controller.MainPlayController;

public class AssignCountry extends Startup{

    public AssignCountry(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void assignCountries() {
        // randomly assign countries for each player
        d_ml.d_GameEngineController.assignCountries();

        d_ml.getDLogEntryBuffer().updateFile();

        next();
    }

    /**
     * continue to next phase
     */
    @Override
    public void next() {
        d_ml.setPhase(new IssueOrder(d_ml));
    }

    /**
     * back to previous phase
     */
    @Override
    public void previous() {
        d_ml.setPhase(new AddPlayer(d_ml));
    }
}
