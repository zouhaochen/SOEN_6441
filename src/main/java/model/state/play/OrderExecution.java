package model.state.play;

import controller.MainPlayController;

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

        // add NEUTRAL player for Blockade
        if (d_Ml.d_GameData.getNeutralPlayer() != null && d_Ml.d_GameData.getPlayerByName(d_Ml.d_GameData.getNeutralPlayer().getColour()) == null) {
            d_Ml.d_GameData.getPlayerList().add(d_Ml.d_GameData.getNeutralPlayer());
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
