package model.state.play;

import controller.MainPlayController;

public class OrderExecution extends MainPlay {

    public OrderExecution(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void executeOrder() {
        d_ml.executeAllOrders();
        d_ml.getDLogEntryBuffer().updateFile();

        if (d_ml.d_GameEngineController.checkGameIsOver()) {
            // game ends
            next();
        } else {
            // go back to issueOrder phase for current build
            previous();
        }
    }

    @Override
    public void next() {
        endGame();
    }

    @Override
    public void previous() {
        d_ml.setPhase(new IssueOrder(d_ml));
    }
}
