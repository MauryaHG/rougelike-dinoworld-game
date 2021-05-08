package game.items;

import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see DinosaurCorpse
 */
public class StegosaurCorpse extends DinosaurCorpse {
    /**
     * An int that the number of turns this corpse will rot away when it is on the ground
     */
    private final int ROT_AGE = 20;

    /**
     * Sets the name of this corpse and the number of turns this will rot away
     */
    public StegosaurCorpse() {
        super("Stego corpse");
        this.addCapability(Type.STEGOSAUR_CORPSE);
        super.setRotAge(ROT_AGE);
    }
}
