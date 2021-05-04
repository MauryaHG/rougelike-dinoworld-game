package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.HarvestAction;
import game.items.Fruit;

/**
 * @author Jinyeop Oh
 * @version 1.1.1
 * @see Ground
 */
public class Bush extends Ground {
    /**
     * Constructor.
     */
    public Bush() {
        super('*');
        addCapability(Type.BUSH);
    }
}
