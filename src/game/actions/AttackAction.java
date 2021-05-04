package game.actions;

import edu.monash.fit2099.engine.*;
import game.Dinosaurs.Allosaur;
import game.Dinosaurs.Stegosaur;
import game.Type;
import game.items.PortableItem;
import game.items.StegosaurCorpse;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// Pre : if actor is Allosaur and target(Stegosaur) is attacked by allosaur and has not been 20 turns
		if( actor instanceof Allosaur && ((Stegosaur)target).isGetAttacked()){
			return "This " +target+" got attacked by " + actor + " so cannot attack again.";
		}

		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);

		if(!target.isConscious()){
			map.locationOf(target).addItem(new StegosaurCorpse());
			map.removeActor(target);
		} else {
			if( actor instanceof Allosaur ){
				((Stegosaur)target).setGetAttacked(true);
			}
		}


		if(actor.hasCapability(Type.ALLOSAUR))
			actor.heal(damage);


		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
