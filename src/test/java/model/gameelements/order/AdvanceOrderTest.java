package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AdvanceOrderTest {

	private Player d_attacker;
	private Player d_defender;
	private Country d_attackCountry;
	private Country d_defendCountry;
	
	@Before
	public void setup() {

		/**
		 * create attacker and defender
		 */
		d_attacker = new Player("attacker");
		d_defender = new Player("defender");

		/**
		 * create attack country and defend country
		 */
		d_attackCountry = new Country("attackCountry");
		d_defendCountry = new Country("defendCountry");

		/**
		 * create border
		 */
		d_attackCountry.addBoarderConnection(d_defendCountry);
		d_defendCountry.addBoarderConnection(d_attackCountry);

		/**
		 * add country to country in control hash map
		 */
		d_attacker.getCountriesInControl().put("attackCountry", d_attackCountry);
		d_defender.getCountriesInControl().put("defendCountry", d_defendCountry);
		d_attackCountry.setOwner(d_attacker);
		d_defendCountry.setOwner(d_defender);

/*		*//**
		 * add order to game engine?
		 *//*
		d_gameEngine.getCountries().put(1, d_attackCountry);
		d_gameEngine.getCountries().put(2, d_defendCountry);*/
	}
	
	@Test
	public void testAttackerControlDefender() {

		/**
		 * give armies to both countries
		 */
		d_attackCountry.setArmies(10);
		d_defendCountry.setArmies(2);

		/**
		 * execute advance order
		 */
		new AdvanceOrder(d_attacker, d_attackCountry, d_defendCountry, 10).execute();
		
		//make sure attacker countried country
//		assertTrue(d_attacker.getCountriesInControl().size() == 2);
//		assertTrue(d_defender.getCountriesInControl().size() == 0);
		
		//make sure the attacking and defending countries lost armies
//		assertTrue(d_attackCountry.getArmies() == 0); //Attacker moves all armies to defender's country
//		assertTrue(d_defendCountry.getArmies() < 10); //The attacker moves all remaining armies (some should be lost due to fights)
	}
	
	@Test
	public void testAttackerControlDefenderWithArmyLeft() {

		/**
		 * give armies to both countries
		 */
		d_attackCountry.setArmies(10);
		d_defendCountry.setArmies(2);

		/**
		 * execute advance order
		 */
		new AdvanceOrder(d_attacker, d_attackCountry, d_defendCountry, 5).execute();
		
		//Make sure attacker conquered country
//		assertTrue(d_attacker.getCountriesInControl().size() == 2);
//		assertTrue(d_defender.getCountriesInControl().size() == 0);
		
		//Make sure the attacking and defending countries lost armies
//		assertTrue(d_attackCountry.getArmies() == 5); //Attacker move 500 armies to defender's country, but keeps 500 in attacking country
//		assertTrue(d_defendCountry.getArmies() < 5); //The attacker moves all remaining armies (some should be lost due to fights)
	}
	
	@Test
	public void testAttackerNotControlDefender() {

		/**
		 * give armies to both countries
		 */
		d_attackCountry.setArmies(2);
		d_defendCountry.setArmies(10);


		/**
		 * execute advance order
		 */
		new AdvanceOrder(d_attacker, d_attackCountry, d_defendCountry, 2).execute();
		
		//Make sure attacker did not conquer country
//		assertTrue(d_attacker.getCountriesInControl().size() == 1);
//		assertTrue(d_defender.getCountriesInControl().size() == 1);
		
		//Make sure the attacking and defending countries lost armies
//		assertTrue(d_attackCountry.getArmies() < 2); //Attacker should lose some armies
//		assertTrue(d_defendCountry.getArmies() < 10); //Some should be lost due to fights
	}

}
