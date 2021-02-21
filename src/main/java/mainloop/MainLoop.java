package mainloop;

import command.CommandValidator;
import gameelements.Player;
import gameplay.GameData;
import gameplay.GameEngine;
import java.util.Scanner;

/** The class is to run main activity for the game
 * @author hanjiaming
 * @version  1.0.0
 */

public class MainLoop extends GameEngine {

    private static GameData p_GameData;
    private static String p_colour;
    private static CommandValidator d_commandValidator;


    /**
     * GameEngine Constructor
     *
     * @param p_GameData you should pass reference that Game Data you used
     */
    public MainLoop(GameData p_GameData) {
        super(p_GameData);
    }


    public static void main(String[] args) {
        Player o_player = new Player(p_colour, d_commandValidator);
        GameEngine o_GameEngine = new GameEngine(p_GameData);
        GameData o_GameData = new GameData();
        Scanner sc = new Scanner(System.in);

        //Assign Reinforcement phase
        //调用gameplay里的方法将每回合新增援的兵数分配给每个玩家
        int p_RainfocementArmy = o_player.getReinforcementArmies();
        o_player.setReinforcementArmies(p_RainfocementArmy);

        //Issue Order Phase
        //o_player.issueOrder();

        //Execute Order Phase

        //获取地图信息, 包括所有的country和country的领主信息, 根据规则和player拥有的领土信息分配军队数量

        //循环每一轮, 直到有人获胜

        //我的任务
        System.out.println("Welcome to warzone! ");

        //获取player的数量和所有player的ID
        System.out.println("Please enter for how many player are going to play: ");
        int p_playerNum = sc.nextInt();
        o_GameData.setTotalPlayer(p_playerNum);

        System.out.println("Please enter the name for a player: ");
        for (int i = 0; i < o_GameData.getTotalPlayer(); i++) {
            String p_playerName = sc.nextLine();
           // o_GameEngine.addNewPlayer(p_playerName);
        }

        System.out.println("please enter how many country in total: ");
        int p_countryNum = sc.nextInt();
        o_GameData.setTotalCountry(p_countryNum);
        System.out.println(o_GameData.getTotalCountry() + " contries have already set !");

/*
        while(o_GameData.getCurrentPhase().getGamePhaseAsInt() == '5' ) {

                //循环遍历所有player, 调用player的issue_order()获取玩家输入的指令并存储, 直到所有玩家都结束下达指令, 将指令存储在Player的orders中
                for (int i = 0; i < o_GameData.getD_TotalPlayer(); i++) {
                    o_player.issueOrder();
                }

                //循环调用player的next_order()获取order, 并调用order的execute()执行命令, 更新所有country上各player的军队信息
              //  for (int i = 0; i < ; i++) {
                    o_player.nextOrder();
             //   }
            //其他指令, 计算本轮结果等

        }
        */

    }
}

