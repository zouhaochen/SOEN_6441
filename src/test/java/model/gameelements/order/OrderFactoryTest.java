package model.gameelements.order;

import model.GameData;
import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        GameData l_GameData = mock(GameData.class);
        when(l_GameData.getCountryByName(anyString())).thenReturn(new Country(l_Command[1]));
        OrderFactory.setGameData(l_GameData);

        Order l_Order = OrderFactory.CreateOrder(l_Command, l_Player);

        assertEquals("deploy", l_Order.getType().getLabel());
    }
}
