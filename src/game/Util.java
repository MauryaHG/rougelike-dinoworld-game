package game;

import java.util.Random;

/**
 * @author Jinyeop Oh
 * @version 1.0.0
 */
public abstract class Util {
    private static Random random = new Random();

    /**
     * A constant used to 1% chance with Ra
     */
    public static final int ONE_PERCENT_CHANCE = 100;

    /**
     * A constant used to 10% chance with Random object
     */
    public static final int TEN_PERCENT_CHANCE = 10;

    /**
     * A constant used to 5% chance with Random object
     */
    public static final int FIVE_PERCENT_CHANCE = 20;

    /**
     * A constant used to 50% chance with Random object
     */
    public static final int FIFTY_PERCENT_CHANCE = 2;

    public static boolean calcPercentage(int chance){
        if( random.nextInt(chance) == 0)
            return true;
        return false;
    }
}