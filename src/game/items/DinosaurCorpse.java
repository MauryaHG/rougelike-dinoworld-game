package game.items;

import edu.monash.fit2099.engine.Location;
import game.Type;

/**
 * @author jinyeopoh
 * @version 1.0.0
 * @see PortableItem
 */
public class DinosaurCorpse extends PortableItem{
    /**
     * The age for rotting
     */
    private int rotAge;
    private int age;

    public DinosaurCorpse(String name) {
        super(name, 'C');
        this.age = 0;
        this.addCapability(Type.CORPSE);
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
