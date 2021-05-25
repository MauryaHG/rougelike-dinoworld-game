package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.DrinkAction;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.items.Fish;

/**
 *
 * @author jinyeopoh
 * @version 1.1.1
 * @see Ground
 * @see Fish
 */
public class Lake extends Ground {
    /**
     * Represents water level
     */
    private int sips = 25;

    /**
     * Fishes that this lake has
     */
    private final int MAX_FISH = 25;

    public Lake() {
        super('~');
        addCapability(Type.LAKE);
    }

    /**
     * Check for the water level to decide whether remove all fish or not.
     * In every turn, calculate the number of fish in the lake and if under the limit,
     * create new fish by 60% chance
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // Pre : check for the water level. If water level == 0, remove all fish in the lake
        if ( sips <= 0){
            removeAllFish(location);
            return;
        }

        // Get number of fish in the lake
        int numOfFish = 0;
        for(Item item: location.getItems()){
            if(item instanceof Fish){
                numOfFish++;
            }
        }

        // Pre : if number of fishes in the lake is more than the limit, then terminate
        if( numOfFish >= MAX_FISH){
            return;
        }



        // If enough room for new Fish, calc 60% and then create new one
        if(Util.calcPercentage(Util.SIXTY_PERCENT_CHANCE)){
            location.addItem(new Fish());
            numOfFish++;
        }
    }

    /**
     * If actor is Dinosaur, allows DrinkAction
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if( actor instanceof Stegosaur || actor instanceof Brachiosaur || actor instanceof Allosaur){
            actions.add(new DrinkAction(location));
        }
        return actions;
    }
    /**
     * Allow passable for Pterodactyl only
     * @param actor the Actor to check
     * @return A boolean if passable by the actor
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Type.PTERODACTYLS) &&  actor.hasCapability(Type.CAN_FLY)){
            return true;
        }
        return false;
    }
    /**
     * Increases the sips after raining
     * @param sips An int to be added onto sips
     */
    public void incrementSips(int sips){
        this.sips += sips;
    }

    /**
     * Decreases the sips after actor drinks
     */
    public void decrementSips(){
        this.sips = Math.max(0, this.sips-1);

    }

    /**
     * Removes all fish in the lake
     * @param location location of lake
     */
    private void removeAllFish(Location location){
        for(Item item: location.getItems()){
            if(item instanceof Fish){
                location.removeItem(item);
            }
        }
    }

    public int getSips() {
        return sips;
    }
}
