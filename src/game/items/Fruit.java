package game.items;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.HarvestAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jinyeop Oh
 * @version 1.1.2
 * @see Util
 */
public class Fruit extends PortableItem {
    /**
     * An age of this fruit. It is incremented every turn
     */
    private int age = 0;
    private boolean isOnTree = false;
    private boolean isOnBush = false;
    private boolean isOnGround = false;
    private boolean isInInventory = false;

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
            age++;
            if(age > 15){
                currentLocation.map().at(currentLocation.x(), currentLocation.y()).removeItem(this);
            }
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

    @Override
    public DropItemAction getDropAction() {
        if( portable ){
            if( isInInventory ){
                return new DropItemAction(this);
            }
        }
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

    public void setOnGround() {
        isOnBush = false;
        isOnTree = false;
        isOnGround = true;
        isInInventory = true;
        this.addCapability(Type.ON_GROUND);
    }

    public void resetAge() {
        this.age = 0;
    }
}
