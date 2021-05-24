package game.items;

import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see Egg
 * @see Type
 */
public class StegosaurEgg extends Egg{

    /**
     * Sets the number of turns for hatching
     */
    private final int HATCH_AFTER = 10;


    /**
     * Constructor
     * Sets the the number of turns before hatching and egg type
     */
    public StegosaurEgg() {
        super("Stegosaur Egg");
        super.setHatchAfter(HATCH_AFTER);
        super.setEggType(Type.STEGOSAUR_EGG);
    }

}
