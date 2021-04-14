package model.state;

import controller.MainPlayController;
import model.GameData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * State of the State pattern. Here implemented as a abstract class.
 * <p>
 * In this example, the states represent states in the board game Risk.
 * There are many states, and even a hierarchy of states:
 * <p>
 * Phase
 * Edit
 * Play
 * Startup
 * LoadMap
 * AddPlayer
 * AssignCountry
 * MainPlay
 * IssueOrder
 * OrderExecution
 * End
 * <p>
 * In each state, nextState() is defined so that it goes down in
 * the above list, except for Fortify, which goes back to
 * Reinforcement state.
 */
public class Phase implements Serializable {

    /**
     * Contains a reference to the State of the GameEngine
     * so that the state object can change the state of
     * the GameEngine to transition between states.
     */
    protected MainPlayController d_Ml;

    static String GAMEFILE = "gamefile";

    private static final long serialVersionUID=2656653232L;
    /**
     * Instantiates a new Phase.
     *
     * @param p_Ml the p ml
     */
    public Phase(MainPlayController p_Ml) {

        d_Ml = p_Ml;

    }

    // common commands

    /**
     * Load map.
     */
    public void loadMap() {
        printInvalidCommandMessage();
    }

    /**
     * Show map.
     */
    public void showMap() {
        printInvalidCommandMessage();
    }

    // Edit map commands

    /**
     * Edit map.
     */
    public void editMap() {
        printInvalidCommandMessage();
    }

    /**
     * Save map.
     */
    public void saveMap() {
        printInvalidCommandMessage();
    }

    // game setup commands
    // Play commands

    /**
     * Sets players.
     */
    public void setPlayers() {
        printInvalidCommandMessage();
    }

    /**
     * Assign countries.
     */
    public void assignCountries() {
        printInvalidCommandMessage();
    }

    /**
     * Issue order.
     */
    public void issueOrder() {
        printInvalidCommandMessage();
    }

    /**
     * Execute order.
     */
    public void executeOrder() {
        printInvalidCommandMessage();
    }

    /**
     * Deploy.
     */
    public void deploy() {
        printInvalidCommandMessage();
    }

    /**
     * End game.
     */
    public void endGame() {
        d_Ml.setPhase(new End(d_Ml));
        System.out.println();
        System.out.println("Exit the game!");
    }

    /**
     * Next.
     */
    public void next() {
        printInvalidCommandMessage();
    }
    /**
     * Next.
     */
    public void nextLoadGame() {
        printInvalidCommandMessage();
    }

    /**
     * Previous.
     */
    public void previous() {
        printInvalidCommandMessage();
    }

    /**
     * Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in phase " + this.getClass().getSimpleName());
    }

    /**
     * Load Game.
     */
    public void loadGame() {
        printInvalidCommandMessage();
    }


    /**
     * save game
     * @param p_GameData get game data as object
     * @return return true if current game can be saved
     */
    public boolean saveGame(GameData p_GameData) {


        boolean l_Saved = false;
        Scanner l_Scanner = new Scanner(System.in);
        String l_Command = "";
        System.out.println("Please type in your command: savegame filename");
        l_Command = l_Scanner.nextLine();
        String[] l_filename = null;
        if(l_Command.startsWith("savegame ")){
            l_filename = l_Command.split(" ");
        }
        else{
            System.out.println("invalid command");
        }
        try
        {
            FileOutputStream l_saveFile=new FileOutputStream(GAMEFILE + "/" + l_filename[1] + ".txt");
            ObjectOutputStream l_Save = new ObjectOutputStream(l_saveFile);
            l_Save.writeObject(p_GameData);
            l_Save.flush();
            l_Save.close();
            System.out.println("write object success!");
            l_Saved=true;
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
            l_Saved=false;
        }
        return l_Saved;
    }
}
