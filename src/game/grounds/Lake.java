package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Type;
import game.Util;
import game.actions.DrinkAction;
import game.dinosaurs.Dinosaur;
import game.items.Fish;

/**
 *
 * @author jinyeopoh
 * @version 1.0.0
 */
public class Lake extends Ground {
    private int sips = 25;
    /**
     * Fishes that this lake has
     */
    private final int MAX_FISH = 25;

    /**
     * Each lake starts with 5 fish
     */
    private int numOfFish = 5;

    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        addCapability(Type.LAKE);
    }

    @Override
    public void tick(Location location) {
        incrementFish(location);
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
        if( actor instanceof Dinosaur){
            actions.add(new Actions(new DrinkAction(location)));
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
        if(actor.hasCapability(Type.PTERODACTYLS) &&
                actor.hasCapability(Type.CAN_FLY)){
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
        this.sips -= 1;
    }


    /**
     * Returns the number of fish in the lake
     * @return
     */
    public int getNumOfFish() {
        return numOfFish;
    }

    /**
     * Checks validity of fish limits and increment(add) by 1 if valid
     */
    public void incrementFish(Location location) {
        // Pre : if number of fishes in the lake is more than the limit, then terminate
        if( getNumOfFish() >= MAX_FISH)
            return;

        // If enough room for new Fish, calc 60% and then create new one
        if(Util.calcPercentage(Util.SIXTY_PERCENT_CHANCE)){
            location.addItem(new Fish());
            numOfFish++;
        }

    }

    public int getSips() {
        return sips;
    }
}
