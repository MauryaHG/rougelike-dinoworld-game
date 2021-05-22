package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Type;
import game.Util;
import game.dinosaurs.Dinosaur;
import game.items.Fish;

import java.util.ArrayList;

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
    private ArrayList<Fish> fishes = new ArrayList<>();
    private final int MAX_FISHES = 25;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Lake() {
        super('~');
        addCapability(Type.LAKE);
        // Initialise fishes with 5
        for(int i = 0; i < 5; i++)
            fishes.add(new Fish());
    }

    @Override
    public void tick(Location location) {
        incrementFish();
    }

//    /**
//     * Allow passable for Pterodactyl only
//     * @param actor the Actor to check
//     * @return A boolean if passable by the actor
//     */
//    @Override
//    public boolean canActorEnter(Actor actor) {
//        if( actor instanceof Pterodactyl)
//            return true;
//        return false;
//    }

    // To be modified after DrinkAciton is done
//    /**
//     * If actor is Dinosaur, allows DrinkAction
//     * @param actor the Actor acting
//     * @param location the current Location
//     * @param direction the direction of the Ground from the Actor
//     * @return
//     */
//    @Override
//    public Actions allowableActions(Actor actor, Location location, String direction) {
//        if( actor instanceof Dinosaur)
//            return new Actions(new DrinkAction());
//        return new Actions();
//    }
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
     * Returns the number of fishes in the lake
     * @return
     */
    public int getNumOfFishes() {
        return fishes.size();
    }

    /**
     * Checks validity of fish limits and increment(add) by 1 if valid
     */
    public void incrementFish() {
        // if number of fishes in the lake is more than the limit, then terminate
        if( getNumOfFishes() > MAX_FISHES )
            return;

        // If enough room for new Fish, calc 60% and then create new one
        if(Util.calcPercentage(Util.SIXTY_PERCENT_CHANCE))
            fishes.add(new Fish());
    }

    public int getSips() {
        return sips;
    }
}
