package gameelements.order;

import command.CommandType;

/**
 * The class to represent an void order.
 */
public class Order {

    /**
     * The order info.
     */
    private OrderInfo d_OrderInfo;
    /**
     * The order type.
     */
    private CommandType d_Type;

    /**
     * Instantiates a new Order.
     */
    public Order() {
        d_Type = CommandType.getCommandFromLabel("void");
        d_OrderInfo = null;
    }

    /**
     * Executes a void order and the subclasses are supposed to override this method.
     *
     * @return false always since this is a void order
     */
    public boolean execute() {
        System.out.println("Void order is not able to execute");
        return false;
    }

    /**
     * Gets order info.
     *
     * @return the OrderInfo object
     */
    public OrderInfo getOrderInfo() {
        return d_OrderInfo;
    }

    /**
     * Sets order info.
     *
     * @param p_OrderInfo the order info
     */
    public void setOrderInfo(OrderInfo p_OrderInfo) {
        d_OrderInfo = p_OrderInfo;
    }

    /**
     * Gets the command type.
     *
     * @return the type
     */
    public CommandType getType() {
        return d_Type;
    }

    /**
     * Sets the command type.
     *
     * @param p_Type the type name
     */
    public void setType(String p_Type) {
        d_Type = CommandType.getCommandFromLabel(p_Type);
    }
}
