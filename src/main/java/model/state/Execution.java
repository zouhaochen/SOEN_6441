package model.state;

public class Execution extends MainPlay {
    Execution(MainLoop p_ml) {
        super(p_ml);
    }

    @Override
    public void reinforce() {
        printInvalidCommandMessage();
    }

    @Override
    public void deployOrder() {
        printInvalidCommandMessage();
    }

    @Override
    public void fortify() {
        printInvalidCommandMessage();
    }

    @Override
    public void next() {
        d_ml.setPhase(new End(d_ml));
    }

}
