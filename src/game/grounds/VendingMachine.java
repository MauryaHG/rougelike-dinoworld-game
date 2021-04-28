package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.PurchaseAction;

import java.util.ArrayList;

/**
 * @author Jinyeop Oh
 * @version 1.0.1
 */
public class VendingMachine extends Ground {
    //private ArrayList<CarnivoreMeal> carnivoreMeals;
    //private ArrayList<VegetarianMeal> vegetarianMeals;
    //private ArrayList<LaserGun> laserGun;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public VendingMachine() {
        super('V');
//        carnivoreMeals = new ArrayList<>();
//        vegetarianMeals = new ArrayList<>();
//        lasergun = new ArrayList<>();

//        storeMachineItems();
    }

//    private void storeMachineItems(){
//        for(int i = 0; i < 5; i++){
//            carnovoreMeals.add(new CarnivoreMeal());
//            vegetarianMeals.add(new VegetarianMeal());
//        }
//        laserGun.add(new LaserGun());
//    }


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new PurchaseAction());
    }
}
