package game.Dinosaurs;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Behaviours.Behaviour;
import game.Type;
import game.Util;
import game.items.AllosaurEgg;
import game.items.BrachiosaurEgg;
import game.items.StegosaurEgg;


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


    public Dinosaur(String name, char displayChar, int hitPoints, String gender) {
        super(name, displayChar, hitPoints);
        if (gender.equals("MALE")) {
            addCapability(Type.MALE);
        }
        if (gender.equals("FEMALE")) {
            addCapability(Type.FEMALE);
        }
        this.breedingCount = 0;

    }

    public Dinosaur(String name, char displayChar, int hitPoints ) {
        super(name, displayChar, hitPoints);
        String gender = Util.getGender();
        if (gender.equals("MALE")) {
            addCapability(Type.MALE);
        }
        if (gender.equals("FEMALE")) {
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
            addCapability(Type.UNCONSCIOUS);
            unconsciousCount += 1;
            if (unconsciousCount == 20) {
                addCapability(Type.DEAD);
            }

        }
    }
}
