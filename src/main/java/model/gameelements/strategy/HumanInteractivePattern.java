package model.gameelements.strategy;

import command.CommandValidator;
import model.GameData;
import model.gameelements.Player;
import model.gameelements.order.Order;
import model.gameelements.order.OrderFactory;

import java.util.Scanner;

/**
 * The type Human interactive pattern.
 */
public class HumanInteractivePattern extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public HumanInteractivePattern(Player p_Player, GameData p_GameData) {
        super(p_Player, p_GameData);
    }

    /**
     * Create order order.
     *
     * @return the order
     */
    @Override
    public Order createOrder() {
        String l_Reply;
        Scanner l_Scanner = new Scanner(System.in);
        do {
            System.out.println("\nPlayer " + getPlayer().getColour() + ", do you want to create an order? (y/n) ");
            l_Reply = l_Scanner.nextLine().trim();
        } while (!l_Reply.equalsIgnoreCase("y") && !l_Reply.equalsIgnoreCase("n"));

        if (l_Reply.equalsIgnoreCase("y")) {

            // read the command from a player
            String l_Command;
            do {
                System.out.println("\nPlease enter the command: \n");
                l_Command = l_Scanner.nextLine();
            } while (!CommandValidator.validate(l_Command));

            // create an order
            String[] l_CommandArr = l_Command.split(" ");
            return OrderFactory.CreateOrder(l_CommandArr, getPlayer());
        }

        return null;
    }

    /**
     * Reset strategy states.
     */
    @Override
    public void reset() {

    }
}
