package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the method to convert a map into
 * a connected directed graph
 * @Auther: Haochen Zou
 * @version 1.0
 */
public class MapGraph {

    private static String d_DirName = "domination";

    /**
     * print table
     *
     * @param p_Filename file name
     * @throws Exception
     */
    public static void printTable(String p_Filename) throws Exception {
        BufferedReader l_bw = new BufferedReader(new FileReader(getFile(p_Filename)));
        BufferedReader l_bw2 = new BufferedReader(new FileReader(getFile(p_Filename)));
        String l_line = "";
        boolean l_border = false;
        boolean l_country = false;
        boolean l_continent = false;
        boolean l_flag = false;
        int l_num = 1;
        int l_count = 0;
        List<String> l_continentList = new ArrayList<>();
        //get line number
        while((l_line = l_bw2.readLine()) != null) {
            if(l_flag && !"".equals(l_line)) {
                l_count++;
            }
            if("[borders]".equals(l_line)) {
                l_flag = true;
            }
        }
        StringBuffer l_buff = new StringBuffer(" ");
        for(int l_i = 1;l_i <= l_count;l_i++) {
            l_buff.append(" "+l_i);
        }
        l_buff.append("\n"+l_num);
        Map<String,List<String>> l_map = new LinkedHashMap<>();
        while((l_line = l_bw.readLine()) != null) {
            //create table
            if(l_border && !"".equals(l_line)) {
                List<String> l_list = Arrays.asList(l_line.split(" "));
                for(int l_i = 1;l_i <= l_count;l_i++) {
                    if(l_list.contains(l_i+"")) {
                        for(int l_a = 0;l_a < (l_i+"").length();l_a++) {
                            l_buff.append(" ");
                        }
                        l_buff.append("x");
                    } else {
                        for(int l_a = 0;l_a < (l_i+"").length()+1;l_a++) {
                            l_buff.append(" ");
                        }
                    }
                }
                l_buff.append("\n"+(++l_num));
            }
            //get continent
            if(l_continent && !"".equals(l_line) && !"[countries]".equals(l_line)) {
                l_continentList.add(l_line.split(" ")[0]);
            }
            //get country
            if(l_country && !"".equals(l_line) && !"[borders]".equals(l_line)) {
                int l_conLine = Integer.parseInt(l_line.split(" ")[2]);
                String l_countryName = l_line.split(" ")[1];
                String l_continet = l_continentList.get(l_conLine - 1);
                if(l_map.get(l_continet) != null) {
                    l_map.get(l_continet).add(l_countryName);
                } else {
                    List<String> l_list = new ArrayList<>();
                    l_list.add(l_countryName);
                    l_map.put(l_continet, l_list);
                }
            }
            if("[continents]".equals(l_line)) {
                l_continent = true;
            }
            if("[countries]".equals(l_line)) {
                l_country = true;
                l_continent = false;
            }
            if("[borders]".equals(l_line)) {
                l_border = true;
                l_country = false;
            }
        }
        String l_str2 = "";
        for(Map.Entry<String, List<String>> l_en : l_map.entrySet()) {
            String l_key = l_en.getKey();
            List<String> l_value = l_en.getValue();
            l_str2 += l_key+":";
            for(String l_s : l_value) {
                l_str2 += " " + l_s;
            }
            l_str2 += "\n";
        }
        String l_str = l_buff.substring(0,l_buff.toString().lastIndexOf("\n"));
        System.out.println(l_str+"\n\n"+l_str2);
        l_bw.close();
        l_bw2.close();
    }

    /**
     * Get a map file from an existing "domination" map file
     *
     * @param p_Filename path to file
     * @throws IOException if file not found or cannot read
     */
    private static File getFile(String p_Filename) throws IOException {
        File l_F = new File("");
        String l_path = l_F.getCanonicalPath();
        File l_F2 = new File(l_path + "/" + d_DirName);
        if (!l_F2.exists()) {
            l_F2.mkdir();
        }
        File l_F3 = new File(l_F2.getAbsolutePath() + "/" + p_Filename);
        return l_F3;
    }
}
