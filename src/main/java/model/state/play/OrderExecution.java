package model.state.play;

import controller.MainPlayController;

public class OrderExecution extends MainPlay {

    public OrderExecution(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void executeOrder() {
        d_ml.executeAllOrders();
    }

    @Override
    public void next() {
        endGame();
    }
}
