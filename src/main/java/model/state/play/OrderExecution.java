package model.state.play;

import controller.MainPlayController;
import model.gameelements.Country;

import java.util.List;

/**
 * The type Order execution.
 */
public class OrderExecution extends MainPlay {

    /**
     * Instantiates a new Order execution.
     *
     * @param p_Ml the p ml
     */
    public OrderExecution(MainPlayController p_Ml) {
        super(p_Ml);
    }

    /**
     * Executes all orders.
     */
    @Override
    public void executeOrder() {
        d_Ml.executeAllOrders();

        // if there are any dangling countries, assign them to NEUTRAL player
        List<Country> l_DanglingCountries = d_Ml.getDanglingCountries();
        if (!l_DanglingCountries.isEmpty()) {
            d_Ml.assignDanglingCountriesToNeutralPlayer(l_DanglingCountries);
        }

        d_Ml.resetAttackableState();
        d_Ml.getDLogEntryBuffer().updateFile();

        if (d_Ml.d_GameEngineController.checkGameIsOver()) {
            // game ends
            next();
        } else {
            // go back to issueOrder phase for current build
            previous();
        }
    }

    /**
     * Goes to the next phase
     */
    @Override
    public void next() {
        endGame();
    }

    /**
     * Goes to the previous phase
     */
    @Override
    public void previous() {
        d_Ml.setPhase(new IssueOrder(d_Ml));
    }
}
