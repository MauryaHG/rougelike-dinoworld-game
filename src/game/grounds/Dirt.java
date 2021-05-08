package game.grounds;

import edu.monash.fit2099.engine.Ground;
import game.Type;

/**
 * A class that represents bare dirt. (Modified by the author)
 * @author Jinyeop Oh
 * @version 1.0.1
 */
public class Dirt extends Ground {
	public Dirt() {
		super('.');
		addCapability(Type.DIRT);
	}

}
