import command.CommandValidator;
import gameelements.Country;
import gameelements.Player;
import gameplay.GameData;
import gameplay.GamePhase;

public class PlayerDemo {

	public static void main(String[] args) {

		GameData gameData = new GameData();
		gameData.setCurrentPhase(GamePhase.STARTUP);
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

		System.out.println("\nArmies left: " + player.getReinforcementArmies());
	}
}
