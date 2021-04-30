package game.Dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;
import game.Behaviours.*;
import game.actions.*;


import java.util.List;

/**
 * @author :Maurya
 * @version :1.0.0
 */

public class Brachiosaur extends Dinosaur {

    private static final int  BRACH_ADULT_AGE = 50;
    private int MIN_HUNGER = 140;


    public Brachiosaur(String name, String gender) {
        super(name, 'b', 160, gender);
        this.hitPoints = 100;
        addCapability(Type.BRACHIOSAUR);
        this.age = BRACH_ADULT_AGE;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions list = super.getAllowableActions(otherActor, direction, map);

        if (!this.isConscious()){
            list.add(new Actions(new FeedAction(this)));
        }
        if (this.isConscious()){
            if (otherActor.hasCapability(Type.PLAYER)){
                list.add(new Actions(new FeedAction(this)));
                list.add(new Actions(new AttackAction(this)));
            }
        }
        return list;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        behaviour.clear();
        List<Action> a = actions.getUnmodifiableActionList();
        if(this.hitPoints>=70) {
            behaviour.add(new BreedBehaviour());
        }
        if (isHungry(MIN_HUNGER, map)) {
            behaviour.add(new SeekFoodBehaviour());
        }
        for (Behaviour index : behaviour) {
            Action action = index.getAction(this, map);
            if (action != null){
                tick();
            return action;
            }
        }
        tick();
        return new DoNothingAction();
    }
}
