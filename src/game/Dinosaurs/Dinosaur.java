package game.Dinosaurs;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Maurya
 * @version :1.0.1
 */
abstract public class Dinosaur extends Actor {

    protected List<Behaviour> behaviour = new ArrayList<Behaviour>();
    protected int unconsciousCount = 0;

    public Dinosaur(String name, char displayChar, int hitPoints, String gender){
        super(name, displayChar, hitPoints);
        if (gender.equals("MALE")){
            addCapability(Type.MALE);
        }
        if (gender.equals("FEMALE"))
            addCapability(Type.FEMALE);

    }

    public boolean isHungry(GameMap map){
        return hitPoints <= 50;
    }

    public void tick(){
        if (isConscious()) {
            hitPoints -= 1;
        }

        if (!isConscious()){
            addCapability(Type.UNCONSCIOUS);
            unconsciousCount += 1;
            if(unconsciousCount == 20){
                addCapability(Type.DEAD);
            }

        }
    }
}
