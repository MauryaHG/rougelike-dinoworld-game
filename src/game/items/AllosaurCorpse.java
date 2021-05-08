package game.items;

/**
 * @author jinyeopoh
 * @version 1.0.1
 * @see DinosaurCorpse
 */
public class AllosaurCorpse extends DinosaurCorpse {
    /**
     * An int that the number of turns this corpse will rot away when it is on the ground
     */
    private final int ROT_AGE = 20;

    /**
     * Sets the name of this corpse and the number of turns this will rot away
     */
    public AllosaurCorpse() {
        super("Allosaur corpse");
        super.setRotAge(ROT_AGE);
    }
}
