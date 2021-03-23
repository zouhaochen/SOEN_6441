package mapTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * test all map function
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MapDetailAccessTest.class,
        MapListingTest.class,
        MapValidateTest.class,
})
public class AllMapTest {
}
