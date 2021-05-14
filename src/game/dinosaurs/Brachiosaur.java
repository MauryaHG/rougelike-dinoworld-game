package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;
import game.behaviours.*;
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
    private int MIN_HUNGER = 140;

    /**
     * creates adult dinosaur with specified gender
     * @param name name of dinosaur
     * @param gender gender of dinosaur
     */
    public Brachiosaur(String name, Type gender) {
        super(name, 'b', 160, gender);
        this.hitPoints = 100;
        this.maxWaterLevel = 200;
        addCapability(Type.BRACHIOSAUR);
        this.age = BRACH_ADULT_AGE;
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
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

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action to be done this turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        behaviour.clear();
        if (this.isConscious()) {
            if (this.hitPoints >= MIN_HUNGER && !(this.hasCapability(Type.PREGNANT))) {
                behaviour.add(new BreedBehaviour());
            }
            if (isHungry(MIN_HUNGER, map)) {
                behaviour.add(new SeekFoodBehaviour());
            }
            behaviour.add(new WanderBehaviour());
            for (Behaviour index : behaviour) {
                Action action = index.getAction(this, map);
                if (action != null) {
                    tick(this, map);
                    return action;
                }
            }
        }
        tick(this, map);
        return new DoNothingAction();
    }
}
