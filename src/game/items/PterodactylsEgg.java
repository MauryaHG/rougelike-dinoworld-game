package game.items;

import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
 */
public class PterodactylsEgg extends Egg{

    /**
     * Sets the number of turns for hatching
     */
    private final int HATCH_AFTER = 10;


    /**
     * Constructor
     * Sets the the number of turns before hatching and egg type
     */
    public PterodactylsEgg() {
        super("Pterodactyls Egg");
        super.setHatchAfter(HATCH_AFTER);
        super.setEggType(Type.PTERODACTYLS_EGG);
    }

}
