package game.Dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.actions.AttackAction;
import game.actions.FeedAction;

/**
 * @author :Maurya
 * @version :1.0.0
 */

public class Brachiosaur extends Dinosaur {

    public Brachiosaur(String name, String gender) {
        super(name, 'B', 160, gender);
        this.hitPoints = 100;
        addCapability(Type.BRACHIOSAUR);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(Type.BRACHIOSAUR)) {
            if (((otherActor.hasCapability(Type.MALE)) && (this.hasCapability(Type.FEMALE))) ||
                    ((this.hasCapability(Type.MALE)) && (otherActor.hasCapability(Type.FEMALE)))){
              //  return new Actions(new BreedBehaviour)
            }
        }
        if (!this.isConscious()){
            return new Actions(new FeedAction(this));
        }
        if (otherActor.hasCapability(Type.PLAYER)){
            return new Actions(new FeedAction(this));
        }
        return new Actions(new AttackAction(this));
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public boolean isHungry(){
        return hitPoints <= 140;
    }
}
