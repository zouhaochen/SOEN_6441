package gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is game main controller
 */
public class GameEngine {

    int d_total_players;
    int d_total_country;
    ArrayList<String> country_name;
    ArrayList<String> country_list;
    ArrayList<String> continent_list;
    HashMap<String, Integer> army_per_country = new HashMap<String, Integer>();
//    Map map;

    /**
     * GameEngine Constructor
     * @param total_country for import total country
     * @param total_players import total players number
     * @param country_list country list
     * @param continent_list continent list
     */
    public GameEngine(int total_country, int total_players, ArrayList<String> country_list, ArrayList<String> continent_list){
        d_total_country=total_country;
        d_total_players=total_players;
        this.continent_list=country_list;
        this.continent_list = continent_list;
    }
    /**
     * this method used to show map in game
     */
    public void showMap() {

    }

    /**
     * this method is current player phase process, player's order will be processed here.
     *
     */
    public void phaseProcess(){

    }

    /**
     * This method will calculate reinforcement armies ()
     * @param countries This parameter contains list of countries for current player.
     * @param continent_country_count This parameter contains continents and total number of countries in each continent.
     * @param country_continent This parameter contains countries mapped to it's continent.
     * @return This method will return calculated reinforced armies.
     */
    public Integer calReinforcementArmies(List<String> countries, Map<String, Integer> continent_country_count, Map<String, String> country_continent, Map<String, Integer> continent_value) {
        int reinforced_armies = Math.max(countries.size() / 3, 3);
        int n = countries.size();
        List<String> new_continent = new ArrayList<String>();
        for(int i=0;i<n;i++) {
            String continent = country_continent.get(countries.get(i));
            if(new_continent.contains(continent)) {
                break;
            }
            new_continent.add(continent);
            int continent_have_countries = continent_country_count.get(continent);
            int flag = continent_have_countries;
            for(int j=0;j<n;j++) {
                if(continent == country_continent.get(countries.get(j))) {
                    flag--;
                }
                if(flag == 0)
                    break;
            }
            if(flag == 0) {
                reinforced_armies += continent_value.get(continent);
            }
        }
        return reinforced_armies;
    }
}
