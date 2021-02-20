package command;

import gameplay.GameData;

public class CommandValidator {

	private GameData d_gameData;

	public CommandValidator(GameData p_gameData) {
		d_gameData = p_gameData;
	}

	public boolean validate(String p_command) {
		if (p_command == null || p_command.isEmpty()) {
			return false;
		}

		String[] l_command_arr = p_command.split(" ");
		boolean isValid = false;
		switch (d_gameData.getCurrentPhase()) {
			case MAP_EDIT:
				isValid = validateMapCommands(l_command_arr);
				break;
			case STARTUP:
				isValid = validateStartupCommands(l_command_arr);
				break;
			case ISSUE_ORDER:
				isValid = validateIssueOrderCommands(l_command_arr);
				break;
			default:
				throw new IllegalStateException("Unexpected game phase!");
		}

		return isValid;
	}

	private boolean validateIssueOrderCommands(String[] p_command_arr) {
		// todo
		return true;
	}

	private boolean validateStartupCommands(String[] p_command_arr) {
		// todo
		return true;
	}

	private boolean validateMapCommands(String[] p_command_arr) {
		// todo
		return true;
	}

}
