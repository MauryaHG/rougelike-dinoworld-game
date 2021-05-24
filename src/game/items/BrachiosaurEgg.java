package game.items;

import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see Egg
 * @see Type
 */
public class BrachiosaurEgg extends Egg{

    /**
     * Sets the number of turns for hatching
     */
    private final int HATCH_AFTER = 10;


    /**
     * Constructor
     * Sets the the number of turns before hatching and egg type
     */
    public BrachiosaurEgg() {
        super("Brachiosaur Egg");
        super.setHatchAfter(HATCH_AFTER);
        super.setEggType(Type.BRACHIOSAUR_EGG);
    }
}
