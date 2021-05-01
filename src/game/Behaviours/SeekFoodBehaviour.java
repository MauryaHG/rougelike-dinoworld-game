package game.Behaviours;

import edu.monash.fit2099.engine.*;
import game.Behaviours.Behaviour;
import game.Type;
import game.Util;

/**
 * @author Maurya Gamage
 * @version 1.0.1
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a food source.
 */
public class SeekFoodBehaviour implements Behaviour {

    /**
     * Constructor.
     *
     * @param
     */
    public SeekFoodBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        NumberRange widths = map.getXRange();
        NumberRange heights = map.getYRange();
        int minimumDistance = 1000000;
        Location closestFood = null;

        for (int x : widths) {
            for (int y : heights) {
                if (actor.hasCapability(Type.STEGOSAUR) || actor.hasCapability(Type.BRACHIOSAUR)) {
                    if (map.at(x, y).getGround().hasCapability(Type.BUSH)) {
                        Location there = map.at(x, y);
                        if (!(map.isAnActorAt(there))) {
                            int currentDistance = Util.distance(here, there);
                            if (currentDistance < minimumDistance) {
                                minimumDistance = currentDistance;
                                closestFood = map.at(x, y);
                            }
                        }
                    }
                }

            }
        }
        Location there = closestFood;

        if (there != null) {
            int currentDistance = Util.distance(here, closestFood);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = Util.distance(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }
        return null;
    }


    public boolean isValidGround(Actor actor, GameMap map){
        return true;
    }
}