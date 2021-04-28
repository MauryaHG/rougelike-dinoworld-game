package game.Dinosaurs;

import edu.monash.fit2099.engine.Actor;
import game.Type;

/**
 * @author :Maurya
 * @version :1.0.0
 */
abstract public class Dinosaur extends Actor {


    public Dinosaur(String name, char displayChar, int hitPoints, String gender){
        super(name, displayChar, hitPoints);
        if (gender.equals("M")){
            addCapability(Type.MALE);
        }
        if (gender.equals("F"))
            addCapability(Type.FEMALE);

    }

    public boolean isHungry(){
        return hitPoints<=50;
    }

    public void tick(){
        hitPoints -= 1;
    }
}
