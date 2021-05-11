package game.grounds;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Type;
import game.Util;

/**
 * A class that represents bare dirt. (Modified by the author)
 * @author Jinyeop Oh
 * @version 1.2.1
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
		addCapability(Type.DIRT);
	}

	/**
	 * Grows bush by different percentages
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
 		if( neighboursTreeCount(location) > 0){
			// Do not grow a bush
		} else if( neighboursBushCount(location) > 1){
			growBush(location, Util.TEN_PERCENT_CHANCE);
		} else {
			growBush(location, Util.ZERO_ONE_PERCENT_CHANCE);
		}
	}

	/**
	 * Creates Bush by different chance
	 * @param chance chance of performing an action
	 */
	private void growBush(Location location, int chance){
		if(Util.calcPercentage(chance))
			location.setGround(new Bush());
	}

	/**
	 * Counts the number of Bushes nearby
	 * @return An int respresenting the number of Bushes nearby
	 */
	private int neighboursBushCount(Location location) {
		return (int) location.getExits().stream().map(exit -> exit.getDestination().getGround())
				.filter(ground -> ground.hasCapability(Type.BUSH)).count();
	}

	/**
	 * Counts the number of Trees nearby
	 * @return An int respresenting the number of Trees nearby
	 */
	private int neighboursTreeCount(Location location) {
		return (int) location.getExits().stream().map(exit -> exit.getDestination().getGround())
				.filter(ground -> ground.hasCapability(Type.TREE)).count();
	}


}
