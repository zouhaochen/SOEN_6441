package model.gameelements.order;

import model.gameelements.Player;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

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
     * print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }


    /**
     * order test
     */
    @Test
    public void diplomaSetupTest() {
        d_TestPlayer1 = new Player("red");
        d_TestPlayer2 = new Player("blue");
        d_TestDiplomacyOrder = new DiplomacyOrder(d_TestPlayer1, d_TestPlayer2);

//        d_TestDiplomacyOrder.execute();
        d_TestPlayer1.setPlayerDiplomacy(d_TestPlayer2);
        d_TestPlayer2.setPlayerDiplomacy(d_TestPlayer1);

        int[] l_Array1 = new int[2];
        l_Array1[0] = d_TestPlayer1.getId();
        l_Array1[1] = d_TestPlayer2.getId();
        System.out.println("test Diploma from player 1");
        assertArrayEquals(l_Array1, d_TestPlayer1.getPlayerDiplomacy());

        int[] l_Array2 = new int[2];
        l_Array2[0] = d_TestPlayer2.getId();
        l_Array2[1] = d_TestPlayer1.getId();
        System.out.println("test Diploma from player 2");
        assertArrayEquals(l_Array2,d_TestPlayer2.getPlayerDiplomacy());
    }

}
