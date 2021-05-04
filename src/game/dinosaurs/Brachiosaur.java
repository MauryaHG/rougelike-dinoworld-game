package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.*;
import game.behaviours.*;
import game.actions.*;


/**
 * @author :Maurya
 * @version :1.1.0
 * A herbivorous dinosaur.
 */

public class Brachiosaur extends Dinosaur {

    protected static final int  BRACH_ADULT_AGE = 50;
    private int MIN_HUNGER = 140;


    public Brachiosaur(String name, Type gender) {
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
                //list.add(new Actions(new AttackAction(this)));        //Jinyeop - spec says player kills stegosaur only
            }
        }
        if (otherActor.hasCapability(Type.ALLOSAUR)){
            list.add(new Actions(new AttackAction(this)));
        }
        return list;
    }

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
