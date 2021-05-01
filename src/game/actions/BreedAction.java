package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Dinosaurs.Dinosaur;
import game.Type;
import game.items.StegosaurEgg;

/**
 * @author :Maurya
 * @version :1.0.0
 *
 */
public class BreedAction extends Action {
    private Actor maleDinosaur;
    private Actor femaleDinosaur;
    public int breedLength;
    private int breedingCount = 0;
    private int STEG_BREEDING_LENGTH = 10;
    private int BRACH_BREEDING_LENGTH = 30;


    public BreedAction(Actor firstDinosaur, Actor secondDinosaur) {
        if((firstDinosaur.hasCapability(Type.MALE)) && (secondDinosaur.hasCapability(Type.FEMALE))){
            this.maleDinosaur = firstDinosaur;
            this.femaleDinosaur = secondDinosaur;
        }else if ((firstDinosaur.hasCapability(Type.FEMALE)) && (secondDinosaur.hasCapability(Type.MALE))){
            this.femaleDinosaur = firstDinosaur;
            this.maleDinosaur = secondDinosaur;
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
            if(breedingCount == breedLength){
                map.locationOf(femaleDinosaur).addItem(new StegosaurEgg("DaBaby"));
                maleDinosaur.removeCapability(Type.BREEDING);
                femaleDinosaur.removeCapability(Type.BREEDING);
            }
            breedingCount++;
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
