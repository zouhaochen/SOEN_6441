package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Airlift order test
 */
public class AirliftOrderTest {

    /**
     * player
     */
    Player d_player;
    /**
     * From country
     */
    Country d_FromCountry;

    /**
     * To country
     */
    Country d_ToCountry;

    /**
     * Number of armies
     */
    int d_NumOfArmies;

    /**
     * necessary parameter to create airlift order
     */
    OrderInfo d_OrderInfo;

    /**
     * blockade order
     */
    AirliftOrder d_AirliftOrder;


    /**
     * necessary setup before each test
     */
    @Before
    public void testSetup() {

        d_player = new Player("player");
        d_FromCountry = new Country("Canada");
        d_ToCountry = new Country("USA");
        d_NumOfArmies=5;


        d_OrderInfo = new OrderInfo();
        d_OrderInfo.setInitiator(d_player);
        d_OrderInfo.setDeparture(d_FromCountry);
        d_OrderInfo.setDestination(d_ToCountry);
        d_OrderInfo.setNumberOfArmy(d_NumOfArmies);

    }

    /**
     * print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }

    /**
     * to check player has no airlift card but he/she want to use
     */
    @Test
    public void hasCardTest() {
        d_AirliftOrder=new AirliftOrder(d_OrderInfo);
        // should return invalid because player has no airlift card in hand.
        assertFalse(d_AirliftOrder.valid());
    }

    /**
     * destination country or departure country is null check
     */
    @Test
    public void countryIsNullTest(){
        // set destination country obj be null
        d_OrderInfo.setDestination(null);
        // give player airlift card
        d_OrderInfo.getInitiator().receiveNewCard(Card.AIRLIFT);
        d_AirliftOrder=new AirliftOrder(d_OrderInfo);
        // should return invalid because destination country is null
        assertFalse(d_AirliftOrder.valid());
    }

    /**
     *  Test source country or target country does not belongs to the player.
     */
    @Test
    public void countryBelongingTest(){
        // give player airlift card
        d_OrderInfo.getInitiator().receiveNewCard(Card.AIRLIFT);
        // give one country to test player
        d_OrderInfo.getInitiator().assignCountry(d_FromCountry);
        d_AirliftOrder=new AirliftOrder(d_OrderInfo);
        // should return invalid because cant airlift to country not belongs to player.
        assertFalse(d_AirliftOrder.valid());
    }

}
