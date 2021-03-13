
import gamePlayTest.GameDataTest;
import gamePlayTest.GameEngineTest;
import mapTest.*;
import model.map.MapCheck;
import model.map.MapListing;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

        MapCheckTest.class,
        MapDetailAccessTest.class,
        MapLineAccessTest.class,
        MapListingTest.class,
        MapValidateTest.class})

public class AllMapClassTest {

}
