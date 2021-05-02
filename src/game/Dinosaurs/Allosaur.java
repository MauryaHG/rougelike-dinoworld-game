package game.Dinosaurs;

import edu.monash.fit2099.engine.*;

public class Allosaur extends Dinosaur{
    public Allosaur(String name, char displayChar, int hitPoints, String gender) {
        super(name, displayChar, hitPoints, gender);
    }

    @Override
    public boolean isHungry(int minHunger, GameMap map) {
        return super.isHungry(minHunger, map);
    }

    @Override
    public void tick(Actor actor, GameMap map) {
        super.tick(actor, map);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
