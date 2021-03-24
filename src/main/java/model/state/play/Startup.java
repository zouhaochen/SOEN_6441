package model.state.play;

import controller.MainPlayController;
import model.map.MapGraph;

public class Startup extends Play {

    public Startup(MainPlayController p_ml) {
        super(p_ml);
    }

    /**
     * to display the map for current phase.
     */
    public void showMap() {
        try {
            System.out.println("\nMain Graph show below:");
            MapGraph.printTable(d_ml.d_MapFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
