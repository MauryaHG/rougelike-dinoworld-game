package game.Dinosaurs;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class Allosaur extends Dinosaur{
    public Allosaur(String name, char displayChar, int hitPoints, String gender) {
        super(name, displayChar, hitPoints, gender);
    }

    @Override
    public boolean isHungry(int minHunger, GameMap map) {
        return super.isHungry(minHunger, map);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
