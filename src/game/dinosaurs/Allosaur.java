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
 *
 */
public class Allosaur extends Dinosaur{

    protected int ALLO_ADULT_AGE = 50;
    private int MIN_HUNGER = 90;

    public Allosaur(String name, Type gender) {
        super(name, 'a', 100, gender);
        this.hitPoints = 50;
        addCapability(Type.ALLOSAUR);
        this.age = ALLO_ADULT_AGE;
    }

    public Allosaur(String name) {
        super(name, 'a', 100);
        this.hitPoints = 20;
        addCapability(Type.ALLOSAUR);
        addCapability(Type.BABY);
        this.age = 0;

    }
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions list = super.getAllowableActions(otherActor, direction, map);
        list.add(new Actions(new FeedAction(this)));
        return list;
    }

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

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        if(this.hasCapability(Type.BABY)) {
            return new IntrinsicWeapon(10, "attacks");
        }
        return new IntrinsicWeapon(20, "attacks");
    }

}
