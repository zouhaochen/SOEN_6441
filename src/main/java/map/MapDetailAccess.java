package map;

import java.io.File;

public class MapDetailAccess {

    File l_file = new File("test_02.map");
    MapLineAccess l_mla = new MapLineAccess();

    public int continentline(File p_file){
        int l_tlines = l_mla.getlines(p_file);
        int l_continentlines = l_mla.getcontinentlines(p_file);
        int l_countrylines = l_mla.getcountrylines(p_file);
        int l_borderlines = l_mla.getborderlines(p_file);

        return 0;
    }
}
