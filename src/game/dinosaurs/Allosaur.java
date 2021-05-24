package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.behaviours.Behaviour;
import game.behaviours.BreedBehaviour;
import game.behaviours.SeekFoodBehaviour;
import game.behaviours.WanderBehaviour;
import game.Type;
import game.actions.FeedAction;
/**
 * @author :Maurya
 * @version :1.1.0
 * A carnivorous dinosaur.
 */
public class Allosaur extends Dinosaur{
    /**
     * minimum int where dinosaur will be hungry
     */
    private int MIN_HUNGER = 90;

    /**
     * creates adult dinosaur with specified gender
     * @param name name of dinosaur
     * @param gender gender of dinosaur
     */
    public Allosaur(String name, Type gender) {
        super(name, 'a', 100, gender);
        this.hitPoints = 50;
        addCapability(Type.ALLOSAUR);
        addCapability(Type.CARNIVORE);
        this.age = ALLO_ADULT_AGE;
    }

    /**
     * add baby Allosaur with a gender chosen randomly
     * @param name name of allosaur
     */
    public Allosaur(String name) {
        super(name, 'a', 100);
        this.hitPoints = 20;
        addCapability(Type.ALLOSAUR);
        addCapability(Type.CARNIVORE);
        addCapability(Type.BABY);
        this.age = 0;

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
        list.add(new Actions(new FeedAction(this)));
        return list;
    }

    @Override
    public int getMIN_HUNGER() {
        return MIN_HUNGER;
    }

    /**
     * returns weapon used according to actor age
     * @return weapon of actor
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        if(this.hasCapability(Type.BABY)) {
            return new IntrinsicWeapon(10, "attacks");
        }
        return new IntrinsicWeapon(20, "attacks");
    }

}
