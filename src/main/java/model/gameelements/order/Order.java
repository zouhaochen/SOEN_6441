package model.gameelements.order;

import command.CommandType;

/**
 * The class to represent an void order.
 */
public abstract class Order {

    /**
     * The order info.
     */
    private OrderInfo d_OrderInfo;
    /**
     * The order type.
     */
    protected CommandType d_Type;

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

    /**
     * Validate an order.
     *
     * @return true if the order is valid for execution, false otherwise
     */
    public abstract boolean valid();

    /**
     * Executes an order and the subclasses are supposed to override this method.
     *
     * @return true if the order executes successfully, false otherwise
     */
    public abstract boolean execute();
}
