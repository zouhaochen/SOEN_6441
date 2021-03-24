package model.gameelements.order;

import model.GameData;
import model.gameelements.Player;

/**
 * The class to create an object of subclass of Type Order based on Factory design pattern.
 */
public class OrderFactory {

    /**
     * Game data
     */
    private static GameData D_GameData;

    /**
     * Sets game data.
     *
     * @param p_GameData the p game data
     */
    public static void setGameData(GameData p_GameData) {
        D_GameData = p_GameData;
    }

    /**
     * Create an object of type Order.
     *
     * @param p_Command the command from console
     * @param p_Player  the object of type Player
     * @return the order
     */
    public static Order CreateOrder(String[] p_Command, Player p_Player) {
        String l_Type = p_Command[0].toLowerCase();
        Order l_Order;
        switch (l_Type) {
            case "deploy":
                l_Order = new DeployOrder();
                l_Order.setOrderInfo(generateDeployOrderInfo(p_Command, p_Player));
                break;
            case "advance":
                l_Order = new AdvanceOrder(generateThreeArgumentOrderInfoFromCommand(p_Command, p_Player));
                break;
            case "bomb":
                l_Order = new BombOrder(generateOneArgumentOrderInfoFromCommand(p_Command, p_Player));
                break;
            case "blockade":
                l_Order = new BlockadeOrder(generateOneArgumentOrderInfoFromCommand(p_Command, p_Player));
                break;
            case "airlift":
                l_Order = new AirliftOrder(generateThreeArgumentOrderInfoFromCommand(p_Command, p_Player));
                break;
            case "negotiate":
                l_Order = new DiplomacyOrder(generateDiplomacyOrderInfoFromCommand(p_Command, p_Player));
                break;
            default:
                System.out.println("\nFail to create an order due to invalid arguments");
                l_Order = null;
        }
        return l_Order;
    }

    /**
     * Generates an object of OrderInfo for DeployOrder
     *
     * @param p_Command the command from console
     * @param p_Player  the object of type Player
     * @return an object of type OrderInfo
     */
    private static OrderInfo generateDeployOrderInfo(String[] p_Command, Player p_Player) {
        String l_CountryID = p_Command[1];
        int l_NumberOfArmy = getIntegerFromCommand(p_Command[2]);

        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Player);
        l_OrderInfo.setDestination(D_GameData.getCountryByName(l_CountryID));
        l_OrderInfo.setNumberOfArmy(l_NumberOfArmy);
        l_OrderInfo.setGameData(D_GameData);

        return l_OrderInfo;
    }

    /**
     * Generate three-argument order info from command.
     *
     * @param p_Command the p command
     * @param p_Player  the p player
     * @return the order info
     */
    private static OrderInfo generateThreeArgumentOrderInfoFromCommand(String[] p_Command, Player p_Player) {
        String l_DepartureCountryId = p_Command[1];
        String l_DestinationCountryId = p_Command[2];
        int l_NumberOfArmy = getIntegerFromCommand(p_Command[3]);

        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Player);
        l_OrderInfo.setDeparture(D_GameData.getCountryByName(l_DepartureCountryId));
        l_OrderInfo.setDestination(D_GameData.getCountryByName(l_DestinationCountryId));
        l_OrderInfo.setNumberOfArmy(l_NumberOfArmy);
        l_OrderInfo.setGameData(D_GameData);

        return l_OrderInfo;
    }

    /**
     * Generate one-argument order info from command.
     *
     * @param p_Command the command
     * @param p_Player  the player
     * @return the order info
     */
    private static OrderInfo generateOneArgumentOrderInfoFromCommand(String[] p_Command, Player p_Player) {
        String l_DestinationCountryId = p_Command[1];

        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Player);
        l_OrderInfo.setDestination(D_GameData.getCountryByName(l_DestinationCountryId));
        l_OrderInfo.setGameData(D_GameData);

        return l_OrderInfo;
    }

    /**
     * Generate diplomacy order info from command.
     *
     * @param p_Command the command
     * @param p_Player  the player
     * @return the order info
     */
    private static OrderInfo generateDiplomacyOrderInfoFromCommand(String[] p_Command, Player p_Player) {
        String l_TargetPlayerName = p_Command[1];
        Player l_TargetPlayer = D_GameData.getPlayerByName(l_TargetPlayerName);

        OrderInfo l_OrderInfo = new OrderInfo();
        l_OrderInfo.setInitiator(p_Player);
        l_OrderInfo.setTargetPlayer(l_TargetPlayer);
        l_OrderInfo.setGameData(D_GameData);

        return l_OrderInfo;
    }

    /**
     * Gets integer from command.
     *
     * @param p_Value the string value
     * @return an integer
     */
    private static int getIntegerFromCommand(String p_Value) {
        int result = 0;
        try {
            result = Integer.parseInt(p_Value);
            return result;
        } catch (NumberFormatException l_Exception) {
            return result;
        }
    }
}
