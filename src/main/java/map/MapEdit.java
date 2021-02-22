package map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapEdit {
    private static String dirName = "domination";
    private static Scanner sc = new Scanner(System.in);
    private static String optFile = "";

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
            l_Command = sc.nextLine();
            if (l_Command.startsWith("editmap ")) {
                editMap(l_Command);
            } else if (l_Command.startsWith("editcontinent ")) {
                editContinent(l_Command);
            } else if (l_Command.startsWith("editcountry ")) {
                editCountry(l_Command);
            } else if (l_Command.startsWith("editneighbour ")) {
                editNeighbour(l_Command);
            } else if ("showmap".equals(l_Command)) {
                File l_File = getFile(optFile);
                if(l_File.exists()) {
                    showmap(l_File);
                }
            } else if (l_Command.startsWith("savemap ")) {
                save(l_Command);
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
    private static void editMap(String p_Command) throws IOException {
        String[] l_String = p_Command.split("editmap ");
        if (l_String.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String l_FileName = l_String[1];
        optFile = l_FileName;
        File l_F3 = getFile(l_FileName);
        if (l_F3.exists()) {
            showmap(l_F3);
        } else {
            l_F3.createNewFile();
            BufferedWriter l_bw = new BufferedWriter(new FileWriter(l_F3));
            l_bw.write("[continents]\n\n" +
                    "[countries]\n\n" +
                    "[borders]");
            l_bw.flush();
            l_bw.close();
        }
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

    /**
     * Edit continent to map
     *
     * @param command command user input
     * @throws IOException if command invalid
     */
    private static void editContinent(String command) throws IOException {
        tempSave(command, "editcontinent ", "[continents]");
    }

    /**
     * Add and remove continents or countries to map file
     *
     * @param suffix add or command commands suffix
     * @param adds store add command
     * @param rems store remove command
     */
    private static void optType(String suffix, List<String> adds, List<String> rems) {
        String str1 = "";
        if (suffix.startsWith("-add ")) {
            str1 = suffix.substring(5);
            int n1 = str1.indexOf("-add ");
            int n2 = str1.indexOf("-remove ");
            if (n1 == -1) {
                if (n2 == -1) {
                    adds.add(str1);
                    return;
                } else {
                    adds.add(str1.substring(0, n2 - 1));
                    str1 = str1.substring(n2);
                }
            } else {
                if (n2 == -1) {
                    adds.add(str1.substring(0, n1 - 1));
                    str1 = str1.substring(n1);
                } else {
                    if (n1 < n2) {
                        adds.add(str1.substring(0, n1 - 1));
                        str1 = str1.substring(n1);
                    } else {
                        adds.add(str1.substring(0, n2 - 1));
                        str1 = str1.substring(n2);
                    }
                }
            }
        } else if (suffix.startsWith("-remove ")) {
            str1 = suffix.substring(8);
            int n1 = str1.indexOf("-add ");
            int n2 = str1.indexOf("-remove ");
            if (n1 == -1) {
                if (n2 == -1) {
                    rems.add(str1);
                    return;
                } else {
                    rems.add(str1.substring(0, n2 - 1));
                    str1 = str1.substring(n2);
                }
            } else {
                if (n2 == -1) {
                    rems.add(str1.substring(0, n1 - 1));
                    str1 = str1.substring(n1);
                } else {
                    if (n1 < n2) {
                        rems.add(str1.substring(0, n1 - 1));
                        str1 = str1.substring(n1);
                    } else {
                        rems.add(str1.substring(0, n2 - 1));
                        str1 = str1.substring(n2);
                    }
                }
            }
        } else {
            System.out.println("invalid command");
            return;
        }
        optType(str1, adds, rems);
    }

    /**
     * Edit contents of map file
     *
     * @param command command user input
     * @param editType split command for program to operate according to content
     * @param head label used for classification in map file
     * @throws IOException if file not found or cannot read
     */
    private static void tempSave(String command, String editType, String head) throws IOException {
        String[] s = command.split(editType);
        if (s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String suffix = s[1];
        List<String> adds = new ArrayList<>();
        List<String> rems = new ArrayList<>();
        optType(suffix, adds, rems);
        File f3 = getFile(optFile);
        BufferedReader br = new BufferedReader(new FileReader(f3));
        BufferedWriter bw = new BufferedWriter(new FileWriter("temp.map"));
        String line = "";
        StringBuffer sb = new StringBuffer();
        boolean flag = false;
        while ((line = br.readLine()) != null) {
            if (head.equals(line)) {
                sb.append(line + "\n");
                flag = true;
                continue;
            } else if (flag && "".equals(line)) {
                adds.forEach((item) -> {
                    sb.append(item + "\n");
                });
                flag = false;
            } else if (line.startsWith("[") && line.endsWith("]")) {
                flag = false;
            }
            if (flag) {
                boolean res = false;
                for (String item : rems) {
                    if (line.startsWith(item)) {
                        res = true;
                    }
                }
                if (!res) {
                    sb.append(line + "\n");
                }
            } else {
                sb.append(line + "\n");
            }
        }
        if("[borders]".equals(head) && flag) {
            adds.forEach((item) -> {
                sb.append(item + "\n");
            });
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * Edit country to map
     *
     * @param command command user input
     * @throws IOException if command invalid
     */
    private static void editCountry(String command) throws IOException {
        tempSave(command, "editcountry ", "[countries]");
    }

    /**
     * Edit connectivity to map
     *
     * @param command command user input
     * @throws IOException if command invalid
     */
    private static void editNeighbour(String command) throws IOException {
        tempSave(command, "editneighbour ", "[borders]");
    }

    /**
     * Display the map as text
     *
     * @param f3 filename user input in editmap command
     * @throws IOException if file not find
     */
    private static void showmap(File f3) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f3));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    /**
     * Save a map to a text file exactly as edited.
     *
     * @param command command user input
     * @throws IOException if command invalid
     */
    private static void save(String command) throws IOException {
        String[] s = command.split("savemap ");
        if (s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String filename = s[1];
        File file = getFile(filename);
        File readFile = new File("temp.map");
        if(readFile.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(readFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String line = "";
            while((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            br.close();
            bw.close();
        }
    }
}
