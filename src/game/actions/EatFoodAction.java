package game.actions;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.dinosaurs.Dinosaur;
import game.items.DinosaurCorpse;
import game.items.PortableItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author :Maurya
 * @version :2.0.0
 * @see : Action
 * @see : Fruit
 * @see : Actor
 * @see : GameMap
 */
public class EatFoodAction extends Action {


    /**
     * item which will be eaten by dinosaur
     */
    protected List <PortableItem> items = new ArrayList<>();
    private String  itemsEaten = "";
    private int waterLevel =0;

    /**
     * searches the item list and adds all the
     * @param actor actor which eats the food
     * @param itemsHere items on ground actor is standing on
     */
    public EatFoodAction(Actor actor, List<Item> itemsHere) {
        if (actor.hasCapability(Type.CARNIVORE)) {
            for (Item indexItem : itemsHere) {
                if (indexItem.hasCapability(Type.EGG) ||
                        (indexItem.hasCapability(Type.CORPSE)) ||
                        (indexItem.hasCapability(Type.FISH))) {
                    this.items.add((PortableItem) indexItem);
                }
            }
        }
        if (actor.hasCapability(Type.HERBIVORE)) {
            for (Item indexItem : itemsHere) {
                if (indexItem.hasCapability(Type.FRUIT)) {
                    this.items.add((PortableItem) indexItem);
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
        int amount = 0;

        if (actor.hasCapability(Type.STEGOSAUR)) {
            heal = 10;
            amount = 1;
        }
        if (actor.hasCapability(Type.BRACHIOSAUR)) {
            heal = 10;
            amount = items.size();
        }

        if (actor.hasCapability(Type.PTERODACTYLS)) {
            heal = 10;
            amount = new Random().nextInt((2 - 0) + 1);;//make random value 0-1-2 _ Jinyeop modified
            if(amount > items.size()){
                amount = items.size();
            }
        }
        if (actor.hasCapability(Type.ALLOSAUR)) {
            amount = 1;
        }
        for (int i = 0; i < amount; i++) {
            Item item = items.get(i);
            if ((item.hasCapability(Type.ALLOSAUR_CORPSE)) || (item.hasCapability(Type.STEGOSAUR_CORPSE))) {
                heal = 50;
            } else if (item.hasCapability(Type.BRACHIOSAUR_CORPSE)) {
                heal = 100;
            } else if (item.hasCapability(Type.EGG)) {
                heal = 10;
            } else if (item.hasCapability(Type.FISH)) {
                heal = 5;
                waterLevel = 30;
            }
            if (actor.hasCapability(Type.PTERODACTYLS)) {
                heal = 10;
            }
            actor.heal(heal);
            if(item.hasCapability(Type.CORPSE)) {
                ((DinosaurCorpse) item).reduceFoodPoints(heal);
                if (((DinosaurCorpse) item).getFoodPoints() <= 0) {
                    here.removeItem(item);
                }
            } else {
                here.removeItem(item);
            }

            itemsEaten += " ," + item;
        }
        if (actor.hasCapability(Type.PTERODACTYLS)) {
            ((Dinosaur) actor).increaseWater(waterLevel);
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
        return actor + " eats" + itemsEaten;
    }
}
