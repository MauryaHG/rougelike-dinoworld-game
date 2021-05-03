package game.items;

import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
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
     * @param name
     */
    public AllosaurEgg(String name) {
        super(name);
        super.setHatchAfter(HATCH_AFTER);
        super.setEggType(Type.ALLOSAUR_EGG);
    }


}
