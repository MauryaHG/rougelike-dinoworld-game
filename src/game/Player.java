package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.grounds.Lake;

import java.util.Random;
import java.util.Scanner;

/**
 * Class representing the Player.
 *
 * @author Jinyeop Oh
 * @version 1.1.0
 */
public class Player extends Actor {
	/**
	 * Number of turns when user selects challenge game and only set prior to the start of game
	 */
	private int turnsRequired;

	/**
	 * Number of eco points when user selects challenge game and only set prior to the start of game
	 */
	private int pointsRequired;

	/**
	 * When user selects challenge game, this will be set true and only prior to the start of game
	 */
	private boolean isChallengeMode = false;

	private boolean win = false;
	private boolean lose = false;

	/**
	 * turn counter
	 */
	private int turns = 0;	// jinyeop


	private Menu menu = new Menu();


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
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// When Challenge Mode, check for winning/losing condition - jinyeop
		if( isChallengeMode ){
			turns++;
			if(turns < turnsRequired && EcoPoint.getEcoPoint() >= pointsRequired){
				win = true;
			} else if(turnsRequired <= turns){
				lose = true;
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Set numOfTurns and numOfPoints as positive numbers
	 * and is only called when user selects challenge game
	 */
	public void setTurnsAndPoints(){
		Scanner scanner = new Scanner(System.in);
		isChallengeMode = true; // only set true when user selects challenge mode

		boolean flag = true;
		while (flag){
			try{
				System.out.print("Choose number of turns : ");
				turnsRequired = scanner.nextInt();
				scanner.nextLine();

				if( isNonPositive(turnsRequired))
					continue;

				System.out.print("Choose number of points : ");
				pointsRequired = scanner.nextInt();
				scanner.nextLine();

				if( isNonPositive(pointsRequired))
					continue;

				flag = false;
			} catch (Exception e){
				System.out.println("Invalid input. Only digits allowed. Try again.");
				scanner.nextLine();
			}
		}

	}

	/**
	 * Checks given int value is non-positive
	 * @param num number
	 * @return true is non-potive else false
	 */
	private boolean isNonPositive(int num){
		if( num <= 0 ){
			System.out.println("Non-positive number is not allowed. Try again");
			return true;
		}
		return false;
	}

	public boolean isWin() {
		return win;
	}

	public boolean isLose() {
		return lose;
	}
}

