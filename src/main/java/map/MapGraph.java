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

public class MapGraph {

    private static String dirName = "domination";

    public static void main(String[] args) throws Exception {
        printTable("germany.map");
    }

    /**
     * print table
     * @param filename file name
     * @throws Exception
     */
    public static void printTable(String filename) throws Exception {
        BufferedReader bw = new BufferedReader(new FileReader(getFile(filename)));
        BufferedReader bw2 = new BufferedReader(new FileReader(getFile(filename)));
        String line = "";
        boolean border = false;
        boolean country = false;
        boolean continent = false;
        boolean flag = false;
        int num = 1;
        int count = 0;
        List<String> continentList = new ArrayList<>();
        //get line num
        while((line = bw2.readLine()) != null) {
            if(flag && !"".equals(line)) {
                count++;
            }
            if("[borders]".equals(line)) {
                flag = true;
            }
        }
        StringBuffer buff = new StringBuffer(" ");
        for(int i = 1;i <= count;i++) {
            buff.append(" "+i);
        }
        buff.append("\n"+num);
        Map<String,List<String>> map = new LinkedHashMap<>();
        while((line = bw.readLine()) != null) {
            //create table
            if(border && !"".equals(line)) {
                List<String> list = Arrays.asList(line.split(" "));
                for(int i = 1;i <= count;i++) {
                    if(list.contains(i+"")) {
                        for(int a = 0;a < (i+"").length();a++) {
                            buff.append(" ");
                        }
                        buff.append("x");
                    } else {
                        for(int a = 0;a < (i+"").length()+1;a++) {
                            buff.append(" ");
                        }
                    }
                }
                buff.append("\n"+(++num));
            }
            //get continent
            if(continent && !"".equals(line) && !"[countries]".equals(line)) {
                continentList.add(line.split(" ")[0]);
            }
            //get country
            if(country && !"".equals(line) && !"[borders]".equals(line)) {
                int conLine = Integer.parseInt(line.split(" ")[2]);
                String countryName = line.split(" ")[1];
                String conti = continentList.get(conLine - 1);
                if(map.get(conti) != null) {
                    map.get(conti).add(countryName);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(countryName);
                    map.put(conti, list);
                }
            }
            if("[continents]".equals(line)) {
                continent = true;
            }
            if("[countries]".equals(line)) {
                country = true;
                continent = false;
            }
            if("[borders]".equals(line)) {
                border = true;
                country = false;
            }
        }
        String str2 = "";
        for(Map.Entry<String, List<String>> en : map.entrySet()) {
            String key = en.getKey();
            List<String> value = en.getValue();
            str2 += key+":";
            for(String s : value) {
                str2 += " " + s;
            }
            str2 += "\n";
        }
        String str = buff.substring(0,buff.toString().lastIndexOf("\n"));
        System.out.println(str+"\n\n"+str2);
        bw.close();
        bw2.close();
    }

    /**
     * Get a map file from an existing "domination" map file
     *
     * @param filename path to file
     * @throws IOException if file not found or cannot read
     */
    private static File getFile(String filename) throws IOException {
        File f = new File("");
        String path = f.getCanonicalPath();
        File f2 = new File(path + "/" + dirName);
        if (!f2.exists()) {
            f2.mkdir();
        }
        File f3 = new File(f2.getAbsolutePath() + "/" + filename);
        return f3;
    }
}
