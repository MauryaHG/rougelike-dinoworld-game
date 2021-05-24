package game.items;

import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Pterodactyls;
import game.dinosaurs.Stegosaur;
import game.Type;
import game.Util;

/**
 * @author Jinyeop Oh
 * @version 1.0.3
 */
public class Egg extends PortableItem {
    /**
     * Age of this Egg
     */
    private int age;
    private int hatchAfter;

    /**
     * This is used in the subclass to distinguish the type
     */
    private Type eggType;

    /**
     * Constructor
     * Initialise age as 0 and add capability
     * @param name
     */
    public Egg(String name) {
        super(name, '0');
        this.addCapability(Type.EGG);//Maurya - code added to see if item on floor is egg
        this.age = 0;
    }

    /**
     * Invoked when this is on the ground
     * Pre checking is that if there is another actor on the same square, then just skip for this turn
     * Check whether it is time to hatch, if so, check for the type and remove this and create new instance of corresponding
     * Dinosaur on the same square.
     * @param location The location of this Egg
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if( ++age > hatchAfter ){
            // Pre : check if any actor is on the same location
            if(location.containsAnActor())
                return;

            location.removeItem(this);

            switch (eggType){
                case STEGOSAUR_EGG:
                    location.addActor(new Stegosaur("StegosaurNewBorn"));
                    EcoPoint.increaseEcoPoint(100);
                    break;
                case BRACHIOSAUR_EGG:
                    location.addActor(new Brachiosaur("BrachiosaurNewBorn"));
                    EcoPoint.increaseEcoPoint(1000);
                    break;
                case ALLOSAUR_EGG:
                    location.addActor(new Allosaur("ALlosaurNewBorn"));
                    EcoPoint.increaseEcoPoint(1000);
                    break;
                case PTERODACTYLS_EGG:
                    location.addActor(new Pterodactyls("PterodactylsNewBorn"));
                    EcoPoint.increaseEcoPoint(100);
                    break;
            }
        }
    }

    /**
     * Sets the number of turns of hatching
     * @param hatchAfter
     */
    public void setHatchAfter(int hatchAfter) {
        this.hatchAfter = hatchAfter;
    }

    /**
     * Sets the eggType
     * @param eggType
     */
    public void setEggType(Type eggType) {
        this.eggType = eggType;
    }

}
