package game.items;

import edu.monash.fit2099.engine.Location;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see PortableItem
 */
public class DinosaurCorp extends PortableItem{
    /**
     * The age for rotting
     */
    private int rotAge;
    private int age;

    public DinosaurCorp(String name) {
        super(name, 'C');
        this.age = 0;
    }




    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if( ++age > rotAge )
            currentLocation.removeItem(this);
    }

    public void setRotAge(int rotAge) {
        this.rotAge = rotAge;
    }
}
