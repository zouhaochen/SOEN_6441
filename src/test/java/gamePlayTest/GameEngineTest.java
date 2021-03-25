package gamePlayTest;

import controller.GameEngineController;
import model.GameData;
import model.gameelements.Continent;
import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Game engine test unit
 */

public class GameEngineTest {

    /**
     * test model.map file
     */
    File d_File = new File("domination/germany.map");

    /**
     * test game data object
     */
    GameData d_GameData = new GameData(d_File);
    /**
     * test game engine object
     */
    GameEngineController d_GameEngine = new GameEngineController(d_GameData);

    /**
     * print ok when test is passed
     */
    @After
    public void checked() {
        System.out.println("ok");
    }

    /**
     * this method used to test GameEngineController Class
     */
    @Test
    public void testGameEngine() {
        System.out.println("Game Engine Test Start:");

    }

    /**
     * thie method used to test add and remove player to the game
     */
    @Test
    public void testAddRemovePlayer() {
        System.out.println("1.check add Player");
        d_GameEngine.addNewPlayer("red");
        System.out.println("2.check removed Player");
        d_GameEngine.removePlayer(d_GameEngine.d_GameData.getPlayerList().get(0));

    }


    /**
     * test show model.map function
     */
    @Test
    public void testShowMap() {
        System.out.println("3. check show model.map");
        d_GameEngine.addNewPlayer("black");
        Player l_Player = d_GameEngine.d_GameData.getPlayerList().get(0);
        try {
            d_GameEngine.d_GameData.loadMap();
        } catch (FileNotFoundException l_E) {
            l_E.printStackTrace();
        }
        Country l_Country1 = new Country("Ostfriesland");
        l_Country1.setArmies(10);
        l_Player.getCountriesInControl().put(l_Country1.getName(), l_Country1);
        d_GameEngine.showMap(l_Player);
    }

    /**
     * Test reinforcement armies calculation with bonus
     */
    @Test
    public void testReinforcementCalculationWithBonus() {
        System.out.println("4. check ReinforcementCalculationWithBonus");
        // given: one continent has only one country
        int l_ExpectedContinentValue = 8;
        Continent l_Continent = new Continent("NorthAmerica", l_ExpectedContinentValue);
        Country l_Country = new Country("Canada");

        // the player owns the continent
        Player l_Player = new Player("Red");
        l_Country.setOwner(l_Player);

        l_Continent.getCountries().put(l_Country.getName(), l_Country);
        List<Continent> l_ContinentList = new ArrayList<>();
        l_ContinentList.add(l_Continent);

        // mock and stub
        GameData l_GameData = mock(GameData.class);
        given(l_GameData.getContinentList()).willReturn((ArrayList<Continent>) l_ContinentList);
        GameEngineController l_GameEngine = new GameEngineController(l_GameData);

        // when
        int l_ReinforcementBonus = l_GameEngine.calculateReinforcement(l_Player);

        // then
        assertEquals(l_ExpectedContinentValue, l_ReinforcementBonus);

    }

    /**
     * Test reinforcement armies calculation without bonus
     */
    @Test
    public void testReinforcementCalculationWithoutBonus() {
        System.out.println("5. check ReinforcementCalculationWithoutBonus");
        // given: one continent has only one country
        int l_ContinentValue = 8;
        Continent l_Continent = new Continent("NorthAmerica", l_ContinentValue);
        Country l_Country = new Country("Canada");

        // the continent is not owned by the player to be tested
        Player l_Player = new Player("Red");
        Player l_AnotherPlayer = new Player("Blue");
        l_Country.setOwner(l_AnotherPlayer);

        l_Continent.getCountries().put(l_Country.getName(), l_Country);
        List<Continent> l_ContinentList = new ArrayList<>();
        l_ContinentList.add(l_Continent);

        // mock and stub
        GameData l_GameData = mock(GameData.class);
        given(l_GameData.getContinentList()).willReturn((ArrayList<Continent>) l_ContinentList);
        GameEngineController l_GameEngine = new GameEngineController(l_GameData);

        // when
        int l_ReinforcementBonus = l_GameEngine.calculateReinforcement(l_Player);

        // then
        assertEquals(3, l_ReinforcementBonus);

    }

    /**
     * unit test for Game Not Over part
     */
    @Test
    public void testGameNotOver() {
        System.out.println("6. check gameNotOver");
        // given: two players own at least one country
        Country l_Canada = new Country("Canada");
        Country l_Japan = new Country("Japan");

        Player l_Alice = new Player("Alice");
        Player l_Bob = new Player("Bob");
        l_Alice.assignCountry(l_Canada);
        l_Bob.assignCountry(l_Japan);

        List<Player> l_Players = new ArrayList<>();
        l_Players.add(l_Alice);
        l_Players.add(l_Bob);

        d_GameData.setPlayerList((ArrayList<Player>) l_Players);

        // when
        boolean l_Result = d_GameEngine.checkGameIsOver();

        // then
        assertFalse(l_Result);
    }

    /**
     * unit test for Game is Over part
     */
    @Test
    public void testGameIsOver() {
        System.out.println("7. check gameIsOver");
        // given: one player owns at least one country
        Country l_Canada = new Country("Canada");
        Country l_Japan = new Country("Japan");

        Player l_Alice = new Player("Alice");
        Player l_Bob = new Player("Bob");
        l_Alice.assignCountry(l_Canada);
        l_Alice.assignCountry(l_Japan);

        List<Player> l_Players = new ArrayList<>();
        l_Players.add(l_Alice);
        l_Players.add(l_Bob);

        d_GameData.setPlayerList((ArrayList<Player>) l_Players);

        // when
        boolean l_Result = d_GameEngine.checkGameIsOver();

        // then
        assertTrue(l_Result);
    }
}
