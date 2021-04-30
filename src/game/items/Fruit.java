package game.items;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.Type;
import game.Util;
import game.actions.FeedAction;
import game.actions.HarvestAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jinyeop Oh
 * @version 1.1.1
 * @see Util
 */
public class Fruit extends PortableItem {
    /**
     * An age of this fruit. It is incremented every turn
     */
    private int age = 0;
    //private boolean isInInventory = false;



    public Fruit() {
        super("Fruit", 'F');
        addCapability(Type.FRUIT);
    }

    /**
     * if this Fruit is older than 15 turns, gets removed from the current location
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        age++;

        if(age > 15){
            currentLocation.map().at(currentLocation.x(), currentLocation.y()).removeItem(this);
        }
    }



}
