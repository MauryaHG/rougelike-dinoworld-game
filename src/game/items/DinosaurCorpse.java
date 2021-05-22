package game.items;

import edu.monash.fit2099.engine.Location;
import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.1
 * @see PortableItem
 */
public class DinosaurCorpse extends PortableItem{
    /**
     * An int that the number of turns the corpse will rot away
     */
    private int rotAge;

    /**
     * An int representing the number of turns
     */
    private int age;

    /**
     * An int representing the amount of food points left in item
     */
    private int foodPoints;
    /**
     * Initialise the name, char, age as 0 and add capability.
     * @param name
     */
    public DinosaurCorpse(String name, int foodPoints) {
        super(name, 'C');
        this.age = 0;
        this.foodPoints = foodPoints;
        this.addCapability(Type.CORPSE);
    }

    /**
     * Only invoked when this is on the ground.
     * This increase age by 1 in every turn and check if it exceed the rotAge
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if( ++age > rotAge )
            currentLocation.removeItem(this);
    }

    /**
     * Sets the rotAge
     * @param rotAge
     */
    public void setRotAge(int rotAge) {
        this.rotAge = rotAge;
    }

    public int getFoodPoints() {
        return foodPoints;
    }

    public void reduceFoodPoints(int decrease){
        this.foodPoints -= decrease;
    }
}
