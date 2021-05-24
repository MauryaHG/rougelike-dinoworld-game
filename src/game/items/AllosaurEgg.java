package game.items;

import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.1
 * @see Egg
 * @see Type
 */
public class AllosaurEgg extends Egg{
    /**
     * Sets the number of turns for hatching
     */
    private final int HATCH_AFTER = 20;


    /**
     * Constructor
     * Sets the the number of turns before hatching and egg type
     */
    public AllosaurEgg() {
        super("Allosaur Egg");
        super.setHatchAfter(HATCH_AFTER);
        super.setEggType(Type.ALLOSAUR_EGG);
    }


}
