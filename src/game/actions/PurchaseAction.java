package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoPoint;
import game.Type;
import game.grounds.VendingMachine;

import java.util.ArrayList;
import java.util.Map;


/**
 * @author Jinyeop Oh
 * @version 1.1.2
 * @see Type
 */
public class PurchaseAction extends Action {

    /**
     * Retrieve the VendingMachine object first and then prompts user the options to choose.
     * Check validity, which are input, quantity and eco points, and then do action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //In order to ask user a menu option
        Display display = new Display();


        // Get reference to the vending machine
        VendingMachine machine = null;

        // exits are adjacent squares of the location of the player
        for (Exit exit: map.locationOf(actor).getExits()){
            Location adjacent = exit.getDestination();
            if( adjacent.getGround() instanceof VendingMachine){
                machine = (VendingMachine) map.at(adjacent.x(), adjacent.y()).getGround();
                break;
            }
        }

        // Pre : if no VendingMachine found in adjacent squares
        if( machine == null){
            return "No vending machine found. Try again.";
        }

        // Get the Map from the machine
        Map<Type, ArrayList<Item>> products = machine.getProducts();

        // Ask user a menu option
        showMenuOptions(products);

        System.out.print("Menu option : ");
        char option = display.readChar();

        // Check validity of given input, quantity of items and eco point first then perform corresponding action
        int size = 0;
        String returnMsg = "There was an typo, not enough eco points or no more item in the machine!!!!";
        switch (option){
            case '1':
                if (products.get(Type.FRUIT).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.FRUIT_PRICE)){
                    actor.addItemToInventory(products.get(Type.FRUIT).get(0));
                    machine.removeItem(Type.FRUIT);
                    EcoPoint.decreaseEcoPoint(VendingMachine.FRUIT_PRICE);
                    returnMsg = "Successfully purchased the fruit from the vending machine";
                }
                break;
            case '2':
                if (products.get(Type.VEGETARIAN_MEAL).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.VEGETARIAN_MEAL_PRICE)){
                    actor.addItemToInventory(products.get(Type.VEGETARIAN_MEAL).get(0));
                    machine.removeItem(Type.VEGETARIAN_MEAL);
                    EcoPoint.decreaseEcoPoint(VendingMachine.VEGETARIAN_MEAL_PRICE);
                    returnMsg = "Successfully purchased the vegetarian meal kit from the vending machine";
                }
                break;
            case '3':
                if (products.get(Type.CARNIVORE_MEAL).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.CARNIVORE_MEAL_PRICE)){
                    actor.addItemToInventory(products.get(Type.CARNIVORE_MEAL).get(0));
                    machine.removeItem(Type.CARNIVORE_MEAL);
                    EcoPoint.decreaseEcoPoint(VendingMachine.CARNIVORE_MEAL_PRICE);
                    returnMsg = "Successfully purchased the carnivore meal kit from the vending machine";
                }
                break;
            case '4':
                if (products.get(Type.STEGOSAUR_EGG).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.STEGOSAUR_EGG_PRICE)){
                    actor.addItemToInventory(products.get(Type.STEGOSAUR_EGG).get(0));
                    machine.removeItem(Type.STEGOSAUR_EGG);
                    EcoPoint.decreaseEcoPoint(VendingMachine.STEGOSAUR_EGG_PRICE);
                    returnMsg = "Successfully purchased the stegosaur egg from the vending machine";
                }
                break;
            case '5':
                if (products.get(Type.BRACHIOSAUR_EGG).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.BRACHIOSAUR_EGG_PRICE)){
                    actor.addItemToInventory(products.get(Type.BRACHIOSAUR_EGG).get(0));
                    machine.removeItem(Type.BRACHIOSAUR_EGG);
                    EcoPoint.decreaseEcoPoint(VendingMachine.BRACHIOSAUR_EGG_PRICE);
                    returnMsg = "Successfully purchased the brachiosaur egg from the vending machine";
                }
                break;
            case '6':
                if (products.get(Type.ALLOSAUR_EGG).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.ALLOSAUR_EGG_PRICE)){
                    actor.addItemToInventory(products.get(Type.ALLOSAUR_EGG).get(0));
                    machine.removeItem(Type.ALLOSAUR_EGG);
                    EcoPoint.decreaseEcoPoint(VendingMachine.ALLOSAUR_EGG_PRICE);
                    returnMsg = "Successfully purchased the allosaur egg from the vending machine";
                }
                break;
            case '7':
                if (products.get(Type.PTERODACTYLS_EGG).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.PTERODACTYLS_EGG_PRICE)){
                    actor.addItemToInventory(products.get(Type.PTERODACTYLS_EGG).get(0));
                    machine.removeItem(Type.PTERODACTYLS_EGG);
                    EcoPoint.decreaseEcoPoint(VendingMachine.PTERODACTYLS_EGG_PRICE);
                    returnMsg = "Successfully purchased the Pterodactyls egg from the vending machine";
                }
                break;
            case '8':
                if (products.get(Type.LASER_GUN).size() > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.LASER_GUN_PRICE)){
                    actor.addItemToInventory(products.get(Type.LASER_GUN).get(0));
                    machine.removeItem(Type.LASER_GUN);
                    EcoPoint.decreaseEcoPoint(VendingMachine.LASER_GUN_PRICE);
                    returnMsg = "Successfully purchased the laser gub from the vending machine";
                }
                break;
        }
        return returnMsg + "\n";
    }

    /**
     * Simply shows menu options within the vending machine
     * @param products Items in the vending machine
     */
    private void showMenuOptions(Map<Type, ArrayList<Item>> products){
        int numFruits = products.get(Type.FRUIT).size();
        int numCarnivoreMeals = products.get(Type.CARNIVORE_MEAL).size();
        int numVegetarianMeals = products.get(Type.VEGETARIAN_MEAL).size();
        int numStegosaurEggs = products.get(Type.STEGOSAUR_EGG).size();
        int numBrachiosaurEggs = products.get(Type.BRACHIOSAUR_EGG).size();
        int numAllosaurEggs = products.get(Type.ALLOSAUR_EGG).size();
        int numLaserGun = products.get(Type.LASER_GUN).size();
        int numPterodactylsEggs = products.get(Type.PTERODACTYLS_EGG).size();

        System.out.println();
        System.out.println("Eco Point : " + EcoPoint.getEcoPoint());
        System.out.println("Fruit (" + numFruits + ") - "+ VendingMachine.FRUIT_PRICE+" points. Press 1" );
        System.out.println("Vegetarian Meal (" + numVegetarianMeals + ") - "+ VendingMachine.VEGETARIAN_MEAL_PRICE+" points. Press 2" );
        System.out.println("Carnivore Meal (" + numCarnivoreMeals + ") - "+ VendingMachine.CARNIVORE_MEAL_PRICE+" points. Press 3" );
        System.out.println("Stegosaur Egg (" + numStegosaurEggs + ") - "+ VendingMachine.STEGOSAUR_EGG_PRICE+" points. Press 4" );
        System.out.println("Brachiosaur Egg (" + numBrachiosaurEggs + ") - "+ VendingMachine.BRACHIOSAUR_EGG_PRICE+" points. Press 5" );
        System.out.println("Allosaur Egg (" + numAllosaurEggs + ") - "+ VendingMachine.ALLOSAUR_EGG_PRICE+" points. Press 6" );
        System.out.println("Pterodactyls Egg (" + numPterodactylsEggs + ") - "+ VendingMachine.PTERODACTYLS_EGG_PRICE+" points. Press 7" );
        System.out.println("Laser Gun (" + numLaserGun + ") - "+ VendingMachine.LASER_GUN_PRICE+" points. Press 8" );
        System.out.println("Cancel - press any");

    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " selects an item to purchase.";
    }

    @Override
    public String hotkey() {
        return "p";
    }
}
