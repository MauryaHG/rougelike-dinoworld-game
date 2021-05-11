package game.items;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.HarvestAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jinyeop Oh
 * @version 1.1.3
 * @see Util
 */
public class Fruit extends PortableItem {
    /**
     * An age of this fruit. It is incremented every turn
     */
    private int age = 0;

    /**
     * Representing whether this is on the tree or not
     */
    private boolean isOnTree = false;

    /**
     * Representing whether this is on the bush or not
     */
    private boolean isOnBush = false;

    /**
     * Representing whether this is on the ground or not
     */
    private boolean isOnGround = false;

    /**
     * Representing whether this is in the inventory or not
     */
    private boolean isInInventory = false;

    /**
     * Constructor.
     * add capability
     */
    public Fruit() {
        super("Fruit", 'F');
        addCapability(Type.FRUIT);
    }

    /**
     * If this Fruit that is on the tree has 5% chance to fall in every turn
     * If this Fruit that is on the ground will rot away(removed from the map) in 15 turns
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {

        if(isOnTree){
            if(Util.calcPercentage(Util.FIVE_PERCENT_CHANCE)){
                setOnGround();
            }
        } else if(isOnGround ){
            if(age > 15){
                currentLocation.map().at(currentLocation.x(), currentLocation.y()).removeItem(this);
            }
            age++;
        }
    }

    /**
     * This Fruit can be only picked up when it is on the ground
     * @return returns PickUpItemAction only if it is on the ground otherwise returns null
     */
    @Override
    public PickUpItemAction getPickUpAction() {
        if( isOnGround ){
            return super.getPickUpAction();
        }
        return null;
    }

    /**
     * Allow only Fruit that is on the tree or bush AND is not in the inventory can be harvested using HarvestAction
     * @return returns HavestAction only if this Fruit is on the tree or bush, otherwise returns null
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> allowableActions = new ArrayList<>();
        if( (isOnTree || isOnBush) && !isInInventory ){
            allowableActions.add(new HarvestAction(this));
        }

        return allowableActions;
    }

    /**
     * Returns iff this is in the player's inventory
     * @return instance of DropItemAction if this is in the inventory, otherwise returns null
     */
    @Override
    public DropItemAction getDropAction() {
        if( portable && isInInventory)
            return new DropItemAction(this);

        return null;
    }

    //////Setters and Getters

    public boolean isOnTree() {
        return isOnTree;
    }

    public boolean isOnBush() {
        return isOnBush;
    }

    public void setOnTree(boolean onTree) {
        isOnTree = onTree;
        this.addCapability(Type.ON_TREE);
    }

    public void setOnBush(boolean onBush) {
        isOnBush = onBush;
        this.addCapability(Type.ON_BUSH);
    }

    /**
     * Sets isOnGround = true, isInInventory = true and set the others as false.
     *
     */
    public void setOnGround() {
        isOnBush = false;
        isOnTree = false;
        isOnGround = true;
        isInInventory = true;
        this.removeCapability(Type.ON_TREE);
        this.removeCapability(Type.ON_BUSH);
        this.addCapability(Type.ON_GROUND);
    }

    public void resetAge() {
        this.age = 0;
    }
}
