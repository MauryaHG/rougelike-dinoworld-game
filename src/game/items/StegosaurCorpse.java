package game.items;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see DinosaurCorp
 */
public class StegosaurCorpse extends DinosaurCorp{

    private final int ROT_AGE = 20;
    public StegosaurCorpse() {
        super("Stego corpse");
        super.setRotAge(ROT_AGE);
    }
}
