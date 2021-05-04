package game.items;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see DinosaurCorp
 */
public class AllosaurCorpse extends DinosaurCorp{

    private final int ROT_AGE = 20;


    public AllosaurCorpse() {
        super("Allosaur corpse");
        super.setRotAge(ROT_AGE);
    }
}
