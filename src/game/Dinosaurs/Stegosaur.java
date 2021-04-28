package game.Dinosaurs;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.Type;
import game.actions.AttackAction;
import game.actions.FeedAction;

import java.util.ArrayList;
import java.util.List;
/**
 * @author :Maurya
 * @version :1.0.0
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private List<Behaviour> behaviour = new ArrayList<Behaviour>();

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
		//behaviour.add(new FollowBehaviour());
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		if (!this.isConscious()){
			return new Actions(new FeedAction(this));
		}
		if (otherActor.hasCapability(Type.PLAYER)){
			return new Actions(new FeedAction(this));
		}
		return new Actions(new AttackAction(this));
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
		if (isHungry()){
			//behaviour.add(new FollowBehaviour());
			Action seekFood = behaviour.get(0).getAction(this, map);
		}
		/**Action wander = behaviour.getAction(this, map);
		if (wander != null)
			return wander;*/
		tick();
		return new DoNothingAction();
	}

	@Override
	public boolean isHungry(){
		return hitPoints <= 90;
	}

}
