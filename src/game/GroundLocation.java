package game;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.grounds.Bush;
import game.grounds.Dirt;
import game.grounds.Tree;
import game.items.Fruit;

import java.util.Random;

/**
 * @author Jinyeop Oh
 * @version 1.1.1
 * @see Type
 * @see game.grounds.Dirt
 * @see game.grounds.Tree
 * @see Bush
 * @see Stegosaur
 * @see Brachiosaur
 * @deprecated see Dirt, Bush and Tree
 */
public class GroundLocation extends Location {
    /**
     * This is used to grow bush at the start of game.
     * This will be set false from the second turn
     */
    private boolean isStartOfGame = true;

    /**
     * Constructor.
     * Locations know which map they are part of, and where.
     *
     * @param map the map that contains this location
     * @param x   x coordinate of this location within the map
     * @param y   y coordinate of this location within the map
     */
    public GroundLocation(GameMap map, int x, int y) {
        super(map, x, y);
    }


    /**
     * Called in every turns
     * Checks what Ground object is on the every square and grow a bush or produces fruit(s)
     */
    @Override
    public void tick() {
        Ground thisGround = getGround();
        boolean isDirt = thisGround.hasCapability(Type.DIRT);
        boolean isTree = thisGround.hasCapability(Type.TREE);
        boolean isBush = thisGround.hasCapability(Type.BUSH);

        if( isDirt ){
            if(isStartOfGame){
                growBush(Util.ONE_PERCENT_CHANCE);
            } else if( neighboursTreeCount() > 0){
                // Do nothing
            } else if( neighboursBushCount() > 1){
                growBush(Util.TEN_PERCENT_CHANCE);
            } else {
                growBush(Util.ZERO_ONE_PERCENT_CHANCE);
            }
        }

        isStartOfGame = false;

        if(isTree){
            if( Util.calcPercentage(Util.FIVE_PERCENT_CHANCE)){     // Supposed to be 50%, but I made 5% for now
                // Produces a fruit object at this location
                Fruit newFruit = new Fruit();
                newFruit.setOnTree(true);
                addItem(newFruit);
                EcoPoint.increaseEcoPoint(1);
            }
        }


        if(isBush){
            // This flag determines whether there is a fruit on bush
            boolean fruitFlag = true;

            // Pre : If a Bush at this location has a fruit, fruitFlag = false
            for(Item item: getItems()){
                if( item instanceof Fruit){
                    if( ((Fruit)item).isOnBush()){
                        fruitFlag = false;
                        break;
                    }
                }
            }

            // This will be true iff this bush has no fruit on it
            if( fruitFlag && Util.calcPercentage(Util.ONE_PERCENT_CHANCE)){
                Fruit newFruit = new Fruit();
                newFruit.setOnBush(true);
                addItem(newFruit);

            }

            // If Brachiosaur is on the same square, 50% chance to kill bush and if so, destroys all the fruits on the square
            if(getActor() != null && getActor().hasCapability(Type.BRACHIOSAUR)){
                if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
                    setGround(new Dirt());

                    for(Item item: getItems()){
                        if(item instanceof Fruit){
                            removeItem(item);
                        }
                    }
                }
            }
        }


        //super.tick();
    }

    /**
     * Counts the number of Bushes nearby
     * @return An int representing the number of Bushes nearby
     */
    private int neighboursBushCount() {
        return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
                .filter(ground -> ground.hasCapability(Type.BUSH)).count();
    }

    /**
     * Counts the number of Trees nearby
     * @return An int representing the number of Trees nearby
     */
    private int neighboursTreeCount() {
        return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
                .filter(ground -> ground.hasCapability(Type.TREE)).count();
    }

    /**
     * Performs an set action by given chance
     * @param chance chance of performing an action
     */
    private void growBush(int chance){
        if(Util.calcPercentage(chance)){
                setGround(new Bush());
        }
    }
}
