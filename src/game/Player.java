package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.grounds.Lake;

import java.util.Random;

/**
 * Class representing the Player.
 *
 * @author Jinyeop Oh
 * @version 1.0.2
 */
public class Player extends Actor {

	private Menu menu = new Menu();

	/**
	 * turn counter
	 */
	private int turns = 0;	// jinyeop

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addCapability(Type.PLAYER);		// Jinyeop
	}

	/**
	 * In every 10 turns, calculate the chance of raining. Then Calculate the number of sips and apply it to all lakes
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Util.setRaining(false);

		// Raining part - jinyeop
		if( ++turns > 10 && Util.calcPercentage(Util.TWENTY_PERCENT_CHANCE)){
			turns = 0;
			Util.setRaining(true); // This is true for one turn only

			// Calculates the rain fall and number of sips to increment
			double rainFall = ((double)new Random().nextInt(6)+1)/10;
			int sips = (int)(rainFall*20);

			// Find all lakes in the map
			for(int y: map.getYRange()){
				for(int x: map.getXRange()){
					if( map.at(x, y).getGround() instanceof Lake) {
						((Lake) map.at(x, y).getGround()).incrementSips(sips);
					}
				}
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}
}
