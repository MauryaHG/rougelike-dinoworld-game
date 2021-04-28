package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Dinosaurs.Brachiosaur;
import game.Dinosaurs.Stegosaur;
import game.grounds.Bush;
import game.grounds.Egg;

import java.util.Random;

/**
 * @author Jinyeop Oh
 * @version 1.0.2
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

    /**
     * used to grow a bush by 1% chance at the start of game
     * then turns into false
     */
    private boolean firstTurn = true;

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
        boolean isStegosaurEgg = getGround().hasCapability(Type.STEGOSAUR_EGG);
        boolean isBrachiosaurEgg = getGround().hasCapability(Type.BRACHIOSAUR_EGG);
        boolean isAllosaurEgg = getGround().hasCapability(Type.ALLOSAUR_EGG);


        if( isDirt ){

            if( firstTurn ) { // Let every dirt instance have 1% chance to grow a bush at the start of the game
                performAction(NextTurn.GROW, Util.ONE_PERCENT_CHANCE);
            } else if( neighboursTreeCount() > 0){  // Second turn onwards from this else-if
                return;
            } else if( neighboursBushCount() > 1){
                performAction(NextTurn.GROW, Util.TEN_PERCENT_CHANCE);
            } else {
                performAction(NextTurn.GROW, Util.ONE_PERCENT_CHANCE);
            }
        }

        if(isTree){
        }

        if(isStegosaurEgg){
            if(((Egg) getGround()).getAge() > 30){
                if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
                    new Stegosaur("newStegoMale", "MALE");     // Naming will be looked later
                } else{
                    new Stegosaur("newStegoFemale", "FEMALE");
                }

            }
        }

        if(isBrachiosaurEgg){
            if(((Egg) getGround()).getAge() > 50){
                if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
                    new Brachiosaur("newBrachioMale", "MALE");     // Naming will be looked later
                } else{
                    new Brachiosaur("newBrachioFemale", "FEMALE");
                }

            }
        }

        if(isAllosaurEgg){
            if(((Egg) getGround()).getAge() > 50){
                if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
                    //new Allosaur("newAlloMale", "MALE");     // Naming will be looked later
                } else{
                    //new Allosaur("newAlloFemale", "FEMALE");
                }

            }
        }

        if( firstTurn )
            firstTurn = false;

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
