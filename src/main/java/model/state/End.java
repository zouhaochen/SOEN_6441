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

    public void attack() {
        printInvalidCommandMessage();
    }

    public void fortify() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        printInvalidCommandMessage();
    }

    public void next() {
        printInvalidCommandMessage();
    }

    public void previous() { printInvalidCommandMessage(); }
}