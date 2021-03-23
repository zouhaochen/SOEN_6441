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
    }

    @Override
    public void next() {
        endGame();
    }
}
