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
        d_ml.executeAllOrders();
        d_ml.resetAttackableState();
        d_ml.getDLogEntryBuffer().updateFile();

        if (d_ml.d_GameEngineController.checkGameIsOver()) {
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
        d_ml.setPhase(new IssueOrder(d_ml));
    }
}
