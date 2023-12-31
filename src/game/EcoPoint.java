package game;

/**
 * @author Jinyeop Oh
 * @version 1.0.3
 */
abstract public class EcoPoint {
    /**
     *  An integer representing the eco point used in the game
     */
    private static int ecoPoint = 0;

    private static int defaultEcoPoint = 0;

    /**
     * Increment the ecoPoint by given ecoPoint. There is no limit.
     * @param ecoPoint An ineteger representing an ecoPoint wanting to be added
     */
    public static void increaseEcoPoint(int ecoPoint){
        EcoPoint.ecoPoint += ecoPoint;
    }


    /**
     * Subtract given ecoPoint from this.ecoPoint
     * @param ecoPoint A point of item that player wants to buy
     */
    public static void decreaseEcoPoint(int ecoPoint){
        EcoPoint.ecoPoint -= ecoPoint;
    }

    /**
     * Checks for validity that if the player can purchase from vending machine
     * @param ecoPoint An ineteger representing an ecoPoint wanting to be subtracted
     * @return true if possible, otherwise false
     */
    public static boolean canDecreaseEcoPoint(int ecoPoint){
        if( (EcoPoint.ecoPoint - ecoPoint) < 0 ){
            return false;
        }
        return true;
    }

    public static int getEcoPoint(){
        return ecoPoint;
    }

    public static void setEcoPointDefault(){
        EcoPoint.ecoPoint = EcoPoint.defaultEcoPoint;
    }

}
