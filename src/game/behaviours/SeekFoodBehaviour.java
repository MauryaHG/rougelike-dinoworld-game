package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;
import game.actions.AttackAction;
import game.actions.EatFoodAction;

import java.util.List;

/**
 * @author Maurya Gamage
 * @version 1.2.1
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a food source.
 */
public class SeekFoodBehaviour implements Behaviour {


    /**
     * finds closest food or eats food around the actor
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
        Location closestFood = null;
        Type sourceOne = null;
        Type sourceTwo = null;
        Type groundTypeOne = null;
        Type groundTypeTwo = null;
        List<Item> itemsHere = here.getItems();


        if(actor.hasCapability(Type.STEGOSAUR)) {
            sourceOne = Type.ON_BUSH;
            sourceTwo = Type.ON_GROUND;
            groundTypeOne = Type.BUSH;
            groundTypeTwo = Type.TREE;
        }

        if(actor.hasCapability(Type.BRACHIOSAUR)) {
            sourceOne = Type.ON_TREE;
            sourceTwo = Type.ON_TREE;
            groundTypeOne = Type.TREE;
            groundTypeTwo = Type.TREE;
        }

        if(actor.hasCapability(Type.ALLOSAUR)) {
            sourceOne = Type.EGG;
            sourceTwo = Type.CORPSE;
        }

        if(actor.hasCapability(Type.PTERODACTYLS)) {
            sourceOne = Type.CORPSE;
            sourceTwo = Type.FISH;
        }

        // if actor is standing on food EatFoodaction is called
        for (Item indexItem : itemsHere) {
            if (indexItem.hasCapability(sourceOne) ||
                    indexItem.hasCapability(sourceTwo)) {
                return new EatFoodAction(actor, itemsHere);
            }
        }

        //if stegosaur is next to Allosaur it will attack it
        if(actor.hasCapability(Type.ALLOSAUR)) {
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                Actor thisActor = destination.getActor();
                if (thisActor != null && (thisActor.hasCapability(Type.STEGOSAUR) ||
                        (thisActor.hasCapability(Type.PTERODACTYLS) && thisActor.hasCapability(Type.CANT_FLY))))// if target is pterodactyl and not flying attack it
{
                    return new AttackAction(thisActor);
                }
            }
            //scan the map and search for closest food allosaur can eat or actors it can attack
            for (int x : widths) {
                for (int y : heights) {
                    Actor thisActor = map.at(x, y).getActor();
                    List<Item> itemsThere = map.at(x, y).getItems();
                    for (Item indexItem : itemsThere) {
                        if ((indexItem.hasCapability(sourceOne)) ||
                                (indexItem.hasCapability(sourceTwo)) ||
                                (thisActor != null && (thisActor.hasCapability(Type.STEGOSAUR) ||
                                        thisActor.hasCapability(Type.PTERODACTYLS)))) {
                            Location there = map.at(x, y);
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

        //scan the map and search for closest food STEGOSAUR/BRACHIOSAUR can eat
        if ((actor.hasCapability(Type.HERBIVORE))) {
            for (int x : widths) {
                for (int y : heights) {
                    Ground groundHere = map.at(x, y).getGround();
                    if (groundHere.hasCapability(groundTypeOne) ||
                            groundHere.hasCapability(groundTypeTwo)) {
                        Location there = map.at(x, y);
                        List<Item> itemsThere = there.getItems();
                        for (Item indexItem : itemsThere) {
                            if (indexItem.hasCapability(Type.FRUIT)) {
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
            }
        }



        if (actor.hasCapability(Type.PTERODACTYLS)){
            boolean landable = true;
            for (Item indexItem : itemsHere) {
                if (indexItem.hasCapability(sourceOne) ||
                        indexItem.hasCapability(sourceTwo)) {
                    for (Exit exit : here.getExits()) {
                        Location destination = exit.getDestination();
                        Actor thisActor = destination.getActor();
                        if (thisActor != null) {
                            landable = false;
                        }
                    }
                    if (landable){
                        return new EatFoodAction(actor, itemsHere);
                    }
                }
            }

            for (int x : widths) {
                for (int y : heights) {
                    List<Item> itemsThere = map.at(x, y).getItems();
                    for (Item indexItem : itemsThere) {
                        if ((indexItem.hasCapability(sourceOne)) ||
                                (indexItem.hasCapability(sourceTwo))) {
                            Location there = map.at(x, y);
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

        //get direction to move to get closer
        if (there != null) {
            int currentDistance = Util.distance(here, closestFood);
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
