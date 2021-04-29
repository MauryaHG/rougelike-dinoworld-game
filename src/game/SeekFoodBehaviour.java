package game;

import edu.monash.fit2099.engine.*;

/**
 * @author Maurya Gamage
 * @version 1.0.0
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a food source.
 */
public class SeekFoodBehaviour implements Behaviour {

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public SeekFoodBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        NumberRange widths = map.getXRange();
        NumberRange heights = map.getYRange();
        int minimumDistance = 0;

        Location closestFood = null;

        for (int x : widths) {
            for (int y : heights) {
                if (map.at(x, y).getGround().hasCapability(Type.FRUIT)) {
                    Location there = map.at(x, y);
                    int currentDistance = Util.distance(here, there);
                    if (currentDistance < minimumDistance) {
                        closestFood = map.at(x, y);
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
}
