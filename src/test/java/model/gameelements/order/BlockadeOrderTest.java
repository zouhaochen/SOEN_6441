package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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
		d_player = new Player("player1");
		d_country = new Country("Canada");
		d_order = new BlockadeOrder(d_player, d_country);
	}

	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
	@Test
	public void testOrderInvalidGivenPlayerHasNoCard() {
		assertFalse(d_order.valid());
	}

	@Test
	public void testOrderInvalidGivenPlayerNotOwnTheCountry() {
		d_player.getCards().add(Card.BLOCKADE);
		d_player.assignCountry(new Country("Japan"));

		assertFalse(d_order.valid());
	}

	@Test
	public void testOrderValid() {
		d_player.getCards().add(Card.BLOCKADE);
		d_player.assignCountry(new Country("Japan"));
		d_player.assignCountry(d_country);

		assertTrue(d_order.valid());
	}
}
