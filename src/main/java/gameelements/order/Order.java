package gameelements.order;

public class Order {

	private OrderInfo d_orderInfo;
	private String d_name;

	public Order() {
		d_name = "void";
		d_orderInfo = null;
	}

	public boolean execute() {
		System.out.println("Void order is not able to execute");
		return false;
	}

	public OrderInfo getOrderInfo() {
		return d_orderInfo;
	}

	public void setOrderInfo(OrderInfo p_orderInfo) {
		this.d_orderInfo = p_orderInfo;
	}

	public String getName() {
		return d_name;
	}

	public void setName(String p_name) {
		d_name = p_name;
	}
}
