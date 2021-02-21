package gameelements.order;

import gameelements.Player;

public class OrderFactory {

	public static Order CreateOrder(String[] p_command, Player p_player) {
		String l_type = p_command[0].toLowerCase();
		Order l_order;
		switch (l_type){
			case "deploy":
				l_order = new DeployOrder();
				l_order.setOrderInfo(generateDeployOrderInfo(p_command, p_player));
				break;
			default:
				throw new IllegalStateException("Fail to create an order due to invalid arguments");
		}
		return l_order;
	}

	private static OrderInfo generateDeployOrderInfo(String[] p_command, Player p_player) {
		String l_countryID = p_command[1];
		int l_numberOfArmy = Integer.parseInt(p_command[2]);

		OrderInfo l_orderInfo = new OrderInfo();
		l_orderInfo.setPlayer(p_player);
		l_orderInfo.setDestination(l_countryID);
		l_orderInfo.setNumberOfArmy(l_numberOfArmy);

		return l_orderInfo;
	}
}
