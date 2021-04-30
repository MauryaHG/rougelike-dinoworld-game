package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.Type;
import game.grounds.Bush;
import game.grounds.Tree;
import game.items.Fruit;

/**
 * @author Jinyeop Oh
 * @version 1.0.0
 * @see Ground the target subclass of Ground are Bush and Tree
 * @see Bush
 * @see Tree
 */
public class HarvestAction extends Action {
    /**
     * The target Ground subclass to be harvested
     */
    private Fruit fruit;

    public HarvestAction(Fruit fruit){
        this.fruit = fruit;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
       // loop through all fruit and find onTreeFruit and give 60% chance to pick up
        return "HarvestAction Test on going";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


    @Override
    public String hotkey() {
        return "H";
    }
}
