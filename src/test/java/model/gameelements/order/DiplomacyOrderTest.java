package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Diplomacy order test
 */
public class DiplomacyOrderTest {

    /**
     * Test player 1
     */
    private Player d_TestPlayer1;

    /**
     * Test player 2
     */
    private Player d_TestPlayer2;


    /**
     * test obj
     */
    private DiplomacyOrder d_TestDiplomacyOrder;

    /**
     * The country canada.
     */
    private Country d_Canada = new Country("Canada");
    /**
     * The country japan.
     */
    private Country d_Japan = new Country("Japan");

    /**
     * Sets .
     */
    @Before
    public void setup() {
        d_TestPlayer1 = new Player("red");
        d_TestPlayer2 = new Player("blue");
        d_TestDiplomacyOrder = new DiplomacyOrder(d_TestPlayer1, d_TestPlayer2);
    }

    /**
     * Print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }


    /**
     * Test order invalid given no card.
     */
    @Test
    public void testOrderInvalidGivenNoCard() {
        assertFalse(d_TestDiplomacyOrder.valid());
    }

    /**
     * unit test for check order valid
     */
    @Test
    public void testOrderValid() {
        // when
        d_TestPlayer1.getCards().add(Card.NEGOTIATE);
        d_TestPlayer1.assignCountry(d_Canada);
        d_TestPlayer2.assignCountry(d_Japan);

        // then
        assertTrue(d_TestDiplomacyOrder.valid());
    }

}
