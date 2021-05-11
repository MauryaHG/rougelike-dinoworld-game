package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.Type;
import game.Util;
import game.items.Fruit;

import java.util.ArrayList;

/**
 * Modified from existing
 * @author Jinyeop Oh
 * @version 1.2.0
 * @see Ground parent class
 */
public class Tree extends Ground {
	private int age = 0;

	public Tree() {
		super('+');
		addCapability(Type.TREE);
	}

	/**
	 * Increments the age of this Tree and produces a fruit by 50% chance in every turns.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		age++;

		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		if( Util.calcPercentage(Util.FIVE_PERCENT_CHANCE)){     // Supposed to be 50%, but I made 5% for now
			// Produces a fruit object at this location
			Fruit newFruit = new Fruit();
			newFruit.setOnTree(true);
			location.addItem(newFruit);
			EcoPoint.increaseEcoPoint(1);
		}
	}

}
