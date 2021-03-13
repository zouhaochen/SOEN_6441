package model.map;

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
 * This is the method to convert a model.map into
 * a connected directed graph
 *
 * @author Haochen Zou
 * @version 1.0
 */
public class MapGraph {

    /**
     * The Map File Directory prefix
     */
    private static String D_DIRNAME = "domination";

    /**
     * print table
     *
     * @param p_Filename file name
     * @throws Exception if file is not found
     */
    public static void printTable(String p_Filename) throws Exception {
        BufferedReader l_Bw = new BufferedReader(new FileReader(getFile(p_Filename)));
        BufferedReader l_Bw2 = new BufferedReader(new FileReader(getFile(p_Filename)));
        String l_Line = "";
        boolean l_Border = false;
        boolean l_Country = false;
        boolean l_Continent = false;
        boolean l_Flag = false;
        int l_Num = 1;
        int l_Count = 0;
        List<String> l_ContinentList = new ArrayList<>();
        //get line number
        while ((l_Line = l_Bw2.readLine()) != null) {
            if (l_Flag && !"".equals(l_Line)) {
                l_Count++;
            }
            if ("[borders]".equals(l_Line)) {
                l_Flag = true;
            }
        }
        StringBuffer l_Buff = new StringBuffer(" ");
        for (int l_I = 1; l_I <= l_Count; l_I++) {
            l_Buff.append(" " + l_I);
        }
        l_Buff.append("\n" + l_Num);
        Map<String, List<String>> l_Map = new LinkedHashMap<>();
        while ((l_Line = l_Bw.readLine()) != null) {
            //create table
            if (l_Border && !"".equals(l_Line)) {
                List<String> l_List = Arrays.asList(l_Line.split(" "));
                for (int l_I = 1; l_I <= l_Count; l_I++) {
                    if (l_List.contains(l_I + "")) {
                        for (int l_A = 0; l_A < (l_I + "").length(); l_A++) {
                            l_Buff.append(" ");
                        }
                        l_Buff.append("x");
                    } else {
                        for (int l_A = 0; l_A < (l_I + "").length() + 1; l_A++) {
                            l_Buff.append(" ");
                        }
                    }
                }
                l_Buff.append("\n" + (++l_Num));
            }
            //get continent
            if (l_Continent && !"".equals(l_Line) && !"[countries]".equals(l_Line)) {
                l_ContinentList.add(l_Line.split(" ")[0]);
            }
            //get country
            if (l_Country && !"".equals(l_Line) && !"[borders]".equals(l_Line)) {
                int l_ConLine = Integer.parseInt(l_Line.split(" ")[2]);
                String l_CountryName = l_Line.split(" ")[1];
                String l_Continet = l_ContinentList.get(l_ConLine - 1);
                if (l_Map.get(l_Continet) != null) {
                    l_Map.get(l_Continet).add(l_CountryName);
                } else {
                    List<String> l_List = new ArrayList<>();
                    l_List.add(l_CountryName);
                    l_Map.put(l_Continet, l_List);
                }
            }
            if ("[continents]".equals(l_Line)) {
                l_Continent = true;
            }
            if ("[countries]".equals(l_Line)) {
                l_Country = true;
                l_Continent = false;
            }
            if ("[borders]".equals(l_Line)) {
                l_Border = true;
                l_Country = false;
            }
        }
        String l_Str2 = "";
        for (Map.Entry<String, List<String>> l_En : l_Map.entrySet()) {
            String l_Key = l_En.getKey();
            List<String> l_Value = l_En.getValue();
            l_Str2 += l_Key + ":";
            for (String l_S : l_Value) {
                l_Str2 += " " + l_S;
            }
            l_Str2 += "\n";
        }
        String l_Str = l_Buff.substring(0, l_Buff.toString().lastIndexOf("\n"));
        System.out.println(l_Str + "\n\n" + l_Str2);
        l_Bw.close();
        l_Bw2.close();
    }

    /**
     * Get a model.map file from an existing "domination" model.map file
     *
     * @param p_Filename path to file
     * @throws IOException if file not found or cannot read
     * @return File
     */
    private static File getFile(String p_Filename) throws IOException {
        File l_F = new File("");
        String l_Path = l_F.getCanonicalPath();
        File l_F2 = new File(l_Path + "/" + D_DIRNAME);
        if (!l_F2.exists()) {
            l_F2.mkdir();
        }
        File l_F3 = new File(l_F2.getAbsolutePath() + "/" + p_Filename);
        return l_F3;
    }
}
