package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;
import game.actions.*;


/**
 * @author :Maurya
 * @version :1.1.1
 * A herbivorous dinosaur.
 */

public class Brachiosaur extends Dinosaur {

    /**
     * minimum int where dinosaur will be hungry
     */
    private int MIN_HUNGER = 20;

    /**
     * creates adult dinosaur with specified gender
     * @param name name of dinosaur
     * @param gender gender of dinosaur
     */
    public Brachiosaur(String name, Type gender) {
        super(name, 'b', 160, 200,  gender);
        this.hitPoints = 100;
        this.maxWaterLevel = 200;
        addCapability(Type.BRACHIOSAUR);
        addCapability(Type.HERBIVORE);
        this.age = BRACH_ADULT_AGE;
    }

    @Override
    public int getMIN_HUNGER() {
        return MIN_HUNGER;
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that can be done to actor
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions list = super.getAllowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Type.PLAYER)) {
            list.add(new Actions(new FeedAction(this)));
        }
        // Jinyeop - Brachiosaur does not get attacted by the player nor allosaur
        return list;
    }


}
