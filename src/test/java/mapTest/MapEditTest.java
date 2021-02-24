package mapTest;

import map.MapEdit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/** This class is for test MapEditor
 * @Auther: Haochen Zou
 * @version: 1.0
 */
public class MapEditTest {
    @Test
    /**
     * test open null
     */
    public void testEditMapEmpty() throws IOException {
        int l_testFlag1 = map.MapEdit.flagEditMap("editmap ");
        assertEquals(1, l_testFlag1);
    }
    @Test
    /**
     * test open exist map
     */
    public void testEditMapExist() throws IOException {
        int l_testFlag2 = map.MapEdit.flagEditMap("editmap testmap.map");
        assertEquals(2, l_testFlag2);
    }
    @Test
    /**
     * test open new map
     */
    public void testEditMapNew() throws IOException {
        int l_testFlag3 = map.MapEdit.flagEditMap("editmap testnewmap.map");
        assertEquals(3, l_testFlag3);
    }
}