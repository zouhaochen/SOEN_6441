//import command.CommandValidator;
//import gameplay.GameData;
//import gameplay.GamePhase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CommandValidatorDemo {
//	public static void main(String[] args) {
//		GameData gameData = new GameData();
//		gameData.setCurrentPhase(GamePhase.STARTUP);
//		CommandValidator commandValidator = new CommandValidator(gameData);
//		List<String> l_Commands = new ArrayList<>();
//		l_Commands.add("deploy London 1");
//		l_Commands.add("loadmap 05.map");
//		l_Commands.add("gameplayer -add hi");
//
//		for (String l_Command : l_Commands) {
//			System.out.println("The command to validate: " + l_Command);
//			System.out.println("Result: " + commandValidator.validate(l_Command));
//		}
//
//	}
//}
