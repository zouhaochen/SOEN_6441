import command.CommandValidator;
import gameelements.Country;
import gameelements.Player;
import gameplay.GameData;
import gameplay.GamePhase;

import java.io.File;

public class PlayerDemo {

	public static void main(String[] args) {
        File map=new File("test_02.map");
		GameData gameData = new GameData(map);
		gameData.setCurrentPhase(GamePhase.ISSUE_ORDER);
		System.out.println(gameData.getCurrentPhase());

		CommandValidator commandValidator = new CommandValidator(gameData);

		Player player = new Player("Red", commandValidator);
		Country london = new Country("London");
		Country liverpool = new Country("Liverpool");
		player.assignCountry(london);
		player.assignCountry(liverpool);
		player.assignCountry(liverpool);
		player.setReinforcementArmies(5);

		player.issueOrder();


		player.nextOrder().execute();


	}
}
