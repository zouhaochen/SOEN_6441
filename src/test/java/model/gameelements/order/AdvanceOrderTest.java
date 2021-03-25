package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test advance order
 */
public class AdvanceOrderTest {

    /**
     * Player attacker
     */
    private Player d_attacker;
    /**
     * Player defender
     */
    private Player d_defender;
    /**
     * Attack country
     */
    private Country d_attackCountry;
    /**
     * Defend country
     */
    private Country d_defendCountry;

    /**
     * Print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }

    /**
     * Necessary setup before each test
     */
    @Before
    public void setup() {


        // Set attacker and defender
        d_attacker = new Player("attacker");
        d_defender = new Player("defender");

        // Set attack country and defend country
        d_attackCountry = new Country("attackCountry");
        d_defendCountry = new Country("defendCountry");

        // Set border
        d_attackCountry.addBoarderConnection(d_defendCountry);
        d_defendCountry.addBoarderConnection(d_attackCountry);


        // Add country to country in control hash map
        d_attacker.getCountriesInControl().put("attackCountry", d_attackCountry);
        d_defender.getCountriesInControl().put("defendCountry", d_defendCountry);
        d_attackCountry.setOwner(d_attacker);
        d_defendCountry.setOwner(d_defender);
    }

    @Test
    public void testAttackerControlDefender() {


        // Give armies to both countries
        d_attackCountry.setArmies(10);
        d_defendCountry.setArmies(0);


        // Execute advance order
        new AdvanceOrder(d_attacker, d_attackCountry, d_defendCountry, 10).execute();

        //Make sure attacker controlled country
        assertTrue(d_attacker.getCountriesInControl().size() == 2);
        assertTrue(d_defender.getCountriesInControl().size() == 0);

        //Make sure the attacking and defending countries lost armies
        //Move all attack armies to defend country
        assertTrue(d_attackCountry.getArmies() == 0);
    }

    @Test
    public void testAttackerControlDefenderWithArmyLeft() {

        // Give armies to both countries
        d_attackCountry.setArmies(10);
        d_defendCountry.setArmies(0);

        // Execute advance order
        new AdvanceOrder(d_attacker, d_attackCountry, d_defendCountry, 5).execute();

        //Make sure attacker controlled country
        assertTrue(d_attacker.getCountriesInControl().size() == 2);
        assertTrue(d_defender.getCountriesInControl().size() == 0);

        //Make sure attack and defend countries lost armies in war
        //Move 5 attack armies to defend country, keep 5 in attack country
        assertTrue(d_attackCountry.getArmies() == 5);
    }

    @Test
    public void testAttackerNotControlDefender() {

        // Give armies to both countries
        d_attackCountry.setArmies(0);
        d_defendCountry.setArmies(10);


        // Execute advance order
        new AdvanceOrder(d_attacker, d_attackCountry, d_defendCountry, 2).execute();

        //Make sure attack not control country
        assertTrue(d_attacker.getCountriesInControl().size() == 1);
        assertTrue(d_defender.getCountriesInControl().size() == 1);

        //Defend country doesn't lost some armies in war
        assertTrue(d_defendCountry.getArmies() == 10);
    }

}
