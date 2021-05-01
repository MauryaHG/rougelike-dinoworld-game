package game.grounds;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.PurchaseAction;
import game.items.*;

import java.util.ArrayList;

/**
 * @author Jinyeop Oh
 * @version 1.1.2
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
    private ArrayList<Fruit> fruits = new ArrayList<>();
    private ArrayList<CarnivoreMeal> carnivoreMeals = new ArrayList<>();
    private ArrayList<VegetarianMeal> vegetarianMeals = new ArrayList<>();
    private ArrayList<StegosaurEgg> stegosaurEggs = new ArrayList<>();
    private ArrayList<BrachiosaurEgg> brachiosaurEggs = new ArrayList<>();
    private ArrayList<AllosaurEgg> allosaurEggs = new ArrayList<>();
    private ArrayList<LaserGun> laserGun = new ArrayList<>();

    private final int NUM_FRUITS = 10;
    private final int NUM_CARNIVORE_MEAL = 5;
    private final int NUM_VEGETARIAN_MEAL = 5;
    private final int NUM_STEGOSAUR_EGG = 3;
    private final int NUM_BRACHIOSAUR_EGG = 3;
    private final int NUM_ALLOSAUR_EGG = 3;

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

        for(int i = 0; i < NUM_FRUITS; i++){
            Fruit newFruit = new Fruit();
            newFruit.setOnGround();
            fruits.add(newFruit);
        }

        for(int i = 0; i < NUM_CARNIVORE_MEAL; i++){
            carnivoreMeals.add(new CarnivoreMeal("Carnivore meal"));
        }

        for(int i = 0; i < NUM_VEGETARIAN_MEAL; i++){
            vegetarianMeals.add(new VegetarianMeal("Vegetarian meal"));
        }

        for(int i = 0; i < NUM_BRACHIOSAUR_EGG; i++){
            brachiosaurEggs.add(new BrachiosaurEgg("Brachiosaur egg"));
        }

        for(int i = 0; i < NUM_STEGOSAUR_EGG; i++){
            stegosaurEggs.add(new StegosaurEgg("Stegosaur egg"));
        }

        for(int i = 0; i < NUM_ALLOSAUR_EGG; i++){
            allosaurEggs.add(new AllosaurEgg("Allosaur egg"));
        }

        laserGun.add(new LaserGun("Laser Gun", 'L', 10, "Shoots"));



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
     * When all the validity checking is complete in PurchaseAction, remove corresponding items from the list
     * @param menuCode A code to distinguish
     */
    public void removeItem(VendingMachine.MenuCode menuCode){
        switch (menuCode){
            case FRUIT:
                fruits.remove(fruits.size()-1);
                break;
            case VEGETARIAN_MEAL:
                vegetarianMeals.remove(vegetarianMeals.size()-1);
                break;
            case CARNIVORE_MEAL:
                carnivoreMeals.remove(carnivoreMeals.size()-1);
                break;
            case STEGOSAUR_EGG:
                stegosaurEggs.remove(stegosaurEggs.size()-1);
                break;
            case BRACHIOSAUR_EGG:
                brachiosaurEggs.remove(brachiosaurEggs.size()-1);
                break;
            case ALLOSAUR_EGG:
                allosaurEggs.remove(allosaurEggs.size()-1);
                break;
            case LASER_GUN:
                laserGun.remove(laserGun.size()-1);
                break;

        }
    }

    // setters and getters
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

    public ArrayList<CarnivoreMeal> getCarnivoreMeals() {
        return carnivoreMeals;
    }

    public void setCarnivoreMeals(ArrayList<CarnivoreMeal> carnivoreMeals) {
        this.carnivoreMeals = carnivoreMeals;
    }

    public ArrayList<VegetarianMeal> getVegetarianMeals() {
        return vegetarianMeals;
    }

    public void setVegetarianMeals(ArrayList<VegetarianMeal> vegetarianMeals) {
        this.vegetarianMeals = vegetarianMeals;
    }

    public ArrayList<StegosaurEgg> getStegosaurEggs() {
        return stegosaurEggs;
    }

    public void setStegosaurEggs(ArrayList<StegosaurEgg> stegosaurEggs) {
        this.stegosaurEggs = stegosaurEggs;
    }

    public ArrayList<BrachiosaurEgg> getBrachiosaurEggs() {
        return brachiosaurEggs;
    }

    public void setBrachiosaurEggs(ArrayList<BrachiosaurEgg> brachiosaurEggs) {
        this.brachiosaurEggs = brachiosaurEggs;
    }

    public ArrayList<AllosaurEgg> getAllosaurEggs() {
        return allosaurEggs;
    }

    public void setAllosaurEggs(ArrayList<AllosaurEgg> allosaurEggs) {
        this.allosaurEggs = allosaurEggs;
    }

    public ArrayList<LaserGun> getLaserGun() {
        return laserGun;
    }

    public void setLaserGun(ArrayList<LaserGun> laserGun) {
        this.laserGun = laserGun;
    }

    public enum MenuCode{
        FRUIT, CARNIVORE_MEAL, VEGETARIAN_MEAL, STEGOSAUR_EGG, BRACHIOSAUR_EGG, ALLOSAUR_EGG, LASER_GUN
    }
}
