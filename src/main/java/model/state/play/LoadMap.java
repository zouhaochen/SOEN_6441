package model.state.play;

import controller.GameEngineController;
import controller.MainPlayController;
import model.GameData;
import model.gameelements.order.OrderFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Load map.
 */
public class LoadMap extends Startup {

    /**
     * list of map files that players are used.
     */
    public List<File> d_ListOfMapFiles = new ArrayList<>();

    /**
     * the current map to play
     */
    private File d_CurrentMap;

    /**
     * map id
     */
    private int d_MapCounter = 0;

    /**
     * flag to show whether user have already setup the list of map to play
     * if true, game wont let user to input more map in list
     * if false , user can still insert map into list
     */
    private boolean d_MapInputDoneFlag = false;

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

        if (!d_MapInputDoneFlag) {
            stringMapPathInputProcess();
        }
        getNextMap();
        System.out.println("Notice: The Current map is " + d_CurrentMap.getName());
        d_Ml.d_MapFile = d_CurrentMap;

        d_Ml.d_GameData = new GameData(d_Ml.d_MapFile);
        d_Ml.d_GameData.setCurrentPhase(this);
        d_Ml.d_GameEngineController = new GameEngineController(d_Ml.d_GameData);
        OrderFactory.setGameData(d_Ml.d_GameData);
        try {
            d_Ml.d_GameData.loadMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        d_Ml.getDLogEntryBuffer().updateFile();

        next();
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
        // is startup is finished, user wont need add more player since already done.
        if(!d_MapInputDoneFlag){
            d_MapInputDoneFlag = true;
            d_Ml.setPhase(new AddPlayer(d_Ml));
        }else{
            d_Ml.setPhase(new AssignCountry(d_Ml));
        }

    }


    /**
     * get user map path list string input and store as real map file in tournament game map list
     */
    public void stringMapPathInputProcess() {
        // local key-in scanner
        Scanner l_Scanner = new Scanner(System.in);

        while(d_ListOfMapFiles.size()<=5){
            try {
                //preprocess path
                File l_NewMap = new File(d_Ml.d_GameEngineController.getMapFilePath());

                // use map file getter to get the map and add to the map list
                d_ListOfMapFiles.add(l_NewMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("===== Insert Map to list, 'n' to stop insert map, 'y' to keep insert =====");
            String keyin=l_Scanner.nextLine();
            if(keyin.equalsIgnoreCase("n")){
                break;
            }
        }
        System.out.println("Successfully insert [" + d_ListOfMapFiles.size() + "] Map in game");
        showCurrentMapList();

    }

    /**
     * function show the tournament map list
     */
    public void showCurrentMapList() {
        System.out.println("Notice: Tournament Map List: ");
        for (File l_EachMapFile : d_ListOfMapFiles) {
            System.out.print(l_EachMapFile.getName() + " ");
        }
        System.out.println("");
    }


    /**
     * get next map to play in map list
     */
    public void getNextMap() {
        // check whether list is empty or counter out of bound
        if (!d_ListOfMapFiles.isEmpty() && d_MapCounter < d_ListOfMapFiles.size()) {
            // get obj index next one
            d_CurrentMap = d_ListOfMapFiles.get(d_MapCounter);
            d_MapCounter++;
        } else {
            System.out.println("Error: Map List Empty or out of bound");
            // if just out of bound iterate back to first map in list
            if (!d_ListOfMapFiles.isEmpty()) {
                d_CurrentMap = d_ListOfMapFiles.get(0);
                // reset counter id 0
                d_MapCounter = 0;
            }
        }

    }
}
