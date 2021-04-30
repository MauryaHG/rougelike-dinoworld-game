package game.items;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.Type;
import game.Util;
import game.actions.HarvestAction;

/**
 * @author Jinyeop Oh
 * @version 1.1.1
 * @see Util
 */
public class Fruit extends PortableItem {
    /**
     * An age of this fruit. It is incremented every turn
     */
    private int age = 0;



    public Fruit() {
        super("Fruit", 'F');
        addCapability(Type.FRUIT);
    }

    /**
     * Calculate dropping percentage and return the outcome
     * @return true if dropped, otherwise false
     */
    public boolean isDropped(){
        if(Util.calcPercentage(Util.FIVE_PERCENT_CHANCE)){
            return true;
        }
        return false;
    }

    /**
     * Check if this fruit is rotted.
     * @return true after 15 turns after dropped.
     */
    public boolean isRotted(){
        age++;

        if( age > 15 ){
            return true;
        }
        return false;

    }

}
