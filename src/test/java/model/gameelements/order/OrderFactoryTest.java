package model.gameelements.order;

import model.gameelements.Player;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * The test class for OrderFactory.
 */
public class OrderFactoryTest {

    /**
     * print ok when test is passed
     */
    @After
    public void checked(){
        System.out.println("ok");
    }

    /**
     * Test deploy order creation.
     */
    @Test
    public void testDeployOrderCreation() {
        Player l_Player = mock(Player.class);
        String[] l_Command = {"deploy", "Canada", "2"};

        Order l_Order = OrderFactory.CreateOrder(l_Command, l_Player);

        assertEquals("deploy", l_Order.getType().getLabel());
    }
}
