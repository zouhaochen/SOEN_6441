package mainloop;

import command.CommandValidator;
import gameelements.Country;
import gameelements.Player;
import gameplay.GameData;
import gameplay.GameEngine;
import gameplay.GamePhase;
import map.MapCheck;
import map.MapDetailAccess;
import map.MapEdit;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/** The class is to run main activity for the game
 * @author hanjiaming
 * @version  1.0.0
 */

public class MainLoop {
    public static GameData d_GameData;
    private static GameEngine d_GameEngine;
    private static GamePhase d_GamePhase;
    private static MapEdit d_MapEdit;
    public  Player d_Player;
    public  CommandValidator d_commandValidator;
    public  String d_colour;
    public  File d_MapFile;

    private static Map<String, Country> d_countryList;
    private static String OPTFILE = "";

    public void MainLoop(){
        this.d_Player = new Player(d_colour, d_commandValidator);
        this.d_GameData = new GameData(d_MapFile);
        this.d_GameEngine = new GameEngine(d_GameData);
        this.d_MapEdit = new MapEdit();
    }


    public static void mainLoopMapEdit() throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner kb = new Scanner(System.in);

        System.out.println("do you want to create and edit a new map? (y/n)");
        String p_CheckNewGame = sc.next();
        if (p_CheckNewGame.equalsIgnoreCase("y")) {
            System.out.println("You are in the map editor.");
            String l_Command = "";

            //---------cannot read mapedit command------------
            for (; ; ) {
                System.out.println("Please type in your command:");
                l_Command = sc.next();
                if (l_Command.startsWith("editmap ")) {
                    MapEdit.editMap(l_Command);
                } else if (l_Command.startsWith("editcontinent ")) {
                    MapEdit.editContinent(l_Command);
                } else if (l_Command.startsWith("editcountry ")) {
                    MapEdit.editCountry(l_Command);
                } else if (l_Command.startsWith("editneighbor ")) {
                    MapEdit.editNeighbor(l_Command);
                } else if ("showmap".equals(l_Command)) {
                    File l_File = MapEdit.getFile(OPTFILE);
                    if (l_File.exists()) {
                        MapEdit.showMap(l_File);
                    }
                } else if (l_Command.startsWith("savemap ")) {
                    MapEdit.saveMap(l_Command);
                } else if (l_Command.startsWith("validatemap")) {
                    File l_File = MapEdit.getFile(OPTFILE);
                    if (l_File.exists()) {
                        MapCheck.check(l_File);
                    }
                } else {
                    System.out.println("invalid command");
                }

                System.out.println("any other option? (y/n)");
                String p_CheckOption = kb.next();
                if (p_CheckOption.equalsIgnoreCase("n")) {
                    break;
                }
                if (p_CheckOption.equalsIgnoreCase("y")) {
                    continue;
                }
            }

        } else if (p_CheckNewGame.equalsIgnoreCase("n")) {
            System.out.println(" End of the Game ");
        }
    }

    public static void mainLoopRun() {
        Scanner sc = new Scanner(System.in);

        //---------cannot read scan input------------
        System.out.println("Please enter for how many player are going to play: ");
        int p_playerNum = sc.nextInt();
        d_GameData.setTotalPlayer(p_playerNum);

        for (int i = 0; i < p_playerNum; i++) {
            System.out.println("Please enter the name for each of player: ");
            String p_playerName = sc.next();
            d_GameEngine.addNewPlayer(p_playerName);
        }

        //获取地图信息, 包括所有的country和country的领主信息, 根据规则和player拥有的领土信息分配军队数量
        for (Player d_Player : d_GameData.getPlayerList()) {
            d_GameEngine.showMap(d_Player);
        }
        //回合前检查玩家是否占领了大陆上足够多的国家来在每回合额外分配兵力
                           /* while(){
                             for (Player d_Player : o_GameData.getPlayerList()) {
                                    o_GameEngine.showMap(d_Player);
                                }
                            }
                            */
        //游戏机制开始， 循环每一轮, 直到有人获胜
        while (MainLoop.d_GamePhase.getGamePhaseAsInt() == 5) {
            //Assign Reinforcement phase 调用gameplay里的方法将每回合新增援的兵数分配给每个玩家

            //循环遍历所有player, 调用player的issue_order()获取玩家输入的指令并存储, 直到所有玩家都结束下达指令, 将指令存储在Player的orders中
            for (Player l_Player : d_GameData.getPlayerList()) {
                l_Player.setReinforcementArmies(5);

                while (l_Player.getReinforcementArmies() > 0) {
                    l_Player.issueOrder();
                }
            }

            for (Player l_Player : d_GameData.getPlayerList()) {
                int p_orderList_length = l_Player.getOrdersInCurrentTurn().size();

                for (int i = 0; i < p_orderList_length; i++) {
                    l_Player.nextOrder().execute();
                }
            }
            //其他指令, 计算本轮结果等

        }

    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to warzone! ");
        System.out.println("do you want to load previous saved map? (y/n)");
        String p_CheckSave = sc.next();
        if(p_CheckSave.equalsIgnoreCase("y")){
            //  gameElements 调用 saveload.loadmap();

        }
        else if (p_CheckSave.equalsIgnoreCase("n")) {
            //     mainLoopMapEdit();
            mainLoopRun();
        }
    }
}