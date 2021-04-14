package model.state.play;

import model.GameData;
import model.gameelements.GameElements;

import java.io.*;

/**
 * This class is to save and load the game
 * @author Zitao Wang
 *
 */

public class SaveGame implements Serializable {


    /**
     * This method is to save the game
     * @param p_GameData  get game data as object
     * @return return trure if current game can be saved.
     */
    public static boolean saveGame(GameData p_GameData){

        boolean saved = false;

        try
        {
            FileOutputStream l_saveFile=new FileOutputStream("save.txt");
            ObjectOutputStream l_save = new ObjectOutputStream(l_saveFile);
            GameElements l_gameElements = new GameElements();
            // write GameData Object
            l_save.writeObject(p_GameData);
            l_save.flush();
            l_save.close();
            System.out.println("write object success!");
            saved=true;
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
            saved=false;
        }
        return saved;
    }

    /**
     * to get the object from file.
     * @return return null.
     */
    public GameData getObjFromFile(){

        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("save.txt"));
            GameData l_GameData=(GameData)ois.readObject();              //读出对象

            return l_GameData;                                       //返回对象
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
