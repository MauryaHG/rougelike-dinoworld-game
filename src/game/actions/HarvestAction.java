package game.actions;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
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

    /**
     * Player has 50% chacne to harvest fruit on the tree or bush
     * When harvested, Fruit will be removed from the map and stored in the inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A String whether player succeeded to harvest or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
            map.locationOf(actor).removeItem(fruit);
            fruit.setOnGround();
            fruit.resetAge();
            actor.addItemToInventory(fruit);
            return menuDescription(actor);
        }
        return actor + " fails to harvest from the tree or bush." ;
    }

    /**
     * Show menu description depending on the location
     * @param actor The actor performing the action.
     * @return A String representing the action that the player would perform
     */
    @Override
    public String menuDescription(Actor actor) {
        if(fruit.isOnTree()){
            return actor + " harvests fruit on the tree." ;
        }

        return actor + " harvests fruit from the bush." ;

    }


    @Override
    public String hotkey() {
        return "h";
    }
}
