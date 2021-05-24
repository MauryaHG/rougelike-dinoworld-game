package game.items;

import game.Type;
/**
 * @author Maurya
 * @version 1.0.0
 * @see DinosaurCorpse
 */
public class PetrodactylCorpse extends DinosaurCorpse {
        /**
         * An int that the number of turns this corpse will rot away when it is on the ground
         */
        private final int ROT_AGE = 15;

    /**
     * Sets the name of this corpse and the number of turns this will rot away
     */
    public PetrodactylCorpse() {
        super("Petro corpse", 30);
        this.addCapability(Type.PTERODACTYL_CORPSE);
        super.setRotAge(ROT_AGE);
    }
}

