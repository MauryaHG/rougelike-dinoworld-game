package game.items;

import edu.monash.fit2099.engine.Location;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.Type;
import game.Util;

/**
 * @author Jinyeop Oh
 * @version 1.0.2
 */
public class Egg extends PortableItem {
    /**
     * Age of this Egg
     */
    private int age;
    private int hatchAfter;
    private Type eggType;

    public Egg(String name) {
        super(name, '0');
        this.addCapability(Type.EGG);//Maurya - code added to see if item on floor is egg
        this.age = 0;
    }

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
                    location.addActor(new Stegosaur("StegosaurNewBorn", Util.getGender()));
                    break;
                case BRACHIOSAUR_EGG:
                    location.addActor(new Brachiosaur("BrachiosaurNewBorn", Util.getGender()));
                    break;
                case ALLOSAUR_EGG:
                    location.addActor(new Allosaur("ALlosaurNewBorn"));
                    break;
            }
        }
    }

    public void setHatchAfter(int hatchAfter) {
        this.hatchAfter = hatchAfter;
    }

    public void setEggType(Type eggType) {
        this.eggType = eggType;
    }

}
