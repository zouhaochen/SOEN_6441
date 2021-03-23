package mapTest;

import gamePlayTest.GameDataTest;
import gamePlayTest.GameEngineTest;
import mapTest.*;
import modeltest.LogEntryBufferTest;
import model.gameelements.PlayerTest;
import model.gameelements.order.OrderFactoryTest;
import model.gameelements.state.StateTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * the test all class used for running all test class
 */
@RunWith(Suite.class)
@SuiteClasses({

        MapDetailAccessTest.class,
        MapListingTest.class,
        MapValidateTest.class,
})
public class AllMapTest {
}
