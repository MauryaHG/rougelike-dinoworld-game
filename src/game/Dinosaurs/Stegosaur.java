package game.Dinosaurs;


import edu.monash.fit2099.engine.*;
import game.*;
import game.actions.AttackAction;
import game.actions.FeedAction;

import java.util.ArrayList;
import java.util.List;
/**
 * @author :Maurya
 * @version :1.0.1
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
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

		//behaviour.add(new WanderBehaviour());
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);

		if (!this.isConscious()){
			list.add(new Actions(new FeedAction(this)));
		}
		if (otherActor.hasCapability(Type.PLAYER)){
			list.add(new Actions(new FeedAction(this)));
			list.add(new Actions(new AttackAction(this)));
		}

		if (otherActor.hasCapability(Type.STEGOSAUR)){
			//list.add(new Actions( ));
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

		if (isHungry()) {
			behaviour.add(new SeekFoodBehaviour());
			Action seekFood = behaviour.get(0).getAction(this, map);
			if (seekFood != null)
				return seekFood;
		}



		behaviour.add(new FollowBehaviour(this));




		return new DoNothingAction();
	}

	@Override
	public boolean isHungry(){
		return hitPoints <= 90;
	}

}
