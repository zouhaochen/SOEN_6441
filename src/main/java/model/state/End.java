package model.state;


import controller.MainPlayController;

public class End extends Phase {

    End(MainPlayController p_ml) {
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

    public void deploy() {
        printInvalidCommandMessage();
    }

    public void advance() {
        printInvalidCommandMessage();
    }

    public void cards() {
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