package game.actions;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Pterodactyls;
import game.dinosaurs.Stegosaur;
import game.Type;
import game.items.PetrodactylCorpse;
import game.items.StegosaurCorpse;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 * @author jinyeopoh
 * @version 1.0.2
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	/**
	 * Executes attacking. Checks if the target is previously attacked by Allosaur first.
	 * If not, perform the action and set corpse or decrease hitPoint of the target
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return A String that representing what action is performed if attacking is successful.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int actorX = map.locationOf(actor).x();
		int actorY = map.locationOf(actor).y();
		int targetX = map.locationOf(target).x();
		int targetY = map.locationOf(target).y();

		// Pre : if actor is Allosaur and target(Stegosaur) is attacked by allosaur and has not been 20 turns, return
		if( actor instanceof Allosaur && target instanceof Stegosaur ){
			if(((Stegosaur)target).isGetAttacked()){
				return "This " +target+"("+targetX+","+targetY+")"+" got attacked by " + actor +"("+actorX+","+actorY+")" + " so cannot attack again.";
			}
		}


		// get weapon and attack the target
		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();

		// This is for Pterodactyl
		if (target instanceof Pterodactyls){
			actor.heal(30);
			map.removeActor(target);
			map.locationOf(target).addItem(new PetrodactylCorpse());
			return actor +"("+actorX+","+actorY+")" + " " + weapon.verb() + " " + target + " and the target is dead.";
		}

		// This is for Stegosaur
		target.hurt(damage);
		String result = actor +"("+actorX+","+actorY+")" + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		if(!target.isConscious()){
			map.locationOf(target).addItem(new StegosaurCorpse());
			map.removeActor(target);
		} else {
			if( actor instanceof Allosaur && target instanceof Stegosaur){
				((Stegosaur)target).setGetAttacked(true);
			}
		}

		if(actor.hasCapability(Type.ALLOSAUR))
			actor.heal(damage);


		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " ate " + target;
	}
}
