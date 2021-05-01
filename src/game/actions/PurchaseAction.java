package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoPoint;
import game.grounds.VendingMachine;


/**
 * @author Jinyeop Oh
 * @version 1.0.1
 * @see VendingMachine
 */
public class PurchaseAction extends Action {

    /**
     * Retrieve the VendingMachine object first and then prompts user the options to choose.
     * Check validity(input, quantity and eco points) and then do action
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


        // Ask user a menu option
        showMenuOptions(machine);

        System.out.print("Menu option : ");
        char option = display.readChar();

        // Check validity of given input, quantity of items and eco point first then perform corresponding action
        int size = 0;
        String returnMsg = "There was an typo or not enough eco points!";
        switch (option){
            case '1':
                size = machine.getFruits().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.FRUIT_PRICE)){
                    actor.addItemToInventory(machine.getFruits().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.FRUIT);
                    returnMsg = "Successfully purchased the fruit from the vending machine";
                }
                break;
            case '2':
                size = machine.getVegetarianMeals().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.VEGETARIAN_MEAL_PRICE)){
                    actor.addItemToInventory(machine.getVegetarianMeals().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.VEGETARIAN_MEAL);
                    returnMsg = "Successfully purchased the vegetarian meal kit from the vending machine";
                }
                break;
            case '3':
                size = machine.getCarnivoreMeals().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.CARNIVORE_MEAL_PRICE)){
                    actor.addItemToInventory(machine.getCarnivoreMeals().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.CARNIVORE_MEAL);
                    returnMsg = "Successfully purchased the carnivore meal kit from the vending machine";
                }
                break;
            case '4':
                size = machine.getStegosaurEggs().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.STEGOSAUR_EGG_PRICE)){
                    actor.addItemToInventory(machine.getStegosaurEggs().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.STEGOSAUR_EGG);
                    returnMsg = "Successfully purchased the stegosaur egg from the vending machine";
                }
                break;
            case '5':
                size = machine.getBrachiosaurEggs().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.BRACHIOSAUR_EGG_PRICE)){
                    actor.addItemToInventory(machine.getBrachiosaurEggs().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.BRACHIOSAUR_EGG);
                    returnMsg = "Successfully purchased the brachiosaur egg from the vending machine";
                }
                break;
            case '6':
                size = machine.getAllosaurEggs().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.ALLOSAUR_EGG_PRICE)){
                    actor.addItemToInventory(machine.getAllosaurEggs().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.ALLOSAUR_EGG);
                    returnMsg = "Successfully purchased the allosaur egg from the vending machine";
                }
                break;
            case '7':
                size = machine.getLaserGun().size();
                if (size > 0 && EcoPoint.canDecreaseEcoPoint(VendingMachine.LASER_GUN_PRICE)){
                    actor.addItemToInventory(machine.getLaserGun().get(size-1));
                    machine.removeItem(VendingMachine.MenuCode.LASER_GUN);
                    returnMsg = "Successfully purchased the laser gub from the vending machine";
                }
                break;
        }
        return returnMsg + "\n";
    }

    /**
     * Simply shows menu options within the vending machine
     * @param machine
     */
    private void showMenuOptions(VendingMachine machine){
        int numFruits = machine.getFruits().size();
        int numCarnivoreMeals = machine.getCarnivoreMeals().size();
        int numVegetarianMeals = machine.getVegetarianMeals().size();
        int numStegosaurEggs = machine.getStegosaurEggs().size();
        int numBrachiosaurEggs = machine.getBrachiosaurEggs().size();
        int numAllosaurEggs = machine.getAllosaurEggs().size();
        int numLaserGun = machine.getLaserGun().size();

        System.out.println();
        System.out.println("Eco Point : " + EcoPoint.getEcoPoint());
        System.out.println("Fruit (" + numFruits + ") - "+VendingMachine.FRUIT_PRICE+" points. Press 1" );
        System.out.println("Vegetarian Meal (" + numVegetarianMeals + ") - "+VendingMachine.VEGETARIAN_MEAL_PRICE+" points. Press 2" );
        System.out.println("Carnivore Meal (" + numCarnivoreMeals + ") - "+VendingMachine.CARNIVORE_MEAL_PRICE+" points. Press 3" );
        System.out.println("Stegosaur Egg (" + numStegosaurEggs + ") - "+VendingMachine.STEGOSAUR_EGG_PRICE+" points. Press 4" );
        System.out.println("Brachiosaur Egg (" + numBrachiosaurEggs + ") - "+VendingMachine.BRACHIOSAUR_EGG_PRICE+" points. Press 5" );
        System.out.println("Allosaur Egg (" + numAllosaurEggs + ") - "+VendingMachine.ALLOSAUR_EGG_PRICE+" points. Press 6" );
        System.out.println("Laser Gun (" + numLaserGun + ") - "+VendingMachine.LASER_GUN_PRICE+" points. Press 7" );
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
