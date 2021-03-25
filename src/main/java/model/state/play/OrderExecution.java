package model.state.play;

import controller.MainPlayController;

/**
 * The type Order execution.
 */
public class OrderExecution extends MainPlay {

    /**
     * Instantiates a new Order execution.
     *
     * @param p_ml the p ml
     */
    public OrderExecution(MainPlayController p_ml) {
        super(p_ml);
    }

    /**
     * Executes all orders.
     */
    @Override
    public void executeOrder() {
        d_Ml.executeAllOrders();
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
