package game.actions;

import edu.monash.fit2099.engine.*;
import game.Dinosaurs.Allosaur;
import game.Dinosaurs.Brachiosaur;
import game.Dinosaurs.Stegosaur;
import game.items.CarnivoreMeal;
import game.items.Fruit;
import game.items.VegetarianMeal;

import java.util.List;

/**
 * No hot key is used for the Action. In case if there are number of dinosaurs in adjacent squares
 * @author :Jinyeop Oh
 * @version :1.0.0
 * @see  Allosaur
 * @see Brachiosaur
 * @see Stegosaur
 * @see CarnivoreMeal
 * @see Fruit
 * @see VegetarianMeal
 */
public class FeedAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    private Display display = new Display();


    public FeedAction(Actor target) {
        this.target = target;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasFruits = false;
        boolean hasVegeMeals = false;
        boolean hasCarnMeals = false;

        // Count number of each items in the inventory
        // boolean values will set true if there is at least 1 item, respectively
        List<Item> items = actor.getInventory();
        for (Item item: items){
            if( item instanceof Fruit)
                hasFruits = true;

            if( item instanceof VegetarianMeal)
                hasVegeMeals = true;

            if( item instanceof CarnivoreMeal)
                hasCarnMeals = true;
        }


        if( target instanceof Stegosaur || target instanceof Brachiosaur){
            // Pre : if no food to feed, terminate
            if( !hasFruits && !hasVegeMeals) {
                return "Player has no food to feed this herbivore.";
            }

            if( hasFruits )
                System.out.println("Feed Fruit. Press 1.");

            if ( hasVegeMeals )
                System.out.println("Feed Vegetarian Meal Kit. Press 2");

            // Get option input
            char option = display.readChar();

            // Feed dinosaur
            if( option == '1' && hasFruits){
                target.heal(20);
                for(Item item: items){
                    if( item instanceof Fruit)
                        actor.removeItemFromInventory(item);
                    break;
                }

            } else if ( option == '2' && hasVegeMeals){
                target.heal(10000); // max hitPoints will be set
                for(Item item: items){
                    if( item instanceof VegetarianMeal)
                        actor.removeItemFromInventory(item);
                    break;
                }
            } else {
                return "Wrong input given!!";
            }
        }

        if ( target instanceof Allosaur ){
            // Pre : if player has no meal kit, terminate
            if( !hasCarnMeals )
                return "Player has no Carnovore Meal Kit to feed";

            System.out.println("Feed Carnivore Meal Kit. Press 3");
            // Get option input
            char option = display.readChar();

            if( option == '3' && hasCarnMeals){
                target.heal(10000);
                for(Item item: items){
                    if( item instanceof CarnivoreMeal )
                        actor.removeItemFromInventory(item);
                    break;
                }
            } else {
                return "Wrong input given!!";
            }

        }

        return "Successfully fed the dinosaur!!";
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds the " +target;
    }


}
