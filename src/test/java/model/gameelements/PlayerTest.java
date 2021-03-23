package model.gameelements;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Player test.
 */
public class PlayerTest {


    /**
     * The player to be tested.
     */
    private Player d_Player;

    /**
     * Sets up the objects needed in tests.
     */
    @Before
    public void setup() {
        d_Player = new Player("Red");
    }

    /**
     * print ok when test is passed
     */
    @After
    public void checked(){
        System.out.println("ok");
    }

    /**
     * Test deploy army.
     */
    @Test
    public void testDeployArmy() {
        d_Player.setReinforcementArmies(5);

        boolean l_Result = d_Player.deployReinforcementArmies(4);

        assertTrue(l_Result);
        assertEquals(1, d_Player.getReinforcementArmies());
    }

    /**
     * Test over deploy army.
     */
    @Test
    public void testOverDeployArmy() {
        d_Player.setReinforcementArmies(5);

        boolean l_Result = d_Player.deployReinforcementArmies(8);

        assertFalse(l_Result);
        assertEquals(0, d_Player.getReinforcementArmies());
    }
}
