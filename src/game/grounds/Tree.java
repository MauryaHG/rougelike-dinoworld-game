package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Type;
import game.Util;
import game.actions.HarvestAction;
import game.items.Fruit;

import java.util.ArrayList;

/**
 * Modified from existing
 * @author Jinyeop Oh
 * @version 1.0.3
 * @see Ground parent class
 * @see Fruit Association
 */
public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Fruit> fruitsOnTree;
	private ArrayList<Fruit> fruitsOnGround;

	public Tree() {
		super('+');
		addCapability(Type.TREE);
		fruitsOnTree = new ArrayList<>();
		fruitsOnGround = new ArrayList<>();
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		age++;

		if (fruitsOnTree.size() > 0){
			displayChar = 'Ŧ';
		} else {
			if (age == 10)
				displayChar = 't';
			if (age == 20)
				displayChar = 'T';
		}



		produceFruit();
		checkDropped();
		removeRotted();
	}

	/**
	 * Fruit can be picked up (harvested) from the player
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return new HarvestedAction
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(new HarvestAction());
	}

	/**
     * Check if any fruit on the ground are rotted
	 */
	public void removeRotted(){
		for(int i = 0; i < fruitsOnGround.size(); i++){
			if(fruitsOnGround.get(i).isRotted()){
				fruitsOnGround.remove(i);
			}
		}
	}

	/**
	 * Check if any fruit on the tree dropped, if so, move them into fruitsOnGround
	 */
	private void checkDropped(){
		for(int i = 0; i < fruitsOnTree.size(); i++){
			if(fruitsOnTree.get(i).isDropped()){
				fruitsOnGround.add(fruitsOnTree.get(i));
				fruitsOnTree.remove(i);
			}
		}
	}

	/**
	 * Produces a fruit by 50% chance and add to the fruitOnTree ArrayList if successful
	 */
	public void produceFruit(){
		if(Util.calcPercentage(Util.FIFTY_PERCENT_CHANCE)){
			fruitsOnTree.add(new Fruit());
		}
	}

	/**
	 * Returns the reference to the fruitsOnTree
	 * @return Returns the reference to the fruitsOnTree
	 */
	public ArrayList<Fruit> getFruitsOnTree(){
		return fruitsOnTree;
	}

	/**
	 * Returns the reference to the fruitsOnGround
	 * @return Returns the reference to the fruitsOnGround
	 */
	public ArrayList<Fruit> getFruitsOnGround() {
		return fruitsOnGround;
	}

    /**
	 * Retrieve the number of fruits on the tree
	 * @return An int representing the number of fruits on the tree
	 */
	public int getNumOfFruitsOnTree(){
		return fruitsOnTree.size();
	}

	/**
	 * Retrieve the number of fruits on the ground
	 * @return An int representing the number of fruits on the ground
	 */
	public int getNumOfFruitsOnGround(){
		return fruitsOnGround.size();
	}
}
