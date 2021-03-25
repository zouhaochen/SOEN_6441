package model.gameelements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Card enum for the various types of cards players can use during gameplay
 */
public enum Card {
    /**
     * BOMB card
     */
    BOMB,
    /**
     * BLOCKADE card
     */
    BLOCKADE,
    /**
     * AIRLIFT card
     */
    AIRLIFT,
    /**
     * NEGOTIATE card
     */
    NEGOTIATE;


    /**
     * the enum list ot a array card list
     */
    private static final List<Card> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

    /**
     * size of card list
     */
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * just return a random from enum list
     *
     * @return a random card
     */
    public static Card getRandomCard() {
        return VALUES.get(RANDOM.nextInt(SIZE));

    }
}
