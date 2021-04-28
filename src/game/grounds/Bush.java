package game.grounds;

import edu.monash.fit2099.engine.Ground;
import game.Type;

public class Bush extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Bush(char displayChar) {
        super(displayChar);
        addCapability(Type.BUSH);
    }
}
