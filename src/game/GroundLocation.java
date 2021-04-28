package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.grounds.Bush;

import java.util.Random;

/**
 * @author Jinyeop Oh
 * @version 1.0.0
 */
public class GroundLocation extends Location {

    /**
     * An Enum to set an action
     */
    private NextTurn action = NextTurn.SAME;

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
        boolean isDirt = getGround().hasCapability(Type.DIRT);
        boolean isTree = getGround().hasCapability(Type.TREE);


        if( isDirt ){
            // if it is a Dirt object, grow a bush with 1% chance, and terminate if successful
            if( performAction(NextTurn.GROW, Util.ONE_PERCENT_CHANCE)){
                return;
            }

            // Count the number of around bushes and there are 2 or more, grow a Bush
            if( neighboursBushCount() >= 2){
                action = NextTurn.GROW;
            }

            // But if there is a tree nearby, set to SAME again, otherwise keep set BUSH
            if( neighboursTreeCount() > 0){
                action = NextTurn.SAME;
            }

        }

        if(isTree){

        }

        switch (action){
            case SAME:
                break;
            case GROW:
                performAction(NextTurn.GROW, Util.TEN_PERCENT_CHANCE);
                break;

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
                setGround(new Bush(';'));
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
