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
 * @see: Actor
 * @see: Stegosaur
 * @see: Brachiosaur
 * @see: Allosaur
 * Parent class of dinosaur
 */
abstract public class Dinosaur extends Actor {
    /**
     * list of behaviours
     */
    protected List<Behaviour> behaviour = new ArrayList<Behaviour>();
    /**
     * number of turns the dinosaur has been unconciouis
     */
    protected int unconsciousCount = 0;
    /**
     * age of dinosaur (increase by one every turn)
     */
    protected int age;
    /**
     * number of turns female dinosaur has been pregnant
     */
    protected int breedingCount;

    /**
     * Create dinosaur with specified gender
     * @param name name of dinosaur
     * @param displayChar display character which will be shown on map
     * @param hitPoints health of dinosaur
     * @param gender gender of dinosaur
     */
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

    /**
     * createa dinosaur with random gender
     * @param name name of dinosaur
     * @param displayChar display character which will be shown on map
     * @param hitPoints health of dinosaur
     */
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

    /**
     * check if dinosaur is hungry
     * @param minHunger min hunger of dinosaur species
     * @param map game map actor is located
     * @return boolean
     */
    public boolean isHungry(int minHunger, GameMap map) {
        boolean isHungry = false;
        if (hitPoints < minHunger) {
            Location here = map.locationOf(this);
            System.out.println(this.name + " at (" + here.x() + "," + here.y() + ") is getting hungry!!!");
            isHungry = true;
        }
        return isHungry;
    }

    /**
     * ticks every turn and changes relevant attributes of dinosaur
     * @param actor
     * @param map
     */
    public void tick(Actor actor, GameMap map) {
        age++;


        if (actor.isConscious()) {
            hitPoints--;
            if (actor.hasCapability(Type.PREGNANT)) {
                if(breedingCount == breedLength){
                    Location here = map.locationOf(actor);

                    if(actor.hasCapability(Type.STEGOSAUR)){
                        here.addItem(new StegosaurEgg());
                    }

                    if(actor.hasCapability(Type.BRACHIOSAUR)){
                        here.addItem(new BrachiosaurEgg());
                    }

                    if(actor.hasCapability(Type.ALLOSAUR)){
                        here.addItem(new AllosaurEgg());
                    }

                    actor.removeCapability(Type.PREGNANT);
                    breedingCount = 0;
                    System.out.println(actor + " laid egg on (" + here.x() + "," + here.y() + ")");
                }
                breedingCount++;
            }
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
