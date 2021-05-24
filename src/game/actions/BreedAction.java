package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Type;

/**
 * @author :Maurya
 * @version :1.0.1
 *
 */
public class BreedAction extends Action {
    /**
     * actor with type male
     */
    private Actor maleDinosaur;
    /**
     * actor with type female
     */
    private Actor femaleDinosaur;



    /**
     * take 2 actors and initialise them into male and female according to their gender
     * @param firstDinosaur first actor participating in the breeding
     * @param secondDinosaur second actor participating in the breeding
     */
    public BreedAction(Actor firstDinosaur, Actor secondDinosaur) {
        if((firstDinosaur.hasCapability(Type.MALE)) && (secondDinosaur.hasCapability(Type.FEMALE))){
            maleDinosaur = firstDinosaur;
            femaleDinosaur = secondDinosaur;
        }else if ((firstDinosaur.hasCapability(Type.FEMALE)) && (secondDinosaur.hasCapability(Type.MALE))){
            femaleDinosaur = firstDinosaur;
            maleDinosaur = secondDinosaur;
        }

    }

    /**
     * adds capability breeding to the female actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.hasCapability(Type.PTERODACTYLS)){
            if((map.locationOf(maleDinosaur).getGround().hasCapability(Type.TREE)) &&
                    map.locationOf(femaleDinosaur).getGround().hasCapability(Type.TREE)) {
                femaleDinosaur.addCapability(Type.PREGNANT);

            }else{
                return actor + "can not mate here";
            }
        } else{
            femaleDinosaur.addCapability(Type.PREGNANT);
        }

        return menuDescription(actor);
    }

    /**
     * returns actors who mated
     * @param actor The actor performing the action.
     * @return string the action done by the actors
     */
    @Override
    public String menuDescription(Actor actor) {
        return maleDinosaur + " mated with " +
                femaleDinosaur;
    }
}
