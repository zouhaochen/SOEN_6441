package model.gameelements.order;

import model.gameelements.Player;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Diplomacy order test
 */
public class DiplomacyOrderTest {

    /**
     * Test player 1
     */
    Player d_TestPlayer1;

    /**
     * Test player 2
     */
    Player d_TestPlayer2;


    /**
     * test obj
     */
    DiplomacyOrder d_TestDiplomacyOrder;

    /**
     * Print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }


    /**
     * Order test
     */
    @Test
    public void diplomaSetupTest() {
        d_TestPlayer1 = new Player("red");
        d_TestPlayer2 = new Player("blue");
        d_TestDiplomacyOrder = new DiplomacyOrder(d_TestPlayer1, d_TestPlayer2);

        d_TestPlayer1.getUnattackablePlayers().add(d_TestPlayer2.getId());
        d_TestPlayer2.getUnattackablePlayers().add(d_TestPlayer1.getId());

        assertTrue(d_TestPlayer1.getUnattackablePlayers().contains(d_TestPlayer2.getId()));
        assertTrue(d_TestPlayer2.getUnattackablePlayers().contains(d_TestPlayer1.getId()));

    }

}
