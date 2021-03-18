package model.state;

public class End extends Phase {

    End(MainLoop p_ml) {
        super(p_ml);
    }

    public void loadMap() {
        printInvalidCommandMessage();
    }

    public void showMap() {
        printInvalidCommandMessage();
    }

    public void editMap() {
        printInvalidCommandMessage();
    }

    public void saveMap() {
        printInvalidCommandMessage();
    }

    public void setPlayers() {
        printInvalidCommandMessage();
    }

    public void assignCountries() {
        printInvalidCommandMessage();
    }

    public void reinforce() {
        printInvalidCommandMessage();
    }

    public void IssureOrder() {
        printInvalidCommandMessage();
    }

    public void execute() {
        printInvalidCommandMessage();
    }

    public void endGame() {

    }

    public void next() {
        printInvalidCommandMessage();
    }

    public void previous() { printInvalidCommandMessage(); }
}