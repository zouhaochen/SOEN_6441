package map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the map editor
 * @author Haochen Zou
 * @version 1.0
 */
public class MapEdit {
    private static String DIRNAME = "domination";
    private static Scanner SC = new Scanner(System.in);
    private static String OPTFILE = "";
    // flag for junit test
    public static int FLAG=0;


    /**
     *
     * Loop read the user input commands
     * Main loop method in map editor
     *
     * @throws IOException if command invalid
     */
    public static void mapEditLoop() throws Exception {
        System.out.println("You are in the map editor.");
        String l_Command = "";
        // map editor loop
        for (;;) {
            System.out.println("Please type in your command:");
            l_Command = SC.nextLine();
            // call methods according to user's commands
            // call the editmap method
            if (l_Command.startsWith("editmap ")) {
                editMap(l_Command);
            // call the editcontinent method
            } else if (l_Command.startsWith("editcontinent ")) {
                editContinent(l_Command);
            // call the editcountry method
            } else if (l_Command.startsWith("editcountry ")) {
                editCountry(l_Command);
            // call the editneighbor method
            } else if (l_Command.startsWith("editneighbor ")) {
                editNeighbor(l_Command);
            // call the showmap method
            } else if ("showmap".equals(l_Command)) {
                File l_File = getFile(OPTFILE);
                if(l_File.exists()) {
                    showMap(l_File);
                }
                System.out.println("");
                System.out.println("Map graph as follow:");
                System.out.println("");
                // show map as a connected directed graph
                map.MapGraph.printTable(OPTFILE);
            // call the savemap method
            } else if (l_Command.startsWith("savemap ")) {
                saveMap(l_Command);
            // call the validatemap method
            } else if (l_Command.startsWith("validatemap")) {
                File l_File = getFile(OPTFILE);
                if(l_File.exists()) {
                    // check map validation
                    map.MapCheck.check(l_File);
                }
            } else if (l_Command.startsWith("exit")) {
                break;
            }
            else {
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
            FLAG = 1;
            return;
        }
        // read filename
        String l_FileName = l_String[1];
        OPTFILE = l_FileName;
        File l_F3 = getFile(l_FileName);
        // load and show the map if the file exists
        if (l_F3.exists()) {
            showMap(l_F3);
            System.out.println("");
            System.out.println("Map validate:");
            // check map validation
            map.MapCheck.check(l_F3);
            FLAG = 2;

        }
        // create a new map if the file does not exists
        else {
            l_F3.createNewFile();
            BufferedWriter l_Bw = new BufferedWriter(new FileWriter(l_F3));
            // add [continents] [countries] [borders]
            // make sure the map is editable
            l_Bw.write("[continents]\n\n" +
                    "[countries]\n\n" +
                    "[borders]");
            l_Bw.flush();
            l_Bw.close();
            // check map validation
            map.MapCheck.check(l_F3);
            FLAG = 3;
        }
    }

    /**
     * Get a map file from an existing "domination" map file
     *
     * @param p_FileName path to file
     * @throws IOException if file not found or cannot read
     * @return the map file
     */
    public static File getFile(String p_FileName) throws IOException {
        File l_F = new File("");
        String l_path = l_F.getCanonicalPath();
        File l_F2 = new File(l_path + "/" + DIRNAME);
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
        // add operation
        if (p_Suffix.startsWith("-add ")) {
            l_Str1 = p_Suffix.substring(5);
            // return the index of the first occurrence of
            // a specified character in a string
            // or - 1 if there is no such character in the string
            int l_n1 = l_Str1.indexOf("-add ");
            int l_n2 = l_Str1.indexOf("-remove ");
            // determine add operation
            // according to numerical value
            if (l_n1 == -1) {
                if (l_n2 == -1) {
                    p_Adds.add(l_Str1);
                    return;
                } else {
                    p_Adds.add(l_Str1.substring(0, l_n2 - 1));
                    l_Str1 = l_Str1.substring(l_n2);
                }
            }
            // determine remove operation
            // according to numerical value
            else {
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
        // remove operation
        } else if (p_Suffix.startsWith("-remove ")) {
            l_Str1 = p_Suffix.substring(8);
            // return the index of the first occurrence of
            // a specified character in a string
            // or - 1 if there is no such character in the string
            int l_n1 = l_Str1.indexOf("-add ");
            int l_n2 = l_Str1.indexOf("-remove ");
            // determine add operation
            // according to numerical value
            if (l_n1 == -1) {
                if (l_n2 == -1) {
                    p_Rems.add(l_Str1);
                    return;
                } else {
                    p_Rems.add(l_Str1.substring(0, l_n2 - 1));
                    l_Str1 = l_Str1.substring(l_n2);
                }
            }
            // determine remove operation
            // according to numerical value
            else {
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
        // get file name
        String[] l_S = p_Command.split(p_EditType);
        if (l_S.length == 0) {
            System.out.println("filename input error");
            return;
        }
        // determine add and delete according to command
        String l_suffix = l_S[1];
        List<String> l_Adds = new ArrayList<>();
        List<String> l_Rems = new ArrayList<>();
        optType(l_suffix, l_Adds, l_Rems);
        // read file name
        File l_F3 = getFile(OPTFILE);
        // read map file
        BufferedReader l_Br = new BufferedReader(new FileReader(l_F3));
        // write content in a temptation file
        BufferedWriter l_Bw = new BufferedWriter(new FileWriter("temp.map"));
        String l_Line = "";
        StringBuffer l_Sb = new StringBuffer();
        // set flag
        boolean l_Flag = false;
        // Edit content in map according to flag
        while ((l_Line = l_Br.readLine()) != null) {
            if (p_Head.equals(l_Line)) {
                // Create a new array
                // enlarge the length
                // copy the strings that need to be added to the new array
                l_Sb.append(l_Line + "\n");
                l_Flag = true;
                continue;
            } else if (l_Flag && "".equals(l_Line)) {
                // add option
                l_Adds.forEach((item) -> {
                    l_Sb.append(item + "\n");
                });
                l_Flag = false;
            // flag starts with [ and ends with ]
            } else if (l_Line.startsWith("[") && l_Line.endsWith("]")) {
                l_Flag = false;
            }
            if (l_Flag) {
                boolean l_Res = false;
                // remove option
                for (String l_Item : l_Rems) {
                    if (l_Line.startsWith(l_Item)) {
                        l_Res = true;
                    }
                }
                if (!l_Res) {
                    l_Sb.append(l_Line + "\n");
                }
            } else {
                l_Sb.append(l_Line + "\n");
            }
        }
        if("[borders]".equals(p_Head) && l_Flag) {
            l_Adds.forEach((item) -> {
                l_Sb.append(item + "\n");
            });
        }
        l_Bw.write(l_Sb.toString());
        l_Bw.close();
        l_Br.close();
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
        BufferedReader l_Br = new BufferedReader(new FileReader(p_F3));
        String l_line = "";
        while ((l_line = l_Br.readLine()) != null) {
            System.out.println(l_line);
        }
        l_Br.close();
    }

    /**
     * Save a map to a text file exactly as edited.
     *
     * @param p_Command command user input
     * @throws IOException if command invalid
     */
    public static void saveMap(String p_Command) throws IOException {
        // get command user input
        String[] l_S = p_Command.split("savemap ");
        System.out.println("Warning: You should check map VALID");
        System.out.println("         only valid map can be played");
        if (l_S.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String l_FileName = l_S[1];
        // get file name
        File l_File = getFile(l_FileName);
        // get temporary file
        File l_ReadFile = new File("temp.map");
        // convert temporary file content to map file
        if(l_ReadFile.exists()) {
            BufferedReader l_Br = new BufferedReader(new FileReader(l_ReadFile));
            BufferedWriter l_Bw = new BufferedWriter(new FileWriter(l_File));
            String l_Line = "";
            while((l_Line = l_Br.readLine()) != null) {
                l_Bw.write(l_Line);
                l_Bw.newLine();
                l_Bw.flush();
            }
            l_Br.close();
            l_Bw.close();
            // check map validation
            map.MapCheck.check(l_File);
        }
    }

    /**
     * Get flag number from different state
     *
     * @param p_Command command user input
     * @return flag number
     */
    public static int flagEditMap(String p_Command)
    {
        MapEdit l_flagEditMap = new MapEdit();
        try {
            MapEdit.editMap(p_Command);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return FLAG;
    }
}

