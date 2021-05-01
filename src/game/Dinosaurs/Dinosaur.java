package game.Dinosaurs;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Behaviours.Behaviour;
import game.Type;
import game.actions.BreedAction;
import game.items.StegosaurEgg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Maurya
 * @version :1.0.1
 */
abstract public class Dinosaur extends Actor {

    protected List<Behaviour> behaviour = new ArrayList<Behaviour>();
    protected int unconsciousCount = 0;
    protected int age;


    public Dinosaur(String name, char displayChar, int hitPoints, String gender) {
        super(name, displayChar, hitPoints);
        if (gender.equals("MALE")) {
            addCapability(Type.MALE);
        }
        if (gender.equals("FEMALE"))
            addCapability(Type.FEMALE);

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

    public void tick() {
        age++;

        if (isConscious()) {
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
