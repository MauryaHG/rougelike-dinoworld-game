package game.actions;

import edu.monash.fit2099.engine.*;
import game.Type;

import java.util.List;

/**
 * @author :Maurya
 * @version :1.0.0
 * @see : Action
 * @see : Fruit
 * @see : Actor
 * @see : GameMap
 */
public class EatFoodAction extends Action {

    /**
     * item which will be eaten by dinosaur
     */
    protected  Item item;
    /**
     * type of food that species can eat
     */
    protected Type foodSource;

    /**
     * searches the item list and adds all the
     * @param actor actor which eats the food
     * @param itemsHere items on ground actor is standing on
     */
    public EatFoodAction(Actor actor, List<Item> itemsHere) {
        if (actor.hasCapability(Type.ALLOSAUR)) {
            for (Item indexItem : itemsHere) {
                if (indexItem.hasCapability(Type.EGG) || (indexItem.hasCapability(Type.CORPSE))) {
                    this.item = indexItem;
                }
            }
        }
        if (actor.hasCapability(Type.STEGOSAUR) || (actor.hasCapability(Type.BRACHIOSAUR))) {
            for (Item indexItem : itemsHere) {
                if (indexItem.hasCapability(Type.FRUIT)) {
                    this.item = indexItem;
                }
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
        int heal = 0;
        here.removeItem(item);
        if(actor.hasCapability(Type.STEGOSAUR)) {
            actor.heal(10);
        }
        if(actor.hasCapability(Type.BRACHIOSAUR)) {
            actor.heal(5);
        }
        if(actor.hasCapability(Type.ALLOSAUR)) {
            if((item.hasCapability(Type.ALLOSAUR)) || (item.hasCapability(Type.STEGOSAUR))){
                heal = 50;
            } else if(item.hasCapability(Type.BRACHIOSAUR)){
                heal = 100;
            }else if (item.hasCapability(Type.EGG)){
                heal = 10;
            }
            actor.heal(heal);
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
