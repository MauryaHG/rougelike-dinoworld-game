package game.Dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Behaviours.Behaviour;
import game.Behaviours.BreedBehaviour;
import game.Behaviours.SeekFoodBehaviour;
import game.Behaviours.WanderBehaviour;
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
    private int MIN_HUNGER = 50;

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
            behaviour.clear();
            if(this.hitPoints >= MIN_HUNGER && !(this.hasCapability(Type.PREGNANT))) {
                behaviour.add(new BreedBehaviour());
            }
            if (isHungry(MIN_HUNGER, map)) {
                behaviour.add(new SeekFoodBehaviour());
            }
            behaviour.add(new WanderBehaviour());
            for (Behaviour index : behaviour) {
                Action action = index.getAction(this, map);
                if (action != null){
                    tick(this, map);
                    return action;
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
