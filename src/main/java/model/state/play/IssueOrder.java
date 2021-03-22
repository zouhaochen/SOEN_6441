package model.state.play;

import controller.MainPlayController;

public class IssueOrder extends MainPlay{
    public IssueOrder(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void issueOrder() {
        d_ml.d_GameEngineController.awardReinforcement();

        next();
    }

    private void awardReinforcement() {
        d_ml.d_GameEngineController.awardReinforcement();

    }
}
