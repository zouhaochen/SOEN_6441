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
        d_Ml.d_GameEngineController.assignCountries();

        d_Ml.getDLogEntryBuffer().updateFile();

        next();
    }

    /**
     * Goes to the next phase
     */
    @Override
    public void next() {
        d_Ml.setPhase(new IssueOrder(d_Ml));
    }

    /**
     * Goes to the previous phase
     */
    @Override
    public void previous() {
        d_Ml.setPhase(new AddPlayer(d_Ml));
    }
}
