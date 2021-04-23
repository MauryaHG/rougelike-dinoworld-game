package game;

/**
 * @author Jinyeop Oh
 * @version 1.0.0
 */
public class EcoPoint {
    /**
     *  An integer representing the eco point used in the game
     */
    private static int ecoPoint = 0;

    /**
     * Increment the ecoPoint by given ecoPoint. There is no limit.
     * @param ecoPoint An ineteger representing an ecoPoint wanting to be added
     */
    public static void increaseEcoPoint(int ecoPoint){
        EcoPoint.ecoPoint += ecoPoint;
    }

    /**
     * Checks for validity if ecoPoint goes down below 0. In this case, does not decrease and return false.
     * Otherwise, decrease by given ecoPoint and return true
     * @param ecoPoint An ineteger representing an ecoPoint wanting to be subtracted
     * @return true if subtracted, otherwise false
     */
    public static boolean decreaseEcoPoint(int ecoPoint){
        if( (EcoPoint.ecoPoint - ecoPoint) < 0 ){
            return false;
        }
        EcoPoint.ecoPoint -= ecoPoint;
        return true;
    }

}
