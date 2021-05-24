package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.*;

import java.util.List;

public class SeekWaterBehaviour implements Behaviour{

    /**
     * finds closest water or drinks if next to lake
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action that has to be done
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        NumberRange widths = map.getXRange();
        NumberRange heights = map.getYRange();
        int minimumDistance = 1000000;
        Location closetsWater = null;


        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            Actor thisActor = destination.getActor();
            if ((destination.getGround().hasCapability(Type.LAKE))) {
                return new DrinkAction(destination);
            }
        }

        for (int x : widths) {
            for (int y : heights) {
                Ground groundHere = map.at(x, y).getGround();
                if (groundHere.hasCapability(Type.LAKE)){
                    Location there = map.at(x, y);
                            if (!(map.isAnActorAt(there))) {
                                int currentDistance = Util.distance(here, there);
                                if (currentDistance < minimumDistance) {
                                    minimumDistance = currentDistance;
                                    closetsWater = map.at(x, y);
                            }
                        }
                    }
                }
            }
        Location there = closetsWater;

    //get direction to move to get closer
        if (there != null) {
        int currentDistance = Util.distance(here, closetsWater);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = Util.distance(destination, there);
                if (newDistance < currentDistance) {
                    return destination.getMoveAction(actor, exit.getName(), exit.getHotKey());
                    //return new MoveActorAction(destination, exit.getName());
                }
            }
        }
    }
        return null;
    }
}
