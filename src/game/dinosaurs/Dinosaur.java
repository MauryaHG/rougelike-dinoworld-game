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

/**
 * @author :Maurya
 * @version :1.3.0
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
     * water level of dinosaur
     */
    protected int waterLevel;

    /**
     * max water level of dinosaur
     */
    protected int maxWaterLevel;

    /**
     * age of a adult allosaur
     */
    protected int ALLO_ADULT_AGE = 50;

    /**
     * age of a adult Stegosaur
     */
    protected int STEG_ADULT_AGE = 50;

    /**
     * age of a adult Brachiosaur
     */
    protected int BRACH_ADULT_AGE = 50;

    /**
     * age of a adult Pterodactyl
     */
    protected int PET_ADULT_AGE = 50;

    /**
     * constant integer of breeding length of Stegosaur
     */
    private final int STEG_BREEDING_LENGTH = 10;

    /**
     * constant integer of breeding length of Brachiosaur
     */
    private final int BRACH_BREEDING_LENGTH = 30;

    /**
     * constant integer of breeding length of Allosaur
     */
    private final int ALLO_BREEDING_LENGTH = 50;

    /**
     * constant integer starting water level
     */
    private final int START_WATER_LVL = 60;

    /**
     * constant integer maximum water level
     */
    private final int MAX_WATER_LVL = 100;

    /**
     * number of turns of unconciousness actor can live after dehydrated
     */
    private final int DEATH_BY_DEHYDRATION = 15;

    /**
     * Create dinosaur with specified gender
     * @param name name of dinosaur
     * @param displayChar display character which will be shown on map
     * @param hitPoints health of dinosaur
     * @param gender gender of dinosaur
     */
    public Dinosaur(String name, char displayChar, int hitPoints, Type gender) {
        super(name, displayChar, hitPoints);
        this.waterLevel = START_WATER_LVL;
        this.maxWaterLevel = MAX_WATER_LVL;
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


    public boolean isHydrated() {
        return waterLevel > 0;
    }

    /**
     * ticks every turn and changes relevant attributes of dinosaur
     * @param actor
     * @param map
     */
    public void tick(Actor actor, GameMap map) {

        DinosaurCorpse corpse = null;
        int maxUnconscious = 0;
        int adultAge  = 0;
        Egg eggItem = null;
        Location here = map.locationOf(actor);
        int breedLength = 0;

        if(actor.hasCapability(Type.STEGOSAUR)){
            eggItem = new StegosaurEgg();
            corpse = new StegosaurCorpse();
            maxUnconscious = 20;
            adultAge = STEG_ADULT_AGE;
            breedLength = STEG_BREEDING_LENGTH;
        }

        if(actor.hasCapability(Type.BRACHIOSAUR)){
            eggItem = new BrachiosaurEgg();
            corpse = new BrachiosaurCorpse();
            maxUnconscious = 15;
            adultAge = BRACH_ADULT_AGE;
            breedLength = BRACH_BREEDING_LENGTH;
        }

        if(actor.hasCapability(Type.ALLOSAUR)){
            eggItem = new AllosaurEgg();
            corpse = new AllosaurCorpse();
            maxUnconscious = 25;
            adultAge = ALLO_ADULT_AGE;
            breedLength = ALLO_BREEDING_LENGTH;
        }

        if (actor.isConscious() && this.isHydrated()) {
            if(actor.hasCapability(Type.BABY)){
                if ((age >= adultAge)){
                    removeCapability(Type.BABY);
                }
            }
            if (actor.hasCapability(Type.PREGNANT)) {
                if(breedingCount == breedLength){
                    actor.removeCapability(Type.PREGNANT);
                    here.addItem(eggItem);
                    breedingCount = 0;
                    System.out.println(actor + " laid egg on (" + here.x() + "," + here.y() + ")");
                }
                breedingCount++;
            }
            hitPoints--;
            waterLevel--;
        }

        if (!isConscious()) {
            addCapability(Type.UNCONSCIOUS);
            if (unconsciousCount == maxUnconscious) {
                map.locationOf(actor).addItem(corpse);
                map.removeActor(actor);
            }
            unconsciousCount += 1;
        }

         if (!isHydrated()){
             addCapability(Type.UNCONSCIOUS);
             if (unconsciousCount == DEATH_BY_DEHYDRATION) {
                 map.locationOf(actor).addItem(corpse);
                 map.removeActor(actor);
             }
             unconsciousCount += 1;
         }
        age++;
    }
}
