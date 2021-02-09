package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to get the details from the map file
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapInformationAccess {

    /**
     *This method is to count the number of lines
     * @return count the total lines
     * @throws FileNotFoundException
     */
    public int getlines(){

        File file = new File("test_02.map");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        while(scan.hasNextLine()){
            count++;
            scan.nextLine();
        }
        return count;
    }
}


