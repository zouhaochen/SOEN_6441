package model.state.play;

import controller.MainPlayController;
import model.gameelements.Country;
import model.gameelements.Player;

import java.util.Map;

public class AssignCountry extends Startup{

    public AssignCountry(MainPlayController p_ml) {
        super(p_ml);
    }

    @Override
    public void assignCountries() {
        // randomly assign countries for each player
        d_ml.d_GameEngine.assignCountries();
        int l_CurrentReinforcement = 5;
        int l_TempReinforcementArmy;

        for (Player l_Player : d_ml.d_GameEngine.d_GameData.getPlayerList()) {
            l_CurrentReinforcement += d_ml.d_GameEngine.getReinforcementBonus(l_Player);
            l_Player.setReinforcementArmies(l_CurrentReinforcement);
            System.out.println(l_CurrentReinforcement + " Reinforcement Armies are assigned to " + " Player [" + l_Player.getColour() + "]  ");

            for (Map.Entry<String, Country> l_CountryEntry : l_Player.getCountriesInControl().entrySet()) {
                System.out.println("Controlling countries: " + l_CountryEntry.getKey());
            }
        }
        d_ml.getDLogEntryBuffer().updateFile();

        next();
    }

    @Override
    public void next() {
        d_ml.setPhase(new IssueOrder(d_ml));
    }
}
