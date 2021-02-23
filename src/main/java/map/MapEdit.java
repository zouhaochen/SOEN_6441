package map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapEdit {
    private static String DIRNAME = "domination";
    private static Scanner SC = new Scanner(System.in);
    private static String OPTFILE = "";

    /**
     *
     * Loop read the user input commands
     * Main method in map editor
     *
     * @param args receive parameters from the console
     * @throws IOException if command invalid
     */
    public static void main(String[] args) throws IOException {
        System.out.println("You are in the map editor.");
        String l_Command = "";
        for (;;) {
            System.out.println("Please type in your command:");
            l_Command = SC.nextLine();
            if (l_Command.startsWith("editmap ")) {
                editMap(l_Command);
            } else if (l_Command.startsWith("editcontinent ")) {
                editContinent(l_Command);
            } else if (l_Command.startsWith("editcountry ")) {
                editCountry(l_Command);
            } else if (l_Command.startsWith("editneighbor ")) {
                editNeighbor(l_Command);
            } else if ("showmap".equals(l_Command)) {
                File l_File = getFile(OPTFILE);
                if(l_File.exists()) {
                    showMap(l_File);
                }
            } else if (l_Command.startsWith("savemap ")) {
                saveMap(l_Command);
            } else if (l_Command.startsWith("validatemap")) {
                File l_File = getFile(OPTFILE);
                if(l_File.exists()) {
                    map.MapCheck.check(l_File);
                }
            } else {
                System.out.println("invalid command");
            }
        }
    }

    /**
     * Load a map from an existing "domination" map file
     * or create a new map form scratch if the file does not exist
     *
     * @param p_Command command user input
     * @throws IOException if command invalid
     */
    public static void editMap(String p_Command) throws IOException {
        String[] l_String = p_Command.split("editmap ");
        if (l_String.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String l_FileName = l_String[1];
        OPTFILE = l_FileName;
        File l_F3 = getFile(l_FileName);
        if (l_F3.exists()) {
            showMap(l_F3);
            System.out.println("");
            System.out.println("Map validate:");
//            map.MapCheck.check(l_F3);
        } else {
            l_F3.createNewFile();
            BufferedWriter l_bw = new BufferedWriter(new FileWriter(l_F3));
            l_bw.write("[continents]\n\n" +
                    "[countries]\n\n" +
                    "[borders]");
            l_bw.flush();
            l_bw.close();
//            map.MapCheck.check(l_F3);
        }
    }

    /**
     * Get a map file from an existing "domination" map file
     *
     * @param p_FileName path to file
     * @throws IOException if file not found or cannot read
     */
    public static File getFile(String p_FileName) throws IOException {
        File l_F = new File("");
        String path = l_F.getCanonicalPath();
        File l_F2 = new File(path + "/" + DIRNAME);
        if (!l_F2.exists()) {
            l_F2.mkdir();
        }
        File l_F3 = new File(l_F2.getAbsolutePath() + "/" + p_FileName);
        return l_F3;
    }

    /**
     * Edit continent to map
     *
     * @param p_Command command user input
     * @throws IOException if command invalid
     */
    public static void editContinent(String p_Command) throws IOException {
        tempSave(p_Command, "editcontinent ", "[continents]");
    }

    /**
     * Add and remove continents or countries to map file
     *
     * @param p_Suffix add or command commands suffix
     * @param p_Adds store add command
     * @param p_Rems store remove command
     */
    private static void optType(String p_Suffix, List<String> p_Adds, List<String> p_Rems) {
        String l_Str1 = "";
        if (p_Suffix.startsWith("-add ")) {
            l_Str1 = p_Suffix.substring(5);
            int l_n1 = l_Str1.indexOf("-add ");
            int l_n2 = l_Str1.indexOf("-remove ");
            if (l_n1 == -1) {
                if (l_n2 == -1) {
                    p_Adds.add(l_Str1);
                    return;
                } else {
                    p_Adds.add(l_Str1.substring(0, l_n2 - 1));
                    l_Str1 = l_Str1.substring(l_n2);
                }
            } else {
                if (l_n2 == -1) {
                    p_Adds.add(l_Str1.substring(0, l_n1 - 1));
                    l_Str1 = l_Str1.substring(l_n1);
                } else {
                    if (l_n1 < l_n2) {
                        p_Adds.add(l_Str1.substring(0, l_n1 - 1));
                        l_Str1 = l_Str1.substring(l_n1);
                    } else {
                        p_Adds.add(l_Str1.substring(0, l_n2 - 1));
                        l_Str1 = l_Str1.substring(l_n2);
                    }
                }
            }
        } else if (p_Suffix.startsWith("-remove ")) {
            l_Str1 = p_Suffix.substring(8);
            int l_n1 = l_Str1.indexOf("-add ");
            int l_n2 = l_Str1.indexOf("-remove ");
            if (l_n1 == -1) {
                if (l_n2 == -1) {
                    p_Rems.add(l_Str1);
                    return;
                } else {
                    p_Rems.add(l_Str1.substring(0, l_n2 - 1));
                    l_Str1 = l_Str1.substring(l_n2);
                }
            } else {
                if (l_n2 == -1) {
                    p_Rems.add(l_Str1.substring(0, l_n1 - 1));
                    l_Str1 = l_Str1.substring(l_n1);
                } else {
                    if (l_n1 < l_n2) {
                        p_Rems.add(l_Str1.substring(0, l_n1 - 1));
                        l_Str1 = l_Str1.substring(l_n1);
                    } else {
                        p_Rems.add(l_Str1.substring(0, l_n2 - 1));
                        l_Str1 = l_Str1.substring(l_n2);
                    }
                }
            }
        } else {
            System.out.println("invalid command");
            return;
        }
        optType(l_Str1, p_Adds, p_Rems);
    }

    /**
     * Edit contents of map file
     *
     * @param p_Command command user input
     * @param p_EditType split command for program to operate according to content
     * @param p_Head label used for classification in map file
     * @throws IOException if file not found or cannot read
     */
    private static void tempSave(String p_Command, String p_EditType, String p_Head) throws IOException {
        String[] l_s = p_Command.split(p_EditType);
        if (l_s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String l_suffix = l_s[1];
        List<String> l_Adds = new ArrayList<>();
        List<String> l_Rems = new ArrayList<>();
        optType(l_suffix, l_Adds, l_Rems);
        File l_F3 = getFile(OPTFILE);
        BufferedReader l_br = new BufferedReader(new FileReader(l_F3));
        BufferedWriter l_bw = new BufferedWriter(new FileWriter("temp.map"));
        String l_line = "";
        StringBuffer l_sb = new StringBuffer();
        boolean l_flag = false;
        while ((l_line = l_br.readLine()) != null) {
            if (p_Head.equals(l_line)) {
                l_sb.append(l_line + "\n");
                l_flag = true;
                continue;
            } else if (l_flag && "".equals(l_line)) {
                l_Adds.forEach((item) -> {
                    l_sb.append(item + "\n");
                });
                l_flag = false;
            } else if (l_line.startsWith("[") && l_line.endsWith("]")) {
                l_flag = false;
            }
            if (l_flag) {
                boolean res = false;
                for (String item : l_Rems) {
                    if (l_line.startsWith(item)) {
                        res = true;
                    }
                }
                if (!res) {
                    l_sb.append(l_line + "\n");
                }
            } else {
                l_sb.append(l_line + "\n");
            }
        }
        if("[borders]".equals(p_Head) && l_flag) {
            l_Adds.forEach((item) -> {
                l_sb.append(item + "\n");
            });
        }
        l_bw.write(l_sb.toString());
        l_bw.close();
        l_br.close();
    }

    /**
     * Edit country to map
     *
     * @param p_Command command user input
     * @throws IOException if command invalid
     */
    public static void editCountry(String p_Command) throws IOException {
        tempSave(p_Command, "editcountry ", "[countries]");
    }

    /**
     * Edit connectivity to map
     *
     * @param p_Command command user input
     * @throws IOException if command invalid
     */
    public static void editNeighbor(String p_Command) throws IOException {
        tempSave(p_Command, "editneighbor ", "[borders]");
    }

    /**
     * Display the map as text
     *
     * @param p_F3 filename user input in editmap command
     * @throws IOException if file not find
     */
    public static void showMap(File p_F3) throws IOException {
        BufferedReader l_br = new BufferedReader(new FileReader(p_F3));
        String l_line = "";
        while ((l_line = l_br.readLine()) != null) {
            System.out.println(l_line);
        }
        l_br.close();
    }

    /**
     * Save a map to a text file exactly as edited.
     *
     * @param p_Command command user input
     * @throws IOException if command invalid
     */
    public static void saveMap(String p_Command) throws IOException {
        String[] l_s = p_Command.split("savemap ");
        System.out.println("Warning: You should check map VALID");
        System.out.println("         only valid map can be played");
        if (l_s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String l_FileName = l_s[1];
        File l_file = getFile(l_FileName);
        File l_readFile = new File("temp.map");
        if(l_readFile.exists()) {
            BufferedReader l_br = new BufferedReader(new FileReader(l_readFile));
            BufferedWriter l_bw = new BufferedWriter(new FileWriter(l_file));
            String l_line = "";
            while((l_line = l_br.readLine()) != null) {
                l_bw.write(l_line);
                l_bw.newLine();
                l_bw.flush();
            }
            l_br.close();
            l_bw.close();
//            map.MapCheck.check(l_file);
        }
    }
}
