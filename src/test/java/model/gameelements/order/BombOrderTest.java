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
 * Bomb order test
 */
public class BombOrderTest {

    /**
     * Player
     */
    Player d_player;
    /**
     * Country
     */
    Country d_country;
    /**
     * Blockade order
     */
    BombOrder d_order;

    /**
     * Print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }

    /**
     * This method can set up game context before test cases begin.
     */
    @Before
    public void setup() {
        d_player = new Player("player1");
        d_country = new Country("Canada");
        d_country.setOwner(d_player);
        d_order = new BombOrder(d_player, d_country);
    }

    /**
     * This method tests the valid method of BlockadeOrder class
     */
    @Test
    public void testOrderInvalidGivenPlayerHasNoCard() {
        assertFalse(d_order.valid());
    }


    @Test
    public void testOrderValid() {

        Country l_country = new Country("Japan");
        Player l_player = new Player("Zhou");
        l_country.addBoarderConnection(d_country);
        l_country.setOwner(l_player);
        l_country.setArmies(10);
        d_player.getCards().add(Card.BOMB);
        d_player.assignCountry(l_country);

        assertTrue(d_order.valid());
    }
}
