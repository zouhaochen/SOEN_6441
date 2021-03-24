package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for BlockadeOrder class
 */
public class BlockadeOrderTest {
	/** player */
	Player d_player;
	/** country */
	Country d_country;
	/** blockade order */
	BlockadeOrder d_order;

	/**
	 * print ok when test is passed
	 */
	@After
	public void checked(){
		System.out.println("ok");
	}
	
	/**
	 * This method can set up game context before test cases begin.
	 */
	@Before
	public void setup() {
		d_player=new Player("player1");
		d_player.getCards();
		d_country=new Country("country1");
		d_order=new BlockadeOrder(d_player, 0);
	}
	
	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
	@Test
	public void testValid() {
	}
}
