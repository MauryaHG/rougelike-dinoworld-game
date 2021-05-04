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
    private Actor maleDinosaur;
    private Actor femaleDinosaur;
    public static int breedLength;
    private int STEG_BREEDING_LENGTH = 10;
    private int BRACH_BREEDING_LENGTH = 30;


    public BreedAction(Actor firstDinosaur, Actor secondDinosaur) {
        if((firstDinosaur.hasCapability(Type.MALE)) && (secondDinosaur.hasCapability(Type.FEMALE))){
            maleDinosaur = firstDinosaur;
            femaleDinosaur = secondDinosaur;
        }else if ((firstDinosaur.hasCapability(Type.FEMALE)) && (secondDinosaur.hasCapability(Type.MALE))){
            femaleDinosaur = firstDinosaur;
            maleDinosaur = secondDinosaur;
        }

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        femaleDinosaur.addCapability(Type.PREGNANT);

        if(maleDinosaur.hasCapability(Type.STEGOSAUR)) {
            breedLength = STEG_BREEDING_LENGTH;
        }
        if(maleDinosaur.hasCapability(Type.BRACHIOSAUR)) {
            this.breedLength = BRACH_BREEDING_LENGTH;
        }

        if(actor.hasCapability(Type.PREGNANT)){

        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return maleDinosaur + " mated with " +
                femaleDinosaur;
    }
}
