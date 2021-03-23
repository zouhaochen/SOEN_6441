package model.state.play;

import controller.MainPlayController;

public class IssueOrder extends MainPlay{
    public IssueOrder(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void issueOrder() {
        d_ml.d_GameEngineController.awardReinforcement();
        d_ml.issueOrders();
        next();
    }

    @Override
    public void next() {
        d_ml.setPhase(new OrderExecution(d_ml));
    }
}
