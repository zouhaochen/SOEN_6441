package model.state;

/**
 *
 */
public class Reinforcement extends MainPlay {

    Reinforcement(MainLoop p_ml) {
        super(p_ml);
    }

    public void reinforce() {
        System.out.println("reinforcement done");
        d_ml.setPhase(new End(d_ml));
    }

    public void attack() {
        printInvalidCommandMessage();
    }

    public void fortify() {
        printInvalidCommandMessage();
    }

    public void next() {
        d_ml.setPhase(new End(d_ml));
    }
}