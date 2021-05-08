package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Type;
import game.Util;
import game.items.Fruit;

import java.util.ArrayList;

/**
 * Modified from existing
 * @author Jinyeop Oh
 * @version 1.1.0
 * @see Ground parent class
 */
public class Tree extends Ground {
	private int age = 0;

	public Tree() {
		super('+');
		addCapability(Type.TREE);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		age++;

		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

	}

}
