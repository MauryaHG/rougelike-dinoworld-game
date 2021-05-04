package game.dinosaurs;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.behaviours.Behaviour;
import game.Type;
import game.Util;
import game.items.*;


import java.util.ArrayList;
import java.util.List;

import static game.actions.BreedAction.breedLength;

/**
 * @author :Maurya
 * @version :1.1.0
 */
abstract public class Dinosaur extends Actor {

    protected List<Behaviour> behaviour = new ArrayList<Behaviour>();
    protected int unconsciousCount = 0;
    protected int age;
    protected int breedingCount;


    public Dinosaur(String name, char displayChar, int hitPoints, Type gender) {
        super(name, displayChar, hitPoints);
        if (gender == Type.MALE) {
            addCapability(Type.MALE);
        }
        if (gender == Type.FEMALE) {
            addCapability(Type.FEMALE);
        }
        this.breedingCount = 0;

    }

    public Dinosaur(String name, char displayChar, int hitPoints ) {
        super(name, displayChar, hitPoints);
        Type gender = Util.getGender();
        if (gender == Type.MALE) {
            addCapability(Type.MALE);
        }
        if (gender == Type.FEMALE) {
            addCapability(Type.FEMALE);
        }
        this.breedingCount = 0;

    }

    public boolean isHungry(int minHunger, GameMap map) {
        boolean isHungry = false;
        if (hitPoints < minHunger) {
            Location here = map.locationOf(this);
            System.out.println(this.name + " at (" + here.x() + "," + here.y() + ") is getting hungry!!!");
            isHungry = true;
        }
        return isHungry;
    }

    public void tick(Actor actor, GameMap map) {
        age++;

        if (actor.hasCapability(Type.PREGNANT)) {
            if(breedingCount == breedLength){
                Location here = map.locationOf(actor);

                if(actor.hasCapability(Type.STEGOSAUR)){
               here.addItem(new StegosaurEgg("DaBaby"));
                }

                if(actor.hasCapability(Type.BRACHIOSAUR)){
                    here.addItem(new BrachiosaurEgg("DaBaby_2"));
                }

                if(actor.hasCapability(Type.ALLOSAUR)){
                    here.addItem(new AllosaurEgg("DaBaby_3"));
                }

                actor.removeCapability(Type.PREGNANT);
                breedingCount = 0;
                System.out.println(actor + " laid egg on (" + here.x() + "," + here.y() + ")");
            }
            breedingCount++;
        }
        if (actor.isConscious()) {
            hitPoints--;
        }

        if (!isConscious()) {
            DinosaurCorpse corpse = null;
            addCapability(Type.UNCONSCIOUS);
            int maxUnconsciouis = 0;
            if (actor.hasCapability(Type.STEGOSAUR)){
                corpse = new StegosaurCorpse();
                maxUnconsciouis = 20;
            }
            if (actor.hasCapability(Type.BRACHIOSAUR)){
                corpse = new BrachiosaurCorpse();
                maxUnconsciouis = 15;
            }
            if (actor.hasCapability(Type.ALLOSAUR)){
                corpse = new AllosaurCorpse();
                maxUnconsciouis = 25;
            }
            if (unconsciousCount == maxUnconsciouis) {

                map.locationOf(actor).addItem(corpse);
                map.removeActor(actor);
            }
            unconsciousCount += 1;
        }
    }
}
