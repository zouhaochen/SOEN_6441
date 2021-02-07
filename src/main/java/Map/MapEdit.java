package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Auther: Haochen Zou
 * @Date: 2021/2/6 - 上午9:55
 * @Description: map
 * @version: 1.0
 */

public class MapEdit {
    private static String dirName = "file";
    public static void main(String[] args) throws IOException {
        System.out.println("Please type in your command:");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while(!command.startsWith("editmap ")) {
            System.out.println("invalid command");
            System.out.println("Please type in your command:");
            command = sc.nextLine();
        }
        String[] s = command.split("editmap ");
        if(s.length == 0) {
            System.out.println("filename input error");
            return;
        }
        String filename = s[1];
        File f = new File("");
        String path = f.getCanonicalPath();
        File f2 = new File(path+"/"+dirName);
        if(!f2.exists()) {
            f2.mkdir();
        }
        File f3 = new File(f2.getAbsolutePath()+"/"+filename);
        if(f3.exists()) {
            //read file
            BufferedReader br = new BufferedReader(new FileReader(f3));
            String line = "";
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } else {
            //create file
            f3.createNewFile();
        }
    }
}
