package map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Auther: Haochen Zou
 * @Date: 2021/2/6 - 上午9:55
 * @Description: map
 * @version: 1.0
 */

public class MapEdit {
    private static String dirName = "domination";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String command = "";
        for (;;) {
            command = sc.nextLine();
            if (command.startsWith("editmap ")) {
                editmap(command);
            } else if (command.startsWith("editcontinent ")) {
                editcontinent(command);
            } else if (command.startsWith("editcountry ")) {
                editcountry(command);
            } else if (command.startsWith("editneighbour ")) {
                editneighbour(command);
            } else if ("showmap".equals(command)) {
                File file = getFile("01.map");
                if(file.exists()) {
                    showmap(file);
                }
            } else if (command.startsWith("savemap ")) {
                save(command);
            } else {
                System.out.println("invalid command");
            }
        }
    }

    private static void editmap(String command) throws IOException {
        String[] s = command.split("editmap ");
        if (s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String filename = s[1];
        File f3 = getFile(filename);
        if (f3.exists()) {
            // read file
            showmap(f3);
        } else {
            // create file
            f3.createNewFile();
        }
    }

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

    private static void editcontinent(String command) throws IOException {
        // editcontinent -add America 4 -add Africa 3 -remove Asia -remove Europe
        tempSave(command, "editcontinent ", "[continents]");
    }

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
        File f3 = getFile("01.map");
        BufferedReader br = new BufferedReader(new FileReader(f3));
        BufferedWriter bw = new BufferedWriter(new FileWriter("temp666.map"));
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

    private static void editcountry(String command) throws IOException {
        //editcountry -add 1 China 1 -add 2 Korean 2 -remove 3 Russia 2 -remove 4 Finland 2
        tempSave(command, "editcountry ", "[countries]");
    }

    private static void editneighbour(String command) throws IOException {
        //editneighbour -add 1 2 3 -add 2 1 3 -remove 3 1 2 -remove 4 3
        tempSave(command, "editneighbour ", "[borders]");
    }

    private static void showmap(File f3) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f3));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private static void save(String command) throws IOException {
        String[] s = command.split("savemap ");
        if (s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String filename = s[1];
        File file = getFile(filename);
        File readFile = new File("temp666.map");
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
