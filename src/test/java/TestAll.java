
import controller.SaveGameTest;
import gamePlayTest.GameDataTest;
import gamePlayTest.GameEngineTest;
import mapTest.*;
import model.gameelements.order.AllOrderTest;
import modeltest.LogEntryBufferTest;
import model.gameelements.PlayerTest;
import model.gameelements.state.StateTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * the test all class used for running all test class
 */
@RunWith(Suite.class)
@SuiteClasses({

        AllMapTest.class,
        AllOrderTest.class,
        LogEntryBufferTest.class,
        ViewTest.class,
        GameDataTest.class,
        GameEngineTest.class,
        PlayerTest.class,
        StateTest.class,
        SaveGameTest.class,


})
public class TestAll {

}
