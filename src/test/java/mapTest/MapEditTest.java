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
        int l_TestFlag1 = map.MapEdit.flagEditMap("editmap ");
        assertEquals(1, l_TestFlag1);
    }

    /**
     * test open exist map
     */
    @Test
    public void testEditMapExist() {
        int l_TestFlag2 = map.MapEdit.flagEditMap("editmap testmap.map");
        assertEquals(2, l_TestFlag2);
    }

    /**
     * test open new map
     * @throws IOException not find file
     */
    @Test
    public void testEditMapNew() throws IOException {
        int l_TestFlag3 = map.MapEdit.flagEditMap("editmap testnewmap.map");
        assertEquals(3, l_TestFlag3);
        File l_File = map.MapEdit.getFile("testnewmap.map");
        l_File.delete();
    }
}