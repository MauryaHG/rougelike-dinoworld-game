package game.items;

import edu.monash.fit2099.engine.Item;
import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
 */
public class Fish extends Item {
    public Fish() {
        super("Fish", 'w', false);
        this.addCapability(Type.FISH);
    }
}
