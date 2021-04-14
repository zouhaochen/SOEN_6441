package model.state.play;

import controller.GameEngineController;
import controller.MainPlayController;
import model.GameData;
import model.gameelements.order.OrderFactory;

import java.io.*;
import java.util.Scanner;

/**
 * The type Load map.
 */
public class LoadMap extends Startup {

    /**
     * Instantiates a new Load map.
     *
     * @param p_Ml the ml
     */
    public LoadMap(MainPlayController p_Ml) {
        super(p_Ml);
    }

    /**
     * Loads a map.
     */
    public void loadMap() {

        try {
            d_Ml.d_MapFile = new File(d_Ml.d_GameEngineController.getMapFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean l_MapRevertRequired = d_Ml.d_GameEngineController.isRevertRequired();

        d_Ml.d_GameData = new GameData(d_Ml.d_MapFile);
        d_Ml.d_GameData.setCurrentPhase(this);
        d_Ml.d_GameEngineController = new GameEngineController(d_Ml.d_GameData);
        OrderFactory.setGameData(d_Ml.d_GameData);
        try {
            d_Ml.d_GameData.loadMap();

            // revert domination map to conquest map
            if (l_MapRevertRequired) {
                d_Ml.d_GameEngineController.revertDominationToConquest(d_Ml.d_GameData.d_MapFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        d_Ml.getDLogEntryBuffer().updateFile();

        next();
    }

    /**
     * Loads a game.
     */
    public void loadGame(){
        Scanner l_Scanner = new Scanner(System.in);
        String l_Command = "";
        System.out.println("Please type in your command: loadgame filename");
        l_Command = l_Scanner.nextLine();
        String[] l_filename = null;
        if(l_Command.startsWith("loadgame ")){
            l_filename = l_Command.split(" ");
        }
        else{
            System.out.println("invalid command");
        }
        //get file name
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("gamefile" + "/" + l_filename[1] + ".txt"));
            GameData l_GameData=(GameData)ois.readObject();              //读出对象
            d_Ml.d_GameData = l_GameData;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        d_Ml.d_GameData.setCurrentPhase(new IssueOrder(d_Ml));
        d_Ml.d_GameEngineController = new GameEngineController(d_Ml.d_GameData);
        OrderFactory.setGameData(d_Ml.d_GameData);
        nextLoadGame();

    }
    /**
     * Showing map is invalid.
     */
    @Override
    public void showMap() {
        printInvalidCommandMessage();
    }

    /**
     * Goes to the next phase.
     */
    @Override
    public void next() {
        d_Ml.setPhase(new AddPlayer(d_Ml));
    }

    /**
     * Goes to the next phase.
     */
    @Override
    public void nextLoadGame() {
        d_Ml.setPhase(new IssueOrder(d_Ml));
    }
}
