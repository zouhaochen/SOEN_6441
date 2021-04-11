package mapTest;

import model.map.MapEdit;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * This class is for test MapEditor
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
        int l_TestFlag3 = MapEdit.flagEditMap("editmap testnewmap.map");
        assertEquals(2, l_TestFlag3);
        File l_File = MapEdit.getFile("testnewmap.map");
        l_File.delete();
    }
}