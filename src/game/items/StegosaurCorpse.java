package game.items;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see DinosaurCorpse
 */
public class StegosaurCorpse extends DinosaurCorpse {

    private final int ROT_AGE = 20;
    public StegosaurCorpse() {
        super("Stego corpse");
        super.setRotAge(ROT_AGE);
    }
}
