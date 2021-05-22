package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

/**
 * @author Jinyeop Oh and Maurya Gamage
 * @version 1.1.0
 */
public abstract class Util {
    private static Random random = new Random();

    public static final int ONE_PERCENT_CHANCE = 10;
    public static final int ZERO_ONE_PERCENT_CHANCE = 1;
    public static final int TEN_PERCENT_CHANCE = 100;
    public static final int FIVE_PERCENT_CHANCE = 50;
    public static final int FIFTY_PERCENT_CHANCE = 500;
    public static final int TWENTY_PERCENT_CHANCE = 200;
    public static final int SIXTY_PERCENT_CHANCE = 600;

    private static boolean raining = false;

    /**
     * Retrieves target chance, calculate it and then return boolean value
     * @param chance An int of target percentage
     * @return A Boolean that outcome of calculation
     */
    public static boolean calcPercentage(int chance){
        // Range 1 to 1000
        if( random.nextInt(1000)+1 <= chance)
            return true;
        return false;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    public static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }


    /**
     * Checks if two actors are of same gender
     * @param actor actor whose gender is checked with
     * @param otherActor the other actor whose gender is checked with
     * @return
     */
    public static boolean isBreedable(Actor actor, Actor otherActor) {
        boolean isValid = false;
        if(!actor.hasCapability(Type.BABY))
        if(!(actor.hasCapability(Type.PREGNANT) || otherActor.hasCapability(Type.PREGNANT))){
            if ((actor.hasCapability(Type.STEGOSAUR) && otherActor.hasCapability(Type.STEGOSAUR)) ||
                    (actor.hasCapability(Type.BRACHIOSAUR) && otherActor.hasCapability(Type.BRACHIOSAUR)) ||
                    (actor.hasCapability(Type.ALLOSAUR) && otherActor.hasCapability(Type.ALLOSAUR))) {  
                if ((actor.hasCapability(Type.MALE)) && otherActor.hasCapability(Type.FEMALE)) {
                    isValid = true;
                }
                if ((actor.hasCapability(Type.FEMALE)) && otherActor.hasCapability(Type.MALE)) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }


    /**
     * Gender MALE or FEMALE is chosen randomly and returned
     * @return
     */
    public static Type getGender(){
        if(new Random().nextBoolean()) {
            return Type.MALE;
        } else {
            return Type.FEMALE;
        }
    }

    public static boolean isRaining() {
        return raining;
    }

    public static void setRaining(boolean isRaining) {
        Util.raining = isRaining;
    }
}
