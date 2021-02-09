package mapTest;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static org.junit.Assert.*;

import map.*;
import org.junit.Test;

/**
 * This class is for testing MapInformationAccess
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapInformationAccessTest {

    @Test
    /**
     * This method is test case1 for total armies
     */
    public void test(){
        MapInformationAccess case1 = new MapInformationAccess();
        int a = case1.getlines();
        assertEquals(35,a);
    }

}
