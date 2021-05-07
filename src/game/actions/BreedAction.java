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
     * breeding length of particular actor
     */
    public static int breedLength;
    /**
     * constant integer of breeding length of Stegosaur
     */
    private int STEG_BREEDING_LENGTH = 10;
    /**
     * constant integer of breeding length of Brachiosaur
     */
    private int BRACH_BREEDING_LENGTH = 30;

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
        femaleDinosaur.addCapability(Type.PREGNANT);

        if(maleDinosaur.hasCapability(Type.STEGOSAUR)) {
            breedLength = STEG_BREEDING_LENGTH;
        }
        if(maleDinosaur.hasCapability(Type.BRACHIOSAUR)) {
            this.breedLength = BRACH_BREEDING_LENGTH;
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
