package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.grounds.Bush;
import game.grounds.Dirt;
import game.items.Fruit;

import java.util.Random;

/**
 * @author Jinyeop Oh
 * @version 1.1.0
 * @see Type
 * @see game.grounds.Dirt
 * @see game.grounds.Tree
 * @see Bush
 * @see Stegosaur
 * @see Brachiosaur
 */
public class GroundLocation extends Location {

    /**
     * An Enum to set an action
     */
    private NextTurn action = NextTurn.SAME;

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



    @Override
    public void tick() {
        Ground thisGround = getGround();
        boolean isDirt = thisGround.hasCapability(Type.DIRT);
        boolean isTree = thisGround.hasCapability(Type.TREE);
        boolean isBush = thisGround.hasCapability(Type.BUSH);

        if( isDirt ){
            if(isStartOfGame){
                performAction(NextTurn.GROW, Util.ONE_PERCENT_CHANCE);
            } else if( neighboursTreeCount() > 0){
                // Do nothing
            } else if( neighboursBushCount() > 1){
                performAction(NextTurn.GROW, Util.TEN_PERCENT_CHANCE);
            } else {
                performAction(NextTurn.GROW, Util.ZERO_ONE_PERCENT_CHANCE);
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

            // If Brachiosaur is on the same square, 50% chance to kill bush
            if(getActor() != null && getActor().hasCapability(Type.BRACHIOSAUR)){
                if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
                    setGround(new Dirt());
                }
            }
        }


        super.tick();
    }

    /**
     * Counts the number of Bushes nearby
     * @return An int respresenting the number of Bushes nearby
     */
    private int neighboursBushCount() {
        return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
                .filter(ground -> ground.hasCapability(Type.BUSH)).count();
    }

    /**
     * Counts the number of Trees nearby
     * @return An int respresenting the number of Trees nearby
     */
    private int neighboursTreeCount() {
        return (int) getExits().stream().map(exit -> exit.getDestination().getGround())
                .filter(ground -> ground.hasCapability(Type.TREE)).count();
    }

    /**
     * Performs an set action by given chance
     * @param action An Enum type
     * @param chance chance of performing an action
     * @return true if set new ground, otherwise false
     */
    private boolean performAction(NextTurn action, int chance){
        if(new Random().nextInt(chance) == 0){
            if( action == NextTurn.GROW ){
                setGround(new Bush());
                return true;
            }
        }
        return false;

    }

    /**
     * An Enum for set next action after checking conditions
     */
    private enum NextTurn {
        SAME, GROW
    }
}
