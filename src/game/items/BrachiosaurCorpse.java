package game.items;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see DinosaurCorpse
 */
public class BrachiosaurCorpse extends DinosaurCorpse {
    private final int ROT_AGE = 40;
    public BrachiosaurCorpse() {
        super("Brachio corpse");
        super.setRotAge(ROT_AGE);
    }
}
