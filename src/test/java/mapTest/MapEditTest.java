package mapTest;

import model.map.MapEdit;
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
        int l_TestFlag1 = MapEdit.flagEditMap("editmap ");
        assertEquals(1, l_TestFlag1);
    }

    /**
     * test open exist model.map
     */
    @Test
    public void testEditMapExist() {
        int l_TestFlag2 = MapEdit.flagEditMap("editmap testmap.model.map");
        assertEquals(2, l_TestFlag2);
    }

    /**
     * test open new model.map
     *
     * @throws IOException not find file
     */
    @Test
    public void testEditMapNew() throws IOException {

        int l_TestFlag3 = MapEdit.flagEditMap("editmap testnewmap.model.map");
        assertEquals(3, l_TestFlag3);
        File l_File = MapEdit.getFile("testnewmap.model.map");
        l_File.delete();
    }
}