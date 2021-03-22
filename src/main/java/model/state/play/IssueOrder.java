package model.state.play;

import controller.MainPlayController;

public class IssueOrder extends MainPlay{
    public IssueOrder(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void issueOrder() {

        next();
    }
}
