package command;

import java.util.stream.Stream;

/**
 * The enum CommandType.
 */
public enum CommandType {

    /**
     * Void command type.
     */
    VOID("void"),
    /**
     * Edit continent command type.
     */
    EDIT_CONTINENT("editcontinent"),
    /**
     * Edit country command type.
     */
    EDIT_COUNTRY("editcountry"),
    /**
     * Edit neighbor command type.
     */
    EDIT_NEIGHBOR("editneighbor"),
    /**
     * Show model.map command type.
     */
    SHOW_MAP("showmap"),
    /**
     * Save model.map command type.
     */
    SAVE_MAP("savemap"),
    /**
     * Edit model.map command type.
     */
    EDIT_MAP("editmap"),
    /**
     * Validate model.map command type.
     */
    VALIDATE_MAP("validatemap"),
    /**
     * Load model.map command type.
     */
    LOAD_MAP("loadmap"),
    /**
     * Add player command type.
     */
    ADD_PLAYER("gameplayer"),
    /**
     * Assign countries command type.
     */
    ASSIGN_COUNTRIES("assigncountries"),
    /**
     * Deploy command type.
     */
    DEPLOY("deploy");

    /**
     * The label of the command.
     */
    private final String d_Label;


    /**
     * Private constructor of the Enum type.
     *
     * @param p_Label the label of the command
     */
    CommandType(String p_Label) {
        d_Label = p_Label;
    }

    /**
     * Gets Enum of CommandType based on the label.
     *
     * @param p_Label the label of the command
     * @return a object of type CommandType
     */
    public static CommandType getCommandFromLabel(String p_Label) {
        return Stream.of(CommandType.values()).filter(c -> c.d_Label.equalsIgnoreCase(p_Label)).findFirst().orElse(null);
    }

    /**
     * Returns the label of the CommandType object.
     *
     * @return the label of command type
     */
    public String getLabel() {
        return d_Label;
    }

}
