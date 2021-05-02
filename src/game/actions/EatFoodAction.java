package game.actions;

import edu.monash.fit2099.engine.*;
import game.Type;

import java.util.List;

public class EatFoodAction extends Action {


    protected Item item;
    protected Type foodSource;

    public EatFoodAction(Actor actor, List<Item> itemsHere) {
        if(actor.hasCapability(Type.ALLOSAUR)){
            foodSource = Type.EGG;
        }
        if(actor.hasCapability(Type.STEGOSAUR) || (actor.hasCapability(Type.BRACHIOSAUR))){
            foodSource = Type.FRUIT;
        }
        for (Item indexItem : itemsHere) {
            if (indexItem.hasCapability(foodSource)){
                this.item = indexItem;
            }
        }
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        here.removeItem(item);
        if(actor.hasCapability(Type.STEGOSAUR)) {
            actor.heal(10);
        }
        if(actor.hasCapability(Type.BRACHIOSAUR)) {
            actor.heal(5);
        }
        if(actor.hasCapability(Type.ALLOSAUR)) {
            actor.heal(10);
        }
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + item;
    }
}
