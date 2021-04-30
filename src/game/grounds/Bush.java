package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.HarvestAction;
import game.items.Fruit;

/**
 * @author Jinyeop Oh
 * @version 1.1.0
 * @see Fruit
 */
public class Bush extends Ground {
    private boolean hasFruit = false;

    /**
     * Constructor.
     */
    public Bush() {
        super(';');
        addCapability(Type.BUSH);
    }


    public boolean hasFruit() {
        return hasFruit;
    }

    public void setHasFruitTrue() {
        this.hasFruit = true;
    }
}
