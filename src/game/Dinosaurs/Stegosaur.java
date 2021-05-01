package game.Dinosaurs;


import edu.monash.fit2099.engine.*;
import game.*;
import game.Behaviours.*;
import game.actions.*;
/**
 * @author :Maurya
 * @version :1.0.2
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.

	private int STEG_ADULT_AGE = 30;
	private int MIN_HUNGER = 90;
	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 *
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name, String gender) {
		super(name, 'd', 100, gender);
		this.hitPoints = 50;
		addCapability(Type.STEGOSAUR);
		this.age = STEG_ADULT_AGE;
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);


		if (otherActor.hasCapability(Type.PLAYER)){
			if (!this.isConscious()){
				list.add(new Actions(new FeedAction(this)));
			}
			if (this.isConscious()){
				list.add(new Actions(new FeedAction(this)));
				list.add(new Actions(new AttackAction(this)));
			}
		}
		return list;
	}

	/**
	 * Figure out what to do next.
	 *
	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 *
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviour.clear();

		if(this.hitPoints>=50) {
			behaviour.add(new BreedBehaviour());
		}

		if (isHungry(MIN_HUNGER, map)) {
			behaviour.add(new SeekFoodBehaviour());
		}
		for (Behaviour index : behaviour) {
			Action action = index.getAction(this, map);
			if (action != null) {
				tick();
				return action;
			}
		}
		tick();
		return new DoNothingAction();
	}
}
