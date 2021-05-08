package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.Type;
import game.actions.PurchaseAction;
import game.items.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jinyeop Oh
 * @version 1.2.1
 * @see Fruit
 * @see CarnivoreMeal
 * @see VegetarianMeal
 * @see StegosaurEgg
 * @see BrachiosaurEgg
 * @see AllosaurEgg
 * @see LaserGun
 *
 */
public class VendingMachine extends Ground {
    /**
     * Items in the vending machine
     */
    private Map<Type, ArrayList<Item>> products = new HashMap<>();

    /**
     * The quantities of each items in the machine
     */
    private final int NUM_FRUITS = 10;
    private final int NUM_CARNIVORE_MEAL = 5;
    private final int NUM_VEGETARIAN_MEAL = 5;
    private final int NUM_STEGOSAUR_EGG = 3;
    private final int NUM_BRACHIOSAUR_EGG = 3;
    private final int NUM_ALLOSAUR_EGG = 3;

    /**
     * The price of each items in the machine
     */
    public static final int FRUIT_PRICE = 30;
    public static final int VEGETARIAN_MEAL_PRICE = 100;
    public static final int CARNIVORE_MEAL_PRICE = 500;
    public static final int STEGOSAUR_EGG_PRICE = 200;
    public static final int BRACHIOSAUR_EGG_PRICE = 500;
    public static final int ALLOSAUR_EGG_PRICE = 1000;
    public static final int LASER_GUN_PRICE = 500;



    /**
     * Constructor.
     * Initialise quantity of items
     */
    public VendingMachine() {
        super('M');

        products.put(Type.FRUIT, getMachineItems(Type.FRUIT));
        products.put(Type.VEGETARIAN_MEAL, getMachineItems(Type.VEGETARIAN_MEAL));
        products.put(Type.CARNIVORE_MEAL, getMachineItems(Type.CARNIVORE_MEAL));
        products.put(Type.STEGOSAUR_EGG, getMachineItems(Type.STEGOSAUR_EGG));
        products.put(Type.BRACHIOSAUR_EGG, getMachineItems(Type.BRACHIOSAUR_EGG));
        products.put(Type.ALLOSAUR_EGG, getMachineItems(Type.ALLOSAUR_EGG));
        products.put(Type.LASER_GUN, getMachineItems(Type.LASER_GUN));
    }

    /**
     * Let VendingMachine allow the Purchase action for the player
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A PurchaseAction object iff the actor is the player
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if( actor instanceof Player){
            return new Actions(new PurchaseAction());
        }
        return new Actions();
    }

    /**
     * Produces corresponding items to put into the map
     * @param type The type of items to put in
     * @return ArrayList containing item objects
     */
    private ArrayList<Item> getMachineItems(Type type){
        ArrayList<Item> items = new ArrayList<>();
        switch (type){
            case FRUIT:
                for(int i = 0; i < NUM_FRUITS; i++){
                    Fruit newFruit = new Fruit();
                    newFruit.setOnGround();
                    items.add(newFruit);
                }
                break;
            case CARNIVORE_MEAL:
                for(int i = 0; i < NUM_CARNIVORE_MEAL; i++){
                    items.add(new CarnivoreMeal("Carnivore meal"));
                }
                break;
            case VEGETARIAN_MEAL:
                for(int i = 0; i < NUM_VEGETARIAN_MEAL; i++){
                    items.add(new VegetarianMeal("Vegetarian meal"));
                }
                break;
            case BRACHIOSAUR_EGG:
                for(int i = 0; i < NUM_BRACHIOSAUR_EGG; i++){
                    items.add(new BrachiosaurEgg());
                }
                break;
            case STEGOSAUR_EGG:
                for(int i = 0; i < NUM_STEGOSAUR_EGG; i++){
                    items.add(new StegosaurEgg());
                }
                break;
            case ALLOSAUR_EGG:
                for(int i = 0; i < NUM_ALLOSAUR_EGG; i++){
                    items.add(new AllosaurEgg());
                }
                break;
            case LASER_GUN:
                items.add(new LaserGun());
                break;
        }
        return items;
    }

    /**
     * When all the validity checking is complete in PurchaseAction, remove corresponding items from the list
     * @param menuCode A code to distinguish
     */
    public void removeItem(Type menuCode){
        Item toRemove;

        switch (menuCode){
            case FRUIT:
                products.get(Type.FRUIT).remove(0);
                break;
            case VEGETARIAN_MEAL:
                products.get(Type.VEGETARIAN_MEAL).remove(0);
                break;
            case CARNIVORE_MEAL:
                products.get(Type.CARNIVORE_MEAL).remove(0);
                break;
            case STEGOSAUR_EGG:
                products.get(Type.STEGOSAUR_EGG).remove(0);
                break;
            case BRACHIOSAUR_EGG:
                products.get(Type.BRACHIOSAUR_EGG).remove(0);
                break;
            case ALLOSAUR_EGG:
                products.get(Type.ALLOSAUR_EGG).remove(0);
                break;
            case LASER_GUN:
                products.get(Type.LASER_GUN).remove(0);
                break;

        }
    }

    /**
     * Prevents actor to pass through
     * @param actor the Actor to check
     * @return Boolean value of false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }


    /**
     * Returns products instance
     * @return Returns products instance
     */
    public Map<Type, ArrayList<Item>> getProducts() {
        return products;
    }


}
