package game.Dinosaurs;


import edu.monash.fit2099.engine.*;
import game.*;
import game.actions.*;
/**
 * @author :Maurya
 * @version :1.0.2
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
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);

		if (!this.isConscious()){
			list.add(new Actions(new FeedAction(this)));
		}
		if (this.isConscious()){
			if (otherActor.hasCapability(Type.PLAYER)){
				list.add(new Actions(new FeedAction(this)));
				list.add(new Actions(new AttackAction(this)));
			}
		}

		if (otherActor.hasCapability(Type.STEGOSAUR) && Util.isOppositeGender(this, otherActor)){
			list.add(new Actions(new BreedAction(otherActor)));
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
		tick();
		if (isHungry(map)) {
			behaviour.add(new SeekFoodBehaviour());
			behaviour.add(new WanderBehaviour());
			Action seekFood = behaviour.get(0).getAction(this, map);
			if (seekFood != null)
				return seekFood;
		}
		behaviour.add(new FollowBehaviour(this));




		return new DoNothingAction();
	}

	@Override
	public boolean isHungry(GameMap map){
		boolean isHungry = false;
		if (hitPoints <= 90 ){
			Location here = map.locationOf(this);
			System.out.println(this.name + " at (" + here.x() + "," + here.y() +") is getting hungry!!!");
			isHungry = true;
		}
		return isHungry;
	}

}
