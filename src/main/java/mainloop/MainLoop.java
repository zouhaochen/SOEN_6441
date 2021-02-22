package mainloop;

import command.CommandValidator;
import gameelements.Player;
import gameelements.order.DeployOrder;
import gameplay.GameData;
import gameplay.GameEngine;
import java.util.Scanner;

/** The class is to run main activity for the game
 * @author hanjiaming
 * @version  1.0.0
 */

public class MainLoop {

    private static GameData p_GameData;
    private static String p_colour;
    private static CommandValidator d_commandValidator;



    public static void main(String[] args) {
        Player o_player = new Player(p_colour, d_commandValidator);
        GameData o_GameData = new GameData();
        GameEngine o_GameEngine = new GameEngine(o_GameData);

        Scanner sc = new Scanner(System.in);

        //我的任务
        System.out.println("Welcome to warzone! ");

        //获取player的数量和所有player的ID
        System.out.println("Please enter for how many player are going to play: ");
        int p_playerNum = sc.nextInt();
        o_GameData.setTotalPlayer(p_playerNum);

        System.out.println("Please enter the name for each of player: ");
            String  p_playerName = sc.next();
        for (int i = 0; i < p_playerNum; i++) {
            o_GameEngine.addNewPlayer(p_playerName);
        }

        //获取地图信息, 包括所有的country和country的领主信息, 根据规则和player拥有的领土信息分配军队数量
        System.out.println("please enter how many country in total: ");
            int p_countryNum = sc.nextInt();
            o_GameData.setTotalCountry(p_countryNum);
        System.out.println(o_GameData.getTotalCountry() + " contries have already set !");



       //游戏机制开始， 循环每一轮, 直到有人获胜
       while(o_GameData.getCurrentPhase().getGamePhaseAsInt() == '5' ) {
           //Assign Reinforcement phase 调用gameplay里的方法将每回合新增援的兵数分配给每个玩家

                //循环遍历所有player, 调用player的issue_order()获取玩家输入的指令并存储, 直到所有玩家都结束下达指令, 将指令存储在Player的orders中
                   for (Player l_Player : o_GameData.getPlayerList()) {
                       l_Player.setReinforcementArmies(5);

                       while(l_Player.getReinforcementArmies()>0) {
                           l_Player.issueOrder();
                       }
                   }

                   for (Player l_Player : o_GameData.getPlayerList()) {
                        int p_orderList_length = l_Player.getOrdersInCurrentTurn().size();

                       for(int i=0; i< p_orderList_length; i++ ) {
                           l_Player.nextOrder().execute();
                       }
                   }

           //其他指令, 计算本轮结果等

        }
    }
}

