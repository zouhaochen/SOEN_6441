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
        d_Ml.d_GameEngineController.awardReinforcement();
        d_Ml.issueOrders();
        next();
    }

    /**
     * Goes to the next phase
     */
    @Override
    public void next() {
        d_Ml.setPhase(new OrderExecution(d_Ml));
    }
}
