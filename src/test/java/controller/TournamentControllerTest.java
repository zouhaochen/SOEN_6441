package controller;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * The type Tournament controller test.
 */
public class TournamentControllerTest {

    /**
     * The tournament controller.
     */
    private TournamentController d_TournamentController;
    /**
     * The map file names.
     */
    private List<String> d_MapFileNames;

    /**
     * Sets .
     */
    @Before
    public void setup() {
        d_TournamentController = new TournamentController();
        d_MapFileNames = new ArrayList<>();
    }

    /**
     * Test process map.
     */
    @Test
    public void testProcessMap() {
        d_MapFileNames.add("02.map");
        d_MapFileNames.add("03.map");
        d_MapFileNames.add("04.map");

        d_TournamentController.stringMapPathInputProcess(d_MapFileNames);

        assertEquals(3, d_TournamentController.getListOfMapFiles().size());
    }

    /**
     * Test load map.
     */
    @Test
    public void testLoadMap() {
        d_MapFileNames.add("02.map");
        d_MapFileNames.add("03.map");
        d_MapFileNames.add("04.map");

        d_TournamentController.stringMapPathInputProcess(d_MapFileNames);

        d_TournamentController.loadMap(d_TournamentController.getListOfMapFiles().get(0));

        assertEquals("02.map", d_TournamentController.d_GameData.d_MapFile.getName());
    }
}
