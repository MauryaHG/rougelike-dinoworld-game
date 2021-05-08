package game.actions;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.items.*;

import java.util.List;

/**
 * No hot key is used for the Action. In case if there are number of dinosaurs in adjacent squares
 * @author :Jinyeop Oh
 * @version :1.1.0
 * @see AllosaurCorpse
 * @see BrachiosaurCorpse
 * @see StegosaurCorpse
 * @see CarnivoreMeal
 * @see Fruit
 * @see VegetarianMeal
 * @see Egg
 */
public class FeedAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * A Display that prompts and gets input from the user
     */
    private Display display = new Display();

    /**
     * Constructor. Sets the target of feeding
     * @param target A Dinosuar
     */
    public FeedAction(Actor target) {
        this.target = target;
    }

    /**
     * Checks what kind of items are in the player's inventory
     * Then display and feed a food depending on the herbivore or carnivore
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return An action that is performed or the reason why feeding failed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasFruits = false;
        boolean hasVegeMeals = false;
        boolean hasCarnMeals = false;
        boolean hasStegoCorpse = false;
        boolean hasBrachioCoprse = false;
        boolean hasAllosaurCoprse = false;
        boolean hasEgg = false;

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

            if( item instanceof StegosaurCorpse)
                hasStegoCorpse = true;

            if( item instanceof BrachiosaurCorpse)
                hasBrachioCoprse = true;

            if( item instanceof AllosaurCorpse)
                hasAllosaurCoprse = true;

            if( item instanceof Egg)
                hasEgg = true;
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
                    if( item instanceof Fruit){
                        actor.removeItemFromInventory(item);
                        break;
                    }
                }

            } else if ( option == '2' && hasVegeMeals){
                target.heal(10000); // max hitPoints will be set
                for(Item item: items){
                    if( item instanceof VegetarianMeal){
                        actor.removeItemFromInventory(item);
                        break;
                    }
                }
            } else {
                return "Wrong input given!!";
            }
        }

        if ( target instanceof Allosaur ){
            // Pre : if player has no food to feed Allosaur, terminate
            if( !hasCarnMeals && !hasStegoCorpse && !hasBrachioCoprse && !hasAllosaurCoprse && !hasEgg)
                return "Player has no Carnovore Meal Kit to feed";

            if( hasCarnMeals )
                System.out.println("Feed Carnivore Meal Kit. Press 3");

            if( hasStegoCorpse )
                System.out.println("Feed Stegosaur corpse. Press 4");

            if( hasBrachioCoprse )
                System.out.println("Feed Brachiosaur corpse. Press 5");

            if( hasAllosaurCoprse )
                System.out.println("Feed Allosaur corpse. Press 6");

            if( hasEgg )
                System.out.println("Feed Egg(Random). Press 7");


            // Get option input
            char option = display.readChar();

            if( option == '3' && hasCarnMeals){
                target.heal(10000);
                for(Item item: items){
                    if( item instanceof CarnivoreMeal ){
                        actor.removeItemFromInventory(item);
                        break;
                    }
                }
            } else if( option == '4' && hasStegoCorpse){
                target.heal(50);
                for(Item item: items){
                    if( item instanceof StegosaurCorpse ){
                        actor.removeItemFromInventory(item);
                        break;
                    }
                }
            } else if( option == '5' && hasBrachioCoprse){
                target.heal(10000); // heal MAX
                for(Item item: items){
                    if( item instanceof BrachiosaurCorpse ){
                        actor.removeItemFromInventory(item);
                        break;
                    }
                }
            } else if( option == '6' && hasAllosaurCoprse){
                target.heal(50);
                for(Item item: items){
                    if( item instanceof AllosaurCorpse ){
                        actor.removeItemFromInventory(item);
                        break;
                    }
                }
            } else if( option == '7' && hasEgg){
                target.heal(10);
                for(Item item: items){
                    if( item instanceof Egg ){
                        actor.removeItemFromInventory(item);
                        break;
                    }
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
