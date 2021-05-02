package game.items;

import edu.monash.fit2099.engine.Location;
import game.Type;

/**
 * @author Jinyeop Oh
 * @version 1.0.1
 */
public class Egg extends PortableItem {
    /**
     * Age of this Egg
     */
    private int age;

    public Egg(String name) {
        super(name, '0');
        this.addCapability(Type.EGG);//Maurya - code added to see if item on floor is egg
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        //increment age
        age++;
    }

    public int getAge() {
        return age;
    }
}
