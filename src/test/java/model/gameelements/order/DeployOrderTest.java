package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Deploy order test.
 */
public class DeployOrderTest {

    /**
     * The order info.
     */
    private OrderInfo d_OrderInfo;
    /**
     * The initiator.
     */
    private Player d_Initiator;
    /**
     * The country Canada.
     */
    private Country d_Canada = new Country("Canada");
    /**
     * The country Japan.
     */
    private Country d_Japan = new Country("Japan");

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        d_OrderInfo = new OrderInfo();
        d_Initiator = new Player("test");
        d_Initiator.setReinforcementArmies(3);
    }

    /**
     * Test order invalid given null destination.
     */
    @Test
    public void testOrderInvalidGivenNullDestination() {
        // given
        d_OrderInfo.setInitiator(d_Initiator);
        Order l_Order = new DeployOrder();
        l_Order.setOrderInfo(d_OrderInfo);

        // then
        assertFalse(l_Order.valid());
    }

    /**
     * Test order invalid given negative army number.
     */
    @Test
    public void testOrderInvalidGivenNegativeArmyNumber() {
        // given
        d_Initiator.assignCountry(d_Canada);
        d_OrderInfo.setInitiator(d_Initiator);
        d_OrderInfo.setDestination(d_Japan);
        d_OrderInfo.setNumberOfArmy(-3);
        Order l_Order = new DeployOrder();
        l_Order.setOrderInfo(d_OrderInfo);

        // then
        assertFalse(l_Order.valid());
    }

    /**
     * Test order invalid given destination not in control.
     */
    @Test
    public void testOrderInvalidGivenDestinationNotInControl() {
        // given
        d_Initiator.assignCountry(d_Canada);
        d_OrderInfo.setInitiator(d_Initiator);
        d_OrderInfo.setDestination(d_Japan);
        d_OrderInfo.setNumberOfArmy(5);
        Order l_Order = new DeployOrder();
        l_Order.setOrderInfo(d_OrderInfo);

        // then
        assertFalse(l_Order.valid());
    }

    /**
     * Test order valid.
     */
    @Test
    public void testOrderValid() {
        // given
        d_Initiator.assignCountry(d_Canada);
        d_OrderInfo.setInitiator(d_Initiator);
        d_OrderInfo.setDestination(d_Canada);
        d_OrderInfo.setNumberOfArmy(5);
        Order l_Order = new DeployOrder();
        l_Order.setOrderInfo(d_OrderInfo);

        // then
        assertTrue(l_Order.valid());
    }
}
