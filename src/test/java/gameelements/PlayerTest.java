package gameelements;

import command.CommandValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * The type Player test.
 */
public class PlayerTest {


    /**
     * The player to be tested.
     */
    private Player d_Player;
    /**
     * The command validator.
     */
    private CommandValidator d_CommandValidator;

    /**
     * Sets up the objects needed in tests.
     */
    @Before
    public void setup() {
        d_CommandValidator = mock(CommandValidator.class);
        d_Player = new Player("Red", d_CommandValidator);
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
