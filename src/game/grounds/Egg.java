package game.grounds;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * @author Jinyeop Oh
 * @version 1.0.1
 */
public class Egg extends Ground {
    /**
     * Age of this Egg
     */
    private int age;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Egg() {
        super('O');
        this.age = 0;
        // Every eggs have to be given a capability after breeding
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
