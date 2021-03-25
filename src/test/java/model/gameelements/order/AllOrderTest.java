package model.gameelements.order;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * the test all class used for running all test class
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdvanceOrderTest.class,
        BlockadeOrderTest.class,
        DiplomacyOrderTest.class,
        OrderFactoryTest.class,
        AirliftOrderTest.class,
        BombOrderTest.class
})
public class AllOrderTest {
}
