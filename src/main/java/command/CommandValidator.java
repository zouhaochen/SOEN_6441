package command;

import gameplay.GameData;

import java.io.File;
import java.io.IOException;

/**
 * The class to validate the commands from the console.
 */
public class CommandValidator {

    /**
     * The game data.
     */
    private GameData d_GameData;

    /**
     * Instantiates a new object of type CommandValidator.
     *
     * @param p_GameData the game data
     */
    public CommandValidator(GameData p_GameData) {
        d_GameData = p_GameData;
    }

    /**
     * Validate the command from console.
     *
     * @param p_Command the command from console
     * @return true if the command is valid, false otherwise
     */
    public boolean validate(String p_Command) {
        if (p_Command == null || p_Command.isEmpty() || p_Command.trim().isEmpty()) {
            System.out.println("\nNo command is entered!");
            return false;
        }

        // validate the command based on the current game phase
        String[] l_Command_arr = p_Command.trim().split(" ");
        boolean l_Valid;
        switch (d_GameData.getCurrentPhase()) {
            case MAP_EDIT:
                l_Valid = true;
                break;
            case STARTUP:
                l_Valid = validateStartupCommands(l_Command_arr);
                break;
            case ISSUE_ORDER:
                l_Valid = validateIssueOrderCommands(l_Command_arr);
                break;
            default:
                l_Valid = false;
        }

        return l_Valid;
    }

    /**
     * Validates the commands allowed in the "issue orders" phase.
     *
     * @param p_CommandArr the command from console
     * @return true if the command is valid, false otherwise
     */
    private boolean validateIssueOrderCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;
        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case SHOW_MAP:
                l_Result = validateNoArgumentCommand(p_CommandArr);
                break;
            case DEPLOY:
                l_Result = validateDeploy(p_CommandArr);
                break;
            default:
                printInvalidCommandInCurrentPhase();
                return false;
        }

        return l_Result;
    }

    /**
     * Validates the commands allowed in the startup phase.
     *
     * @param p_CommandArr the command from console
     * @return true if the command is valid, false otherwise
     */
    private boolean validateStartupCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;
        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case LOAD_MAP:
                l_Result = validateLoadMap(p_CommandArr);
                break;
            case ADD_PLAYER:
                l_Result = validateAddPlayer(p_CommandArr);
                break;
            case ASSIGN_COUNTRIES:
                l_Result = validateNoArgumentCommand(p_CommandArr);
                break;
            default:
                printInvalidCommandInCurrentPhase();
                return false;
        }
        return l_Result;
    }

    /**
     * Validates the commands requiring no arguments.
     *
     * @param p_CommandArr an array of command components from the console
     * @return true if the command length is 1; false otherwise
     */
    private boolean validateNoArgumentCommand(String[] p_CommandArr) {
        if (p_CommandArr.length != 1) {
            printInvalidArguments();
            return false;
        }

        return true;
    }

    /**
     * Validates LOAD_MAP command.
     *
     * @param p_CommandArr an array of command components from the console
     * @return true if the LOAD_MAP command is valid, false otherwise
     */
    private boolean validateLoadMap(String[] p_CommandArr) {
        if (p_CommandArr.length != 2) {
            printInvalidArguments();
            return false;
        }

        String l_FileName = p_CommandArr[1];
        boolean l_MapFileExist;

        // check if the map file exists
        try {
            l_MapFileExist = isMapFileExist(l_FileName);
        } catch (IOException p_Exception) {
            return false;
        }

        return l_MapFileExist;
    }

    /**
     * Validates ADD_PLAYER command.
     *
     * @param p_CommandArr an array of command components from the console
     * @return true if the ADD_PLAYER command is valid, false otherwise
     */
    private boolean validateAddPlayer(String[] p_CommandArr) {
        if (p_CommandArr.length < 3 || p_CommandArr.length % 2 != 1) {
            System.out.println("\nInvalid command arguments!");
            return false;
        }

        String l_AddTag = "-add";
        String l_RemoveTag = "-remove";
        for (int l_Index = 1; l_Index < p_CommandArr.length; ) {
            if (!p_CommandArr[l_Index].equalsIgnoreCase(l_AddTag) && !p_CommandArr[l_Index].equalsIgnoreCase(l_RemoveTag)) {
                printInvalidArguments();
                return false;
            }
            l_Index += 2;
        }

        return true;
    }

    /**
     * Validates DEPLOY command.
     *
     * @param p_Command_arr an array of command components from the console
     * @return true if the DEPLOY command is valid, false otherwise
     */
    private boolean validateDeploy(String[] p_Command_arr) {
        if (p_Command_arr.length != 3) {
            printInvalidArguments();
            return false;
        }

        try {
            Integer.parseInt(p_Command_arr[2]);
        } catch (Exception p_Exception) {
            printInvalidArguments();
            return false;
        }

        return true;
    }

    /**
     * Get a map file from an existing "domination" map file
     *
     * @param p_FileName name of the map file
     * @return true if the file exists, false otherwise
     * @throws IOException if file not found or cannot read
     */
    private boolean isMapFileExist(String p_FileName) throws IOException {
        String l_ProjectPath = new File("").getCanonicalPath();
        File l_File = new File(l_ProjectPath + "/domination/" + p_FileName);

        if (!l_File.exists()) {
            System.out.println("\nNo such map file exists!");
            return false;
        }
        return true;
    }

    /**
     * Prints a warning of invalid arguments to the console
     */
    private void printInvalidArguments() {
        System.out.println("\nInvalid command arguments!");
    }

    /**
     * Prints a warning of invalid command to the console
     */
    private void printInvalidCommandInCurrentPhase() {
        System.out.println("Invalid command in the current game phase!");
    }


}
