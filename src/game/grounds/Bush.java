package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.HarvestAction;
import game.dinosaurs.Brachiosaur;
import game.items.Fruit;

/**
 * @author Jinyeop Oh
 * @version 1.2.0
 * @see Ground
 * @see Fruit
 */
public class Bush extends Ground {
    private boolean hasFruit = false;
    /**
     * Constructor.
     * Initialise the character and the capability
     */
    public Bush() {
        super('*');
        addCapability(Type.BUSH);
    }

    @Override
    public void tick(Location location) {
        // If this bush has no fruit, produce fruit by 1% chance
        if( !hasFruit ){
            if( Util.calcPercentage(Util.ONE_PERCENT_CHANCE)){
                Fruit fruit = new Fruit();
                fruit.setOnBush(true);
                location.addItem(fruit);
                hasFruit = true;
            }
        }

        // Get reference to the actor on the this location
        Actor actor = location.getActor();

        // Pre : If a actor on the same location is not a Brachiosaur, terminate
        if( !(actor instanceof Brachiosaur) ){
            return;
        }


        // Brachiosaur has 50% chance to kill bush and all the fruits
        if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
            location.setGround(new Dirt());

            for(Item item: location.getItems()){
                if(item instanceof Fruit){
                    location.removeItem(item);
                }
            }
        }
    }

    /**
     * Sets hasFruit = false.
     * Called in HarvestAction
     */
    public void setHasNoFruit() {
        this.hasFruit = false;
    }
}
