package mapTest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * This class is for test MapEditor
 *
 * @author Haochen Zou
 * @version 1.0
 */
public class MapEditTest {
    /**
     * test open null
     */
    @Test
    public void testEditMapEmpty() {
        int l_testFlag1 = map.MapEdit.flagEditMap("editmap ");
        assertEquals(1, l_testFlag1);
    }

    /**
     * test open exist map
     */
    @Test
    public void testEditMapExist() {
        int l_testFlag2 = map.MapEdit.flagEditMap("editmap testmap.map");
        assertEquals(2, l_testFlag2);
    }

    /**
     * test open new map
     * @throws IOException not find file
     */
    @Test
    public void testEditMapNew() throws IOException {
        int l_testFlag3 = map.MapEdit.flagEditMap("editmap testnewmap.map");
        assertEquals(3, l_testFlag3);
        File l_file = map.MapEdit.getFile("testnewmap.map");
        l_file.delete();
    }
}