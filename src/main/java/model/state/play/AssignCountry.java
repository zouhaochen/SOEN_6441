package model.state.play;

import controller.MainPlayController;

/**
 * The type Assign country.
 */
public class AssignCountry extends Startup {

    /**
     * Instantiates a new Assign country.
     *
     * @param p_Ml the ml
     */
    public AssignCountry(MainPlayController p_Ml) {
        super(p_Ml);
    }

    /**
     * Randomly assigns countries to each player.
     */
    @Override
    public void assignCountries() {
        // randomly assign countries for each player
        d_ml.d_GameEngineController.assignCountries();

        d_ml.getDLogEntryBuffer().updateFile();

        next();
    }

    /**
     * Goes to the next phase
     */
    @Override
    public void next() {
        d_ml.setPhase(new IssueOrder(d_ml));
    }

    /**
     * Goes to the previous phase
     */
    @Override
    public void previous() {
        d_ml.setPhase(new AddPlayer(d_ml));
    }
}
