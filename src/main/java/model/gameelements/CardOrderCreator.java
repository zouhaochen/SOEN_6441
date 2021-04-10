package model.gameelements;

import model.gameelements.order.*;

/**
 * The type Card order creator.
 */
public class CardOrderCreator {

    /**
     * Create card order.
     *
     * @param p_Card           the card
     * @param p_Initiator      the initiator
     * @param p_TargetPlayer   the target player
     * @param p_Departure      the departure
     * @param p_Destination    the destination
     * @param p_NumberOfArmies the number of armies
     * @return the order
     */
    public static Order createCardOrder(Card p_Card, Player p_Initiator, Player p_TargetPlayer, Country p_Departure,
                                        Country p_Destination, Country p_ToAttack, int p_NumberOfArmies) {
        // add the card to the committed card list
        p_Initiator.addCardToCommittedList(p_Card);

        switch (p_Card) {
            case AIRLIFT:
                return createAirliftOrder(p_Initiator, p_Departure, p_Destination, p_NumberOfArmies);
            case BOMB:
                return createBombOrder(p_Initiator, p_ToAttack);
            case BLOCKADE:
                return createBlockadeOrder(p_Initiator, p_Departure);
            case NEGOTIATE:
                return createDiplomacyOrder(p_Initiator, p_TargetPlayer);
            default:
                return null;
        }
    }

    /**
     * Create airlift order.
     *
     * @param p_Initiator      the initiator
     * @param p_Departure      the departure
     * @param p_Destination    the destination
     * @param p_NumberOfArmies the number of armies
     * @return the order
     */
    private static Order createAirliftOrder(Player p_Initiator, Country p_Departure,
                                            Country p_Destination, int p_NumberOfArmies) {
        p_Departure.setCommittedArmies(p_Departure.getCommittedArmies() + p_NumberOfArmies);
        return new AirliftOrder(p_Initiator, p_Departure, p_Destination, p_NumberOfArmies);
    }

    /**
     * Create bomb order.
     *
     * @param p_Initiator   the initiator
     * @param p_Destination the destination
     * @return the order
     */
    private static Order createBombOrder(Player p_Initiator, Country p_Destination) {
        return new BombOrder(p_Initiator, p_Destination);
    }

    /**
     * Create blockade order.
     *
     * @param p_Initiator   the initiator
     * @param p_Destination the destination
     * @return the order
     */
    private static Order createBlockadeOrder(Player p_Initiator, Country p_Destination) {
        return new BlockadeOrder(p_Initiator, p_Destination);
    }

    /**
     * Create diplomacy order.
     *
     * @param p_Initiator    the initiator
     * @param p_TargetPlayer the target player
     * @return the order
     */
    private static Order createDiplomacyOrder(Player p_Initiator, Player p_TargetPlayer) {
        return new DiplomacyOrder(p_Initiator, p_TargetPlayer);
    }

}
