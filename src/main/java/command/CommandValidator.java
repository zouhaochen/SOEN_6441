package command;

import model.GameData;

import java.io.File;
import java.io.IOException;

/**
 * The class to validate the commands from the console.
 */
public class CommandValidator {

    /**
     * The game data.
     */
    private static GameData D_GameData;

    /**
     * Sets game data.
     *
     * @param p_GameData the game data
     */
    public static void setGameData(GameData p_GameData) {
        D_GameData = p_GameData;
    }

    /**
     * Validate the command from console.
     *
     * @param p_Command the command from console
     * @return true if the command is valid, false otherwise
     */
    public static boolean validate(String p_Command) {
        if (p_Command == null || p_Command.isEmpty() || p_Command.trim().isEmpty()) {
            System.out.println("\nNo command is entered!");
            return false;
        }

        // validate the command based on the current game phase
        String[] l_Command_arr = p_Command.trim().split(" ");
        boolean l_Valid;
        String l_CurrentPhase = D_GameData.getCurrentPhase().getClass().getSimpleName();
        switch (l_CurrentPhase) {
            case "PostLoad":
                l_Valid = validatePostLoadCommands(l_Command_arr);
                break;
            case "Startup":
            case "LoadMap":
                l_Valid = validateLoadMapCommands(l_Command_arr);
                break;
            case "AddPlayer":
                l_Valid = validateAddPlayerCommands(l_Command_arr);
                break;
            case "AssignCountry":
                l_Valid = validateAssignCountryCommands(l_Command_arr);
                break;
            case "IssueOrder":
                l_Valid = validateIssueOrderCommands(l_Command_arr);
                break;
            default:
                l_Valid = false;
        }

        return l_Valid;
    }

    /**
     * Validate post load commands boolean.
     *
     * @param p_CommandArr the command arr
     * @return true if the command is valid, false otherwise
     */
    private static boolean validatePostLoadCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;

        if (CommandType.getCommandFromLabel(l_CommandType) == null) {
            printInvalidCommandType();
            return false;
        }

        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case EDIT_MAP:
            case EDIT_CONTINENT:
            case EDIT_COUNTRY:
            case EDIT_NEIGHBOR:
            case SAVE_MAP:
            case VALIDATE_MAP:
            case EXIT:
                l_Result = true;
                break;
            case SHOW_MAP:
                l_Result = validateNoArgumentCommand(p_CommandArr);
                break;
            default:
                printInvalidCommandInCurrentPhase();
                return false;
        }
        return l_Result;
    }

    /**
     * Validates the commands allowed in the "issue orders" phase.
     *
     * @param p_CommandArr the command from console
     * @return true if the command is valid, false otherwise
     */
    private static boolean validateIssueOrderCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;

        if (CommandType.getCommandFromLabel(l_CommandType) == null) {
            printInvalidCommandType();
            return false;
        }

        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case SHOW_MAP:
                l_Result = validateNoArgumentCommand(p_CommandArr);
                break;
            case DEPLOY:
                l_Result = validateDeploy(p_CommandArr);
                break;
            case ADVANCE:
            case AIRLIFT:
                l_Result = validateAdvance(p_CommandArr);
                break;
            case BOMB:
            case BLOCKADE:
            case NEGOTIATE:
                l_Result = validateSingleTargetCommand(p_CommandArr);
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
    private static boolean validateLoadMapCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;

        if (CommandType.getCommandFromLabel(l_CommandType) == null) {
            printInvalidCommandType();
            return false;
        }

        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case LOAD_MAP:
                l_Result = validateLoadMap(p_CommandArr);
                break;
            default:
                printInvalidCommandInCurrentPhase();
                return false;
        }
        return l_Result;
    }

    /**
     * to validate if the input of Command AddPlayer is correct.
     *
     * @param p_CommandArr command array as string type
     * @return return the value with boolean type to check if command is valid.
     */
    private static boolean validateAddPlayerCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;

        if (CommandType.getCommandFromLabel(l_CommandType) == null) {
            printInvalidCommandType();
            return false;
        }

        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case SHOW_MAP:
                l_Result = validateNoArgumentCommand(p_CommandArr);
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
     * to validate if the input of Command assign country is correct.
     *
     * @param p_CommandArr command array as string type
     * @return return the value with boolean type to check if command is valid.
     */
    private static boolean validateAssignCountryCommands(String[] p_CommandArr) {
        String l_CommandType = p_CommandArr[0].toLowerCase();
        boolean l_Result;

        if (CommandType.getCommandFromLabel(l_CommandType) == null) {
            printInvalidCommandType();
            return false;
        }

        switch (CommandType.getCommandFromLabel(l_CommandType)) {
            case SHOW_MAP:
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
    private static boolean validateNoArgumentCommand(String[] p_CommandArr) {
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
    private static boolean validateLoadMap(String[] p_CommandArr) {
        if (p_CommandArr.length != 2) {
            printInvalidArguments();
            return false;
        }

        String l_FileName = p_CommandArr[1];
        boolean l_MapFileExist;

        // check if the model.map file exists
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
    private static boolean validateAddPlayer(String[] p_CommandArr) {
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
    private static boolean validateDeploy(String[] p_Command_arr) {
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
     * Validates ADVANCE and AIRLIFT command.
     *
     * @param p_Command_arr an array of command components from the console
     * @return true if the ADVANCE or AIRLIFT command is valid, false otherwise
     */
    private static boolean validateAdvance(String[] p_Command_arr) {
        if (p_Command_arr.length != 4) {
            printInvalidArguments();
            return false;
        }

        try {
            Integer.parseInt(p_Command_arr[3]);
        } catch (Exception p_Exception) {
            printInvalidArguments();
            return false;
        }

        return true;
    }

    /**
     * Validates single-target command.
     *
     * @param p_Command_arr an array of command components from the console
     * @return true if the single-target command is valid, false otherwise
     */
    private static boolean validateSingleTargetCommand(String[] p_Command_arr) {
        if (p_Command_arr.length != 2) {
            printInvalidArguments();
            return false;
        }

        return true;
    }

    /**
     * Get a model.map file from an existing "domination" model.map file
     *
     * @param p_FileName name of the model.map file
     * @return true if the file exists, false otherwise
     * @throws IOException if file not found or cannot read
     */
    private static boolean isMapFileExist(String p_FileName) throws IOException {
        String l_ProjectPath = new File("").getCanonicalPath();
        File l_File = new File(l_ProjectPath + "/domination/" + p_FileName);

        if (!l_File.exists()) {
            System.out.println("\nNo such model.map file exists!");
            return false;
        }
        return true;
    }

    /**
     * Prints a warning of invalid arguments to the console
     */
    private static void printInvalidArguments() {
        System.out.println("\nInvalid command arguments!");
    }

    /**
     * Prints a warning of invalid command to the console
     */
    private static void printInvalidCommandInCurrentPhase() {
        System.out.println("Invalid command in phase " + D_GameData.getCurrentPhase().getClass().getSimpleName());
    }

    /**
     * Prints a warning of invalid command to the console
     */
    private static void printInvalidCommandType() {
        System.out.println("Invalid command type. There is no such command.");
    }


}
