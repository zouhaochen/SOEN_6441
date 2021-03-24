package model.state.play;

import controller.MainPlayController;

/**
 * The type Issue order.
 */
public class IssueOrder extends MainPlay {
    /**
     * Instantiates a new Issue order.
     *
     * @param p_Ml the ml
     */
    public IssueOrder(MainPlayController p_Ml) {
        super(p_Ml);
    }

    /**
     * Allows players to issue orders
     */
    @Override
    public void issueOrder() {
        d_ml.d_GameEngineController.awardReinforcement();
        d_ml.issueOrders();
        next();
    }

    /**
     * Goes to the next phase
     */
    @Override
    public void next() {
        d_ml.setPhase(new OrderExecution(d_ml));
    }
}
