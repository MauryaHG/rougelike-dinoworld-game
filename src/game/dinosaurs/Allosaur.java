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
     * age of a adult allosaur
     */
    protected int ALLO_ADULT_AGE = 50;
    /**
     * minimum int where dinosaur will be hungry
     */
    private int MIN_HUNGER = 90;

    /**
     * adds adult dinosaur with specified gender
     * @param name name of dinosaur
     * @param gender gender of dinosaur
     */
    public Allosaur(String name, Type gender) {
        super(name, 'a', 100, gender);
        this.hitPoints = 50;
        addCapability(Type.ALLOSAUR);
        this.age = ALLO_ADULT_AGE;
    }

    /**
     * add baby Allosaur with a gender chosen randomly
     * @param name
     */
    public Allosaur(String name) {
        super(name, 'a', 100);
        this.hitPoints = 20;
        addCapability(Type.ALLOSAUR);
        addCapability(Type.BABY);
        this.age = 0;

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
        list.add(new Actions(new FeedAction(this)));
        return list;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
        @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
            if (this.isConscious()) {
                behaviour.clear();
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
