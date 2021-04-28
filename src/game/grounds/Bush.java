package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.HarvestAction;

/**
 * @author Jinyeop Oh
 * @version 1.0.0
 * @see Fruit
 * @see HarvestAction
 */
public class Bush extends Ground {
    private Fruit fruit;
    private boolean hasFruit;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Bush() {
        super(';');
        addCapability(Type.BUSH);
        hasFruit = false;
    }

    /**
     * Produces a fruit by 5% chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if( !hasFruit ){
            if(Util.calcPercentage(Util.FIVE_PERCENT_CHANCE)){
                fruit = new Fruit();
                hasFruit = true;
            }
        }
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new HarvestAction());
    }
}
