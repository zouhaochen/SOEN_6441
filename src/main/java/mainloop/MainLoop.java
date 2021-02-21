package MainLoop;

import gameelements.Player;
import gameplay.GameEngine;
import gameplay.GamePhase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** The class is to run main activity for the game
 * @author hanjiaming
 * @version  1.0.0
 */

public class MainLoop extends GameEngine {
    private static int total_country;
    private static int total_player;
    private static ArrayList <String> country_list;
    private static ArrayList<String> continent_list;
    private static String p_colour;
    private static List<String> countries;
    private static Map<String, Integer> continent_country_count;
    private static Map<String, String> country_continent;
    private static Map<String, Integer> continent_value;
    private static int total_order;

    private static int gamePhase;

    /**
     * GameEngine Constructor
     *
     * @param total_country  for import total country
     * @param total_players  import total players number
     * @param country_list   country list
     * @param continent_list continent list
     */
    public MainLoop(int total_country, int total_players, ArrayList<String> country_list, ArrayList<String> continent_list) {
        super(total_country, total_players, country_list, continent_list);
    }

    public static void main(String[] args){
        Player o_player = new Player(p_colour);
        GameEngine o_GameEngine = new GameEngine(total_country, total_player,country_list, continent_list);
        GamePhase o_GamePhase = new GamePhase();

        //Assign Reinforcement phase
        //调用gameplay里的方法将每回合新增援的兵数分配给每个玩家
        Integer RF = o_GameEngine.calReinforcementArmies( countries, continent_country_count, country_continent, continent_value);
        o_player.setReinforcementArmies(RF);

        //Issue Order Phase
        //o_player.issueOrder();

        //Execute Order Phase

        //我的任务
        //获取player的数量和所有player的ID


        //获取地图信息, 包括所有的country和country的领主信息, 根据规则和player拥有的领土信息分配军队数量

        //循环每一轮, 直到有人获胜
        while( o_GamePhase.getGamePhaseAsInt() != '5') {
                //循环遍历所有player, 调用player的issue_order()获取玩家输入的指令并存储, 直到所有玩家都结束下达指令, 将指令存储在Player的orders中
                for (int i = 0; i < total_order; i++) {
                    //我觉得这应该有个 if条件
                    // if（deployReinforcementArmies（p_armyNum）) {
                    //o_player.issueOrder();
                    // }
                    o_player.issueOrder();
                }

                //循环调用player的next_order()获取order, 并调用order的execute()执行命令, 更新所有country上各player的军队信息
                for (int i = 0; i < total_order; i++) {
                    o_player.nextOrder();
                }
            //其他指令, 计算本轮结果等

        }
    }
}

