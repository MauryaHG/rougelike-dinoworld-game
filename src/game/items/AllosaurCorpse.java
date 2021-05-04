package game.items;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see DinosaurCorpse
 */
public class AllosaurCorpse extends DinosaurCorpse {

    private final int ROT_AGE = 20;


    public AllosaurCorpse() {
        super("Allosaur corpse");
        super.setRotAge(ROT_AGE);
    }
}
