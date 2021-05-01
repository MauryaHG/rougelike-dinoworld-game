package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.PurchaseAction;
import game.items.CarnivoreMeal;
import game.items.Fruit;
import game.items.LaserGun;
import game.items.VegetarianMeal;

import java.util.ArrayList;

/**
 * @author Jinyeop Oh
 * @version 1.1.0
 * @see Fruit
 * @see CarnivoreMeal
 * @see VegetarianMeal
 * @see Egg
 * @see LaserGun
 *
 */
public class VendingMachine extends Ground {
    private ArrayList<Fruit> fruits;
    private ArrayList<CarnivoreMeal> carnivoreMeals;
    private ArrayList<VegetarianMeal> vegetarianMeals;
    private ArrayList<Egg> stegosaurEggs;
    private ArrayList<Egg> brachiosaurEggs;
    private ArrayList<Egg> allosaurEggs;
    private ArrayList<LaserGun> laserGun;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public VendingMachine() {
        super('V');
    }
}
